package com.resale.homeflypayment.dto;

import com.resale.homeflypayment.utils.OfferStatus;
import lombok.Data;

@Data
public class GetOffersDTO {
    private Integer offerId;
    private Integer unitId;

    private String offerNumber;
    private String projectName;
    private String unitNumber;
    private String unitStatus;
    private String reservationAmount;
    private String remainingTime;
    private String orderDateTime;
    private String expirationDate;

    private String paymentPlan;
    private String paymentPlanDescriptionEn;
    private String paymentPlanDescriptionAr;
    private String finishing;
    private String maintenancePlan;
    private String maintenancePlanDescriptionEn;
    private String maintenancePlanDescriptionAr;

    private OfferStatus offerStatus;
    private String offerStatusTextEn;
    private String offerStatusTextAr;

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getOfferNumber() {
        return offerNumber;
    }

    public void setOfferNumber(String offerNumber) {
        this.offerNumber = offerNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(String unitStatus) {
        this.unitStatus = unitStatus;
    }

    public String getReservationAmount() {
        return reservationAmount;
    }

    public void setReservationAmount(String reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public String getPaymentPlanDescriptionEn() {
        return paymentPlanDescriptionEn;
    }

    public void setPaymentPlanDescriptionEn(String paymentPlanDescriptionEn) {
        this.paymentPlanDescriptionEn = paymentPlanDescriptionEn;
    }

    public String getPaymentPlanDescriptionAr() {
        return paymentPlanDescriptionAr;
    }

    public void setPaymentPlanDescriptionAr(String paymentPlanDescriptionAr) {
        this.paymentPlanDescriptionAr = paymentPlanDescriptionAr;
    }

    public String getFinishing() {
        return finishing;
    }

    public void setFinishing(String finishing) {
        this.finishing = finishing;
    }

    public String getMaintenancePlan() {
        return maintenancePlan;
    }

    public void setMaintenancePlan(String maintenancePlan) {
        this.maintenancePlan = maintenancePlan;
    }

    public String getMaintenancePlanDescriptionEn() {
        return maintenancePlanDescriptionEn;
    }

    public void setMaintenancePlanDescriptionEn(String maintenancePlanDescriptionEn) {
        this.maintenancePlanDescriptionEn = maintenancePlanDescriptionEn;
    }

    public String getMaintenancePlanDescriptionAr() {
        return maintenancePlanDescriptionAr;
    }

    public void setMaintenancePlanDescriptionAr(String maintenancePlanDescriptionAr) {
        this.maintenancePlanDescriptionAr = maintenancePlanDescriptionAr;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerNationality() {
        return customerNationality;
    }

    public void setCustomerNationality(String customerNationality) {
        this.customerNationality = customerNationality;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getUnitModelCode() {
        return unitModelCode;
    }

    public void setUnitModelCode(String unitModelCode) {
        this.unitModelCode = unitModelCode;
    }

    private Integer customerId;
    private String customerName;
    private String customerMobile;
    private String customerEmail;
    private String customerNationality;
    private String projectCode;
    private String unitModelCode;
}


