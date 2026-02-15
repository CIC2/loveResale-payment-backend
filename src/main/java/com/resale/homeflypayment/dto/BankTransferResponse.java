package com.resale.homeflypayment.dto;

import com.resale.homeflypayment.model.BankTransfers;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BankTransferResponse {

    private Integer id;
    private Integer offerId;
    private Integer amount;
    private String country;
    private String transferDate;
    private String customerBankName;
    private String tmgBankName;
    private String tmgBankAccount;
    private String imageUrl;
    private String imageContentType;
    private String fileName;
    private Timestamp createdAt;

    public static BankTransferResponse fromEntity(BankTransfers entity) {

        BankTransferResponse response = new BankTransferResponse();

        response.setId(entity.getId());
        response.setOfferId(entity.getOfferId());
        response.setAmount(entity.getAmount());
        response.setCountry(entity.getCountry());
        response.setTransferDate(entity.getTransferDate());
        response.setCustomerBankName(entity.getCustomerBankName());
        response.setTmgBankName(entity.getTmgBankName());
        response.setTmgBankAccount(entity.getTmgBankAccount());
        response.setImageUrl(entity.getImageUrl());
        response.setImageContentType(entity.getImageContentType());
        response.setFileName(entity.getFileName());
        response.setCreatedAt(entity.getCreatedAt());

        return response;
    }
}


