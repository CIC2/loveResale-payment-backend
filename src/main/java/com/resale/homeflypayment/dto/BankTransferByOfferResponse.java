package com.resale.homeflypayment.dto;

import lombok.Data;

@Data
public class BankTransferByOfferResponse {
    private Integer offerId;
    private BankTransferResponse bankTransfer;
}


