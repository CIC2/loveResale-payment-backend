package com.resale.homeflypayment.service;


import com.resale.homeflypayment.dto.PayMobPaymentRequest;
import com.resale.homeflypayment.model.CBankKeys;
import com.resale.homeflypayment.model.Payment;
import com.resale.homeflypayment.repository.CBankKeysRepository;
import com.resale.homeflypayment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymobService {

    private static final String PAYMOB_BASE_URL =
            "https://oman.paymob.com/v1/intention/";

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    CBankKeysRepository bankKeysRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> createIntention(PayMobPaymentRequest dto) {

        System.out.println("========== PAYMOB CREATE INTENTION START ==========");
        System.out.println("Request DTO: " + dto);

        int bankId = 2; // PAYMOB
        int projectId = resolveProjectId(dto.getProjectCode());

        // 1️⃣ Load keys from DB
        CBankKeys bankKeys = bankKeysRepository
                .findByBankIdAndProjectId(bankId, projectId)
                .orElseThrow(() -> new RuntimeException("Paymob keys not found"));

        System.out.println("Loaded Bank Keys ID: " + bankKeys.getId());

        // 2️⃣ Save payment record
        Payment payment = new Payment();
        payment.setOfferId(Integer.parseInt(dto.getOfferId()));
        payment.setAmount(dto.getAmount());
        payment.setMethod(1); // PAYMOB
        payment.setStatus(0); // PENDING
        payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        payment = paymentRepository.save(payment);

        System.out.println("Payment saved with ID: " + payment.getId());

        // 3️⃣ Billing data
        Map<String, Object> billingData = new HashMap<>();
        billingData.put("first_name", dto.getFirstName());
        billingData.put("last_name", dto.getLastName());
//        billingData.put("email", dto.getCustomerEmail());
        billingData.put("email", "21412@gmail.com");
        billingData.put("phone_number", dto.getPhone());
        billingData.put("city", "NA");
        billingData.put("country", "OM");

        // 4️⃣ Request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", dto.getAmount() + "000");
        requestBody.put("currency", "OMR");
        requestBody.put("payment_methods",
                List.of(Integer.parseInt(bankKeys.getExtraKey())));
        requestBody.put("billing_data", billingData);
        requestBody.put("special_reference", payment.getId());
        requestBody.put("redirection_url", "https://vso-dev-customer.nuca-mycluster-eu-de-1-cx-5fc3035946e1f798c7284cb63267e8d1-0000.eu-de.containers.appdomain.cloud/" + "api/payment/bank/paymob-callback/" + payment.getId());
        requestBody.put("notification_url", "https://vso-dev-customer.nuca-mycluster-eu-de-1-cx-5fc3035946e1f798c7284cb63267e8d1-0000.eu-de.containers.appdomain.cloud/" + "api/payment/bank/paymob-callback");

        System.out.println("Paymob Request Body: " + requestBody);

        // 5️⃣ Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Token " + bankKeys.getSecretKey());

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        // 6️⃣ Call Paymob
        ResponseEntity<Map> response =
                restTemplate.exchange(
                        PAYMOB_BASE_URL,
                        HttpMethod.POST,
                        entity,
                        Map.class
                );

        System.out.println("Paymob Status: " + response.getStatusCode());
        System.out.println("Paymob Response: " + response.getBody());

        String clientSecret = (String) response.getBody().get("client_secret");

        if (clientSecret == null || clientSecret.isBlank()) {
            throw new IllegalStateException("client_secret missing from Paymob");
        }

        // 7️⃣ Update payment
        payment.setBankResponse(response.getBody().toString());
        paymentRepository.save(payment);

        System.out.println("Payment updated with client_secret");

        // 8️⃣ Build response
        Map<String, Object> result = new HashMap<>();
        result.put("clientSecret", clientSecret);
        result.put(
                "redirectUrl",
                "https://oman.paymob.com/unifiedcheckout/?publicKey="
                        + bankKeys.getProfileId()
                        + "&clientSecret=" + clientSecret
        );

        System.out.println("Final Response: " + result);
        System.out.println("========== PAYMOB CREATE INTENTION END ==========");

        return result;
    }

    private int resolveProjectId(String projectCode) {
        return switch (projectCode) {
            case "J" -> 1;
            case "U" -> 6;
            case "E" -> 6;
            default -> throw new IllegalArgumentException("Unsupported project code");
        };
    }
}



