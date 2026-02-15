package com.resale.homeflypayment.feign;

import com.resale.homeflypayment.dto.GetOffersDTO;
import com.resale.homeflypayment.dto.UpdateOfferPaymentDTO;
import com.resale.homeflypayment.utils.ReturnObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "offer-service",
        url = "${offer.url}",
        configuration = OfferFeignConfig.class)
public interface OfferFeignClient {
    @GetMapping("/getInternalOffer")
    ResponseEntity<ReturnObject<GetOffersDTO>> getOfferDetails(
            @RequestParam Integer offerId,@RequestParam Long customerId);

    @PutMapping("/internal/offers/payment")
    ResponseEntity<ReturnObject<?>> updateOfferAfterPayment(
            @RequestBody UpdateOfferPaymentDTO request
    );
}


