package com.resale.resalepayment.feign;

import com.resale.resalepayment.dto.GetOffersDTO;
import com.resale.resalepayment.dto.UpdateOfferPaymentDTO;
import com.resale.resalepayment.utils.ReturnObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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


