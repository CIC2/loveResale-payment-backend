package com.resale.homeflypayment.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReceiptDetailsResponse {

    // ===== Receipt data =====
    private Integer paymentId;
    private Integer offerId;
    private Integer customerId;
    private Integer unitId;
    private Integer projectId;
    private Integer amount;
    private String sapFiDocument;
    private Timestamp createdAt;

    // ===== Payment / Offer / Customer data (from view_payment) =====
    private String customerName;
    private String customerNameAr;
    private String customerEmail;
    private String customerMobile;
    private String customerSapPartnerId;

    private String unitNameEn;
    private String unitNameAr;

    private String paymentPlan;
    private String maintenancePlan;
    private String clubPlan;
    private String finishing;

    private String offerStatusTextEn;
    private String offerStatusTextAr;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSapFiDocument() {
        return sapFiDocument;
    }

    public void setSapFiDocument(String sapFiDocument) {
        this.sapFiDocument = sapFiDocument;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNameAr() {
        return customerNameAr;
    }

    public void setCustomerNameAr(String customerNameAr) {
        this.customerNameAr = customerNameAr;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerSapPartnerId() {
        return customerSapPartnerId;
    }

    public void setCustomerSapPartnerId(String customerSapPartnerId) {
        this.customerSapPartnerId = customerSapPartnerId;
    }

    public String getUnitNameEn() {
        return unitNameEn;
    }

    public void setUnitNameEn(String unitNameEn) {
        this.unitNameEn = unitNameEn;
    }

    public String getUnitNameAr() {
        return unitNameAr;
    }

    public void setUnitNameAr(String unitNameAr) {
        this.unitNameAr = unitNameAr;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public String getMaintenancePlan() {
        return maintenancePlan;
    }

    public void setMaintenancePlan(String maintenancePlan) {
        this.maintenancePlan = maintenancePlan;
    }

    public String getClubPlan() {
        return clubPlan;
    }

    public void setClubPlan(String clubPlan) {
        this.clubPlan = clubPlan;
    }

    public String getFinishing() {
        return finishing;
    }

    public void setFinishing(String finishing) {
        this.finishing = finishing;
    }

    public String getOfferStatusTextEn() {
        return offerStatusTextEn;
    }

    public void setOfferStatusTextEn(String offerStatusTextEn) {
        this.offerStatusTextEn = offerStatusTextEn;
    }

    public String getOfferStatusTextAr() {
        return offerStatusTextAr;
    }

    public void setOfferStatusTextAr(String offerStatusTextAr) {
        this.offerStatusTextAr = offerStatusTextAr;
    }
}


