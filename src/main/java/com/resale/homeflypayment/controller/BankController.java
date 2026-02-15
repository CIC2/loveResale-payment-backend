package com.resale.homeflypayment.controller;

import com.resale.homeflypayment.dto.BankTransferByOfferIdsRequest;
import com.resale.homeflypayment.dto.BankTransferByOfferResponse;
import com.resale.homeflypayment.dto.BankTransferRequest;
import com.resale.homeflypayment.dto.CustomerPaymentRequest;
import com.resale.homeflypayment.model.Payment;
import com.resale.homeflypayment.model.Receipt;
import com.resale.homeflypayment.repository.PaymentRepository;
import com.resale.homeflypayment.repository.ReceiptRepository;
import com.resale.homeflypayment.service.PaymentService;
import com.resale.homeflypayment.utils.ReturnObject;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ReceiptRepository receiptRepository;
    @PostMapping("/communicate")
    public ResponseEntity<?> createPayment(@RequestBody CustomerPaymentRequest customerPaymentRequest) {

        return paymentService.createPaymentRequestToBank(customerPaymentRequest);
    }



    @GetMapping("/customerPayments")
    public ResponseEntity<?> getAllCustomerPayments(
        @RequestParam Integer customerId,
        @RequestParam Integer offerId
    ) {
        return ResponseEntity.ok(
                paymentService.getAllCustomerPayments(customerId, offerId)
        );
    }
    @GetMapping("/receipt")
    public ResponseEntity<?> getReceipt(@RequestParam Integer paymentId) {

        System.out.println(">> [Receipt] Loading receipt for paymentId: " + paymentId);

        return ResponseEntity.ok(
                paymentService.getReceiptDetails(paymentId)
        );
    }



    @GetMapping("/receipt/{paymentId}")
    public RedirectView getReceipt(@PathVariable int paymentId) {

        System.out.println(">> [Receipt] Loading receipt for paymentId: " + paymentId);

        Optional<Receipt> receiptOpt =
                receiptRepository.findByPaymentId(paymentId);

        if (receiptOpt.isEmpty()) {
            System.out.println("!! [Receipt] Not found for paymentId: " + paymentId);
            return new RedirectView(
                    "https://vso-dev-customer.nuca-mycluster-eu-de-1-cx-5fc3035946e1f798c7284cb63267e8d1-0000.eu-de.containers.appdomain.cloud/api/payment/bank/receipt/error?reasonMessage=RECEIPT_NOT_FOUND"
            );
        }

        String frontendUrl =
                "https://vso-dev-customer.nuca-mycluster-eu-de-1-cx-5fc3035946e1f798c7284cb63267e8d1-0000.eu-de.containers.appdomain.cloud/profile/receipt/"+paymentId;

        System.out.println(">> [Receipt] Redirecting to: " + frontendUrl);
        return new RedirectView(frontendUrl);
    }


    @GetMapping("/paymob-callback/{paymentId}")
    public RedirectView handlePaymobRedirect(
            @PathVariable String paymentId,
            @RequestParam Map<String, String> allParams) {

        System.out.println(">> [Paymob Redirect] Payment ID: " + paymentId);
        System.out.println(">> [Paymob Redirect] Params: " + allParams);

        return processPaymobCallback(paymentId, allParams);
    }

    private RedirectView processPaymobCallback(
            String paymentId,
            Map<String, String> allParams) {

        Optional<Payment> optionalPayment =
                paymentRepository.findById(Integer.valueOf(paymentId));

        if (optionalPayment.isEmpty()) {
            System.out.println("!! [Paymob] Payment NOT FOUND: " + paymentId);
            return new RedirectView(
                    "https://vso-dev-customer.nuca-mycluster-eu-de-1-cx-5fc3035946e1f798c7284cb63267e8d1-0000.eu-de.containers.appdomain.cloud/api/payment/bank/receipt/error?reasonMessage=NOT_FOUND"
            );
        }

        Payment payment = optionalPayment.get();

        String success = allParams.get("success");

        if (!"true".equalsIgnoreCase(success)) {
            payment.setStatus(2); // Rejected
            payment.setBankResponse(allParams.toString());
            paymentRepository.save(payment);

            System.out.println("!! [Paymob] Bank rejected payment");

            return new RedirectView(
                      "https://vso-dev-customer.nuca-mycluster-eu-de-1-cx-5fc3035946e1f798c7284cb63267e8d1-0000.eu-de.containers.appdomain.cloud/api/payment/bank/receipt/error?reasonMessage=BANK_REJECTION"
            );
        }

        // Success
        payment.setStatus(1);
        payment.setPaidAt(new Timestamp(System.currentTimeMillis()));
        payment.setBankResponse(allParams.toString());
        paymentRepository.save(payment);

        System.out.println(">> [Paymob] Payment SUCCESS, creating receipt");

        paymentService.createReceipt(payment);

        return new RedirectView("https://vso-dev-customer.nuca-mycluster-eu-de-1-cx-5fc3035946e1f798c7284cb63267e8d1-0000.eu-de.containers.appdomain.cloud/api/payment/bank/receipt/" + payment.getId()
        );
    }

    @PostMapping(
            value = "/bank-transfer",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ReturnObject<?>> createBankTransfer(

            @RequestPart(name = "image", required = true)
            MultipartFile image,

            @RequestParam int offerId,
            @RequestParam int amount,
            @RequestParam String country,
            @RequestParam String transferDate,
            @RequestParam String customerBankName,
            @RequestParam String tmgBankName,
            @RequestParam String tmgBankAccount
    ) {

        try {

            BankTransferRequest request = new BankTransferRequest(
                    offerId,
                    amount,
                    country,
                    transferDate,
                    customerBankName,
                    tmgBankName,
                    tmgBankAccount
            );

            ReturnObject<?> response =
                    paymentService.createBankTransfer(request, image);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(
                    new ReturnObject<>(ex.getMessage(), false, null)
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ReturnObject<>(
                            "Something went wrong while creating bank transfer",
                            false,
                            e.getMessage()
                    )
            );
        }
    }
    @GetMapping("/bank-transfer/{bankTransferId}")
    public ResponseEntity<ReturnObject<?>> getBankTransferById(
            @PathVariable Integer bankTransferId
    ) {
        try {
            System.out.println("📥 Incoming request to fetch bank transfer with ID: " + bankTransferId);

            ReturnObject<?> response =
                    paymentService.getBankTransferById(bankTransferId);

            System.out.println("✅ Bank transfer fetched successfully");

            return ResponseEntity.ok(response);

        } catch (EntityNotFoundException e) {

            System.out.println("❌ Bank transfer not found: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ReturnObject<>(e.getMessage(), false, null)
            );

        } catch (Exception e) {

            System.out.println("🔥 Unexpected error while fetching bank transfer");
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ReturnObject<>("Failed to fetch bank transfer", false, e.getMessage())
            );
        }
    }

    @PostMapping("/internal/bank-transfers/by-offer-ids")
    public ResponseEntity<ReturnObject<List<BankTransferByOfferResponse>>>
    getBankTransfersByOfferIds(
            @RequestBody BankTransferByOfferIdsRequest request
    ) {
        return ResponseEntity.ok(
                paymentService.getBankTransfersByOfferIds(
                        request.getOfferIds()
                )
        );
    }



}

