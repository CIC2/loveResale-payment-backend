package com.resale.homeflypayment.service;

import com.resale.homeflypayment.dto.*;
import com.resale.homeflypayment.feign.OfferFeignClient;
import com.resale.homeflypayment.model.BankTransfers;
import com.resale.homeflypayment.model.Payment;
import com.resale.homeflypayment.model.Receipt;
import com.resale.homeflypayment.model.view.ViewPayment;
import com.resale.homeflypayment.objectstorage.StorageService;
import com.resale.homeflypayment.repository.BankTransfersRepository;
import com.resale.homeflypayment.repository.PaymentRepository;
import com.resale.homeflypayment.repository.ReceiptRepository;
import com.resale.homeflypayment.repository.ViewPaymentRepository;
import com.resale.homeflypayment.utils.ReturnObject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final OfferFeignClient offerFeignClient;
    private final PaymobService paymobService;
    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ViewPaymentRepository viewPaymentRepository;
    @Autowired
    private BankTransfersRepository bankTransfersRepository;
    @Autowired
    private StorageService storageService;

    public PaymentService(OfferFeignClient offerFeignClient, PaymobService paymobService) {
        this.offerFeignClient = offerFeignClient;
        this.paymobService = paymobService;
    }

    public ResponseEntity<?> createPaymentRequestToBank(
            CustomerPaymentRequest request) {


        GetOffersDTO offer =
                offerFeignClient
                        .getOfferDetails(request.getOfferId(), request.getCustomerId())
                        .getBody()
                        .getData();

//        if(offer.getProjectCode().equals("O")||offer.getProjectCode().equals("U")||offer.getProjectCode().equals("J")) {
            PayMobPaymentRequest paymobRequest = new PayMobPaymentRequest();
            paymobRequest.setOfferId(request.getOfferId().toString());
            paymobRequest.setAmount(request.getAmount());
            paymobRequest.setFirstName(offer.getCustomerName());
            paymobRequest.setLastName(offer.getCustomerName());
            paymobRequest.setPhone(offer.getCustomerMobile());
            paymobRequest.setCustomerEmail(offer.getCustomerEmail());
            paymobRequest.setProjectCode(offer.getProjectCode());

            return ResponseEntity.ok(
                    paymobService.createIntention(paymobRequest)
            );
//        }else{
//            return ResponseEntity.badRequest().body("Please pay on Oman projects only");
//        }
    }

    public void createReceipt(Payment payment) {

        String sapFiDocument = "FAIL";
        ViewPayment viewPayment =
                viewPaymentRepository.findByPaymentId(payment.getId());
        
        System.out.println(">> [Receipt] Starting receipt creation");
        System.out.println(">> [Receipt] Payment ID: " + payment.getId());

        // SAP integration removed - set to FAIL
        payment.setSapFiDocument(sapFiDocument);
        paymentRepository.save(payment);
        System.out.println(">> [Receipt] Payment updated with SAP FI");

        Receipt receipt = new Receipt();
        receipt.setPaymentId(payment.getId());
        receipt.setAmount(payment.getAmount());
        receipt.setOfferId(payment.getOfferId());
        receipt.setSapFiDocument(sapFiDocument);
        receipt.setCustomerId(viewPayment.getCustomerId());
        receipt.setProjectId(viewPayment.getProjectId());
        receipt.setUnitId(viewPayment.getUnitId());
        receipt.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        receiptRepository.save(receipt);

        System.out.println(">> [Receipt] Notifying offer service");

        UpdateOfferPaymentDTO dto = new UpdateOfferPaymentDTO(
                payment.getOfferId(),
                payment.getAmount(),
                "VISA",
                String.valueOf(payment.getId())
        );

        offerFeignClient.updateOfferAfterPayment(dto);
        System.out.println(">> [Receipt] Saved successfully with SAP FI: " + sapFiDocument);
    }
    private ParsedUnitSapCode parseUnitSapCode(String unitSapCode) {

        System.out.println(">> [Parser] Raw unit SAP code: " + unitSapCode);

        if (unitSapCode == null || unitSapCode.length() < 12) {
            throw new IllegalArgumentException("Invalid unit SAP code: " + unitSapCode);
        }

        String companyCode = unitSapCode.substring(0, 4);

        // Business entity = everything between companyCode and last 8 digits
        String businessEntity =
                unitSapCode.substring(4, unitSapCode.length() - 8);

        // Unit = last 8 digits, remove leading zeros
        String unitRaw = unitSapCode.substring(unitSapCode.length() - 8);
        String unit = String.valueOf(Integer.parseInt(unitRaw));

        System.out.println(">> [Parser] companyCode = " + companyCode);
        System.out.println(">> [Parser] businessEntity = " + businessEntity);
        System.out.println(">> [Parser] unit = " + unit);

        return new ParsedUnitSapCode(companyCode, businessEntity, unit);
    }


    public ReturnObject<List<ViewPayment>> getAllCustomerPayments(
            Integer customerId,
            Integer offerId
    ) {

        List<ViewPayment> payments =
                viewPaymentRepository
                        .findByCustomerIdAndOfferIdAndSapFiDocumentIsNotNull(
                                customerId,
                                offerId
                        );

        ReturnObject<List<ViewPayment>> response =
                new ReturnObject<>("Customer payments loaded successfully", true,payments);

        response.setData(payments);

        return response;
    }

    public ReceiptDetailsResponse getReceiptDetails(Integer paymentId) {

        System.out.println(">> [Receipt] Fetching receipt & payment view");

        Receipt receipt = receiptRepository
                .findByPaymentId(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Receipt not found for paymentId: " + paymentId)
                );

        ViewPayment viewPayment = viewPaymentRepository
                .findByPaymentId(paymentId);

        if (viewPayment == null) {
            throw new RuntimeException("ViewPayment not found for paymentId: " + paymentId);
        }

        ReceiptDetailsResponse response = new ReceiptDetailsResponse();

        // ===== Receipt =====
        response.setPaymentId(receipt.getPaymentId());
        response.setOfferId(receipt.getOfferId());
        response.setCustomerId(receipt.getCustomerId());
        response.setUnitId(receipt.getUnitId());
        response.setProjectId(receipt.getProjectId());
        response.setAmount(receipt.getAmount());
        response.setSapFiDocument(receipt.getSapFiDocument());
        response.setCreatedAt(receipt.getCreatedAt());

        // ===== ViewPayment =====
        response.setCustomerName(viewPayment.getCustomerName());
        response.setCustomerNameAr(viewPayment.getCustomerNameAr());
        response.setCustomerEmail(viewPayment.getCustomerEmail());
        response.setCustomerMobile(viewPayment.getCustomerMobile());
        response.setCustomerSapPartnerId(viewPayment.getCustomerSapPartnerId());

        response.setUnitNameEn(viewPayment.getUnitNameEn());
        response.setUnitNameAr(viewPayment.getUnitNameAr());

        response.setPaymentPlan(viewPayment.getPaymentPlan());
        response.setMaintenancePlan(viewPayment.getMaintenancePlan());
        response.setClubPlan(viewPayment.getClubPlan());
        response.setFinishing(viewPayment.getFinishing());

        response.setOfferStatusTextEn(viewPayment.getOfferStatusTextEn());
        response.setOfferStatusTextAr(viewPayment.getOfferStatusTextAr());

        System.out.println(">> [Receipt] Response prepared successfully");

        return response;
    }


    public ReturnObject<?> createBankTransfer(
            BankTransferRequest request,
            MultipartFile image
    ) {
        System.out.println(">> [PaymentService] Creating bank transfer");
        System.out.println(">> Request: " + request);

        try {
            validateCreateRequest(request, image);

            String originalFileName = image.getOriginalFilename();
            String extension = (originalFileName != null && originalFileName.contains("."))
                    ? originalFileName.substring(originalFileName.lastIndexOf('.') + 1)
                    : "jpg";

            String uniqueFileName = UUID.randomUUID() + "." + extension;
            String imagePath = "bank-transfers/" + uniqueFileName;

            System.out.println(">> Uploading image to: " + imagePath);
            storageService.uploadMultipartFile(image, imagePath);

            BankTransfers transfer = request.toEntity();
            transfer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            transfer.setFileName(originalFileName);
            transfer.setImageContentType(image.getContentType());
            transfer.setImageUrl(imagePath);

            BankTransfers savedTransfer =
                    bankTransfersRepository.saveAndFlush(transfer);

            System.out.println(">> Bank transfer saved with ID: " + savedTransfer.getId());

            // 🔹 Call Offer Service AFTER save
            UpdateOfferPaymentDTO dto = new UpdateOfferPaymentDTO(
                    request.getOfferId(),
                    request.getAmount(),
                    "BANK_TRANSFER",
                    String.valueOf(savedTransfer.getId())
            );

            System.out.println(">> Calling Offer Service with DTO: " + dto);

            ResponseEntity<?> offerResponse =
                    offerFeignClient.updateOfferAfterPayment(dto);

            System.out.println(">> Offer Service response status: " + offerResponse.getStatusCode());

            if (offerResponse.getStatusCode().is2xxSuccessful()) {
                System.out.println(">> Offer updated successfully");
                return new ReturnObject<>(
                        "Bank transfer created and pending approval",
                        true,
                        savedTransfer
                );
            }

            throw new RuntimeException("Offer service failed");

        } catch (Exception e) {
            System.out.println(">> ERROR while creating bank transfer");
            e.printStackTrace();
            throw new RuntimeException(
                    "Something went wrong while creating bank transfer",
                    e
            );
        }
    }
    private void validateCreateRequest(
            BankTransferRequest request,
            MultipartFile image
    ) {

        if (request.getOfferId() <= 0)
            throw new IllegalArgumentException("Invalid offerId");

        if (request.getAmount() <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero");

        if (request.getCountry() == null || request.getCountry().isBlank())
            throw new IllegalArgumentException("Country is required");

        if (request.getTransferDate() == null || request.getTransferDate().isBlank())
            throw new IllegalArgumentException("Transfer date is required");

        if (request.getCustomerBankName() == null || request.getCustomerBankName().isBlank())
            throw new IllegalArgumentException("Customer bank name is required");

        if (request.getTmgBankName() == null || request.getTmgBankName().isBlank())
            throw new IllegalArgumentException("TMG bank name is required");

        if (request.getTmgBankAccount() == null || request.getTmgBankAccount().isBlank())
            throw new IllegalArgumentException("TMG bank account is required");

        if (image == null || image.isEmpty())
            throw new IllegalArgumentException("Transfer image is required");
    }
    public ReturnObject<BankTransferResponse> getBankTransferById(Integer bankTransferId) {

        System.out.println("➡️ Fetching bank transfer by ID: " + bankTransferId);

        BankTransfers transfer =
                bankTransfersRepository.findById(bankTransferId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Bank transfer not found with id: " + bankTransferId
                                )
                        );

        System.out.println("✅ Bank transfer found for offerId: " + transfer.getOfferId());

        return new ReturnObject<>(
                "Bank transfer fetched successfully",
                true,
                BankTransferResponse.fromEntity(transfer)
        );
    }


    public ReturnObject<List<BankTransferByOfferResponse>>
    getBankTransfersByOfferIds(List<Integer> offerIds) {

        System.out.println("➡️ Fetching bank transfers for offers: " + offerIds);

        List<BankTransfers> transfers =
                bankTransfersRepository.findAllByOfferIdIn(offerIds);

        Map<Integer, BankTransfers> map =
                transfers.stream()
                        .collect(Collectors.toMap(
                                BankTransfers::getOfferId,
                                t -> t
                        ));

        List<BankTransferByOfferResponse> response =
                offerIds.stream()
                        .map(offerId -> {
                            BankTransfers t = map.get(offerId);
                            if (t == null) return null;

                            BankTransferByOfferResponse r =
                                    new BankTransferByOfferResponse();
                            r.setOfferId(offerId);
                            r.setBankTransfer(
                                    BankTransferResponse.fromEntity(t)
                            );
                            return r;
                        })
                        .filter(Objects::nonNull)
                        .toList();

        return new ReturnObject<>(
                "Bank transfers fetched successfully",
                true,
                response
        );
    }




}


