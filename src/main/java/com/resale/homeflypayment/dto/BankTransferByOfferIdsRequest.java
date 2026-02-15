package com.resale.homeflypayment.dto;

import lombok.Data;

import java.util.List;

@Data
public class BankTransferByOfferIdsRequest {
    private List<Integer> offerIds;
}


