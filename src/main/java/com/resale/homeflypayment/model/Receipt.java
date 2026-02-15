package com.resale.homeflypayment.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Receipt {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "payment_id")
    private int paymentId;
    @Basic
    @Column(name = "amount")
    private int amount;
    @Basic
    @Column(name = "project_id")
    private int projectId;
    @Basic
    @Column(name = "sap_fi_document")
    private String sapFiDocument;
    @Basic
    @Column(name = "offer_id")
    private int offerId;
    @Basic
    @Column(name = "customer_id")
    private int customerId;
    @Basic
    @Column(name = "unit_id")
    private int unitId;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getSapFiDocument() {
        return sapFiDocument;
    }

    public void setSapFiDocument(String sapFiDocument) {
        this.sapFiDocument = sapFiDocument;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        if (id != receipt.id) return false;
        if (paymentId != receipt.paymentId) return false;
        if (amount != receipt.amount) return false;
        if (projectId != receipt.projectId) return false;
        if (offerId != receipt.offerId) return false;
        if (customerId != receipt.customerId) return false;
        if (unitId != receipt.unitId) return false;
        if (sapFiDocument != null ? !sapFiDocument.equals(receipt.sapFiDocument) : receipt.sapFiDocument != null)
            return false;
        if (createdAt != null ? !createdAt.equals(receipt.createdAt) : receipt.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + paymentId;
        result = 31 * result + amount;
        result = 31 * result + projectId;
        result = 31 * result + (sapFiDocument != null ? sapFiDocument.hashCode() : 0);
        result = 31 * result + offerId;
        result = 31 * result + customerId;
        result = 31 * result + unitId;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}


