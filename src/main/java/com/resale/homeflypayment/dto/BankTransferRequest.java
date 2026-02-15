package com.resale.homeflypayment.dto;

import com.resale.homeflypayment.model.BankTransfers;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankTransferRequest {

    private int offerId;
    private int amount;
    private String country;
    private String transferDate;
    private String customerBankName;
    private String tmgBankName;
    private String tmgBankAccount;

    public BankTransfers toEntity() {
        BankTransfers entity = new BankTransfers();
        entity.setOfferId(offerId);
        entity.setAmount(amount);
        entity.setCountry(country);
        entity.setTransferDate(transferDate);
        entity.setCustomerBankName(customerBankName);
        entity.setTmgBankName(tmgBankName);
        entity.setTmgBankAccount(tmgBankAccount);
        return entity;
    }
}


