package com.resale.homeflypayment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "offer_id")
    private int offerId;
    @Basic
    @Column(name = "amount")
    private Integer amount;
    @Basic
    @Column(name = "method")
    private Integer method;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "paid_at")
    private Timestamp paidAt;
    @Basic
    @Column(name = "sap_fi_document")
    private String sapFiDocument;
    @Basic
    @Column(name = "bank_response")
    private String bankResponse;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Timestamp paidAt) {
        this.paidAt = paidAt;
    }

    public String getSapFiDocument() {
        return sapFiDocument;
    }

    public void setSapFiDocument(String sapFiDocument) {
        this.sapFiDocument = sapFiDocument;
    }

    public String getBankResponse() {
        return bankResponse;
    }

    public void setBankResponse(String bankResponse) {
        this.bankResponse = bankResponse;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

