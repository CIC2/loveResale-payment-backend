package com.resale.resalepayment.dto;

import lombok.Data;

@Data
public class BankTransferByOfferResponse {
    private Integer offerId;
    private BankTransferResponse bankTransfer;
}


