package com.resale.resalepayment.model.view;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Timestamp;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Entity
@Table(name = "view_payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect(fieldVisibility = ANY)
public class ViewPayment {


    @Basic
    @Id
    @Column(name = "payment_id")
    private int paymentId;
    @Basic
    @Column(name = "offer_id")
    private int offerId;
    @Basic
    @Column(name = "payment_amount")
    private Integer paymentAmount;
    @Basic
    @Column(name = "payment_method")
    private Integer paymentMethod;
    @Basic
    @Column(name = "payment_status")
    private Integer paymentStatus;
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
    @Column(name = "payment_created_at")
    private Timestamp paymentCreatedAt;
    @Basic
    @Column(name = "payment_updated_at")
    private Timestamp paymentUpdatedAt;
    @Basic
    @Column(name = "unit_id")
    private int unitId;
    @Basic
    @Column(name = "customer_id")
    private int customerId;
    @Basic
    @Column(name = "reservation_amount")
    private String reservationAmount;
    @Basic
    @Column(name = "sap_offer_number")
    private String sapOfferNumber;
    @Basic
    @Column(name = "offer_paid_amount")
    private String offerPaidAmount;
    @Basic
    @Column(name = "offer_status_text_en")
    private String offerStatusTextEn;
    @Basic
    @Column(name = "offer_status_text_ar")
    private String offerStatusTextAr;
    @Basic
    @Column(name = "payment_plan")
    private String paymentPlan;
    @Basic
    @Column(name = "finishing")
    private String finishing;
    @Basic
    @Column(name = "maintenance_plan")
    private String maintenancePlan;
    @Basic
    @Column(name = "club_plan")
    private String clubPlan;
    @Basic
    @Column(name = "customer_name")
    private String customerName;
    @Basic
    @Column(name = "customer_name_ar")
    private String customerNameAr;
    @Basic
    @Column(name = "customer_email")
    private String customerEmail;
    @Basic
    @Column(name = "customer_mobile")
    private String customerMobile;
    @Basic
    @Column(name = "customer_sap_partner_id")
    private String customerSapPartnerId;
    @Basic
    @Column(name = "unit_name_en")
    private String unitNameEn;
    @Basic
    @Column(name = "unit_name_ar")
    private String unitNameAr;
    @Basic
    @Column(name = "project_id")
    private Integer projectId;

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaidAt(Timestamp paidAt) {
        this.paidAt = paidAt;
    }

    public void setSapFiDocument(String sapFiDocument) {
        this.sapFiDocument = sapFiDocument;
    }

    public void setBankResponse(String bankResponse) {
        this.bankResponse = bankResponse;
    }

    public void setPaymentCreatedAt(Timestamp paymentCreatedAt) {
        this.paymentCreatedAt = paymentCreatedAt;
    }

    public void setPaymentUpdatedAt(Timestamp paymentUpdatedAt) {
        this.paymentUpdatedAt = paymentUpdatedAt;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setReservationAmount(String reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public void setSapOfferNumber(String sapOfferNumber) {
        this.sapOfferNumber = sapOfferNumber;
    }

    public void setOfferPaidAmount(String offerPaidAmount) {
        this.offerPaidAmount = offerPaidAmount;
    }

    public void setOfferStatusTextEn(String offerStatusTextEn) {
        this.offerStatusTextEn = offerStatusTextEn;
    }

    public void setOfferStatusTextAr(String offerStatusTextAr) {
        this.offerStatusTextAr = offerStatusTextAr;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public void setFinishing(String finishing) {
        this.finishing = finishing;
    }

    public void setMaintenancePlan(String maintenancePlan) {
        this.maintenancePlan = maintenancePlan;
    }

    public void setClubPlan(String clubPlan) {
        this.clubPlan = clubPlan;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerNameAr(String customerNameAr) {
        this.customerNameAr = customerNameAr;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public void setCustomerSapPartnerId(String customerSapPartnerId) {
        this.customerSapPartnerId = customerSapPartnerId;
    }

    public void setUnitNameEn(String unitNameEn) {
        this.unitNameEn = unitNameEn;
    }

    public void setUnitNameAr(String unitNameAr) {
        this.unitNameAr = unitNameAr;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getOfferId() {
        return offerId;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public Timestamp getPaidAt() {
        return paidAt;
    }

    public String getSapFiDocument() {
        return sapFiDocument;
    }

    public String getBankResponse() {
        return bankResponse;
    }

    public Timestamp getPaymentCreatedAt() {
        return paymentCreatedAt;
    }

    public Timestamp getPaymentUpdatedAt() {
        return paymentUpdatedAt;
    }

    public int getUnitId() {
        return unitId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getReservationAmount() {
        return reservationAmount;
    }

    public String getSapOfferNumber() {
        return sapOfferNumber;
    }

    public String getOfferPaidAmount() {
        return offerPaidAmount;
    }

    public String getOfferStatusTextEn() {
        return offerStatusTextEn;
    }

    public String getOfferStatusTextAr() {
        return offerStatusTextAr;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public String getFinishing() {
        return finishing;
    }

    public String getMaintenancePlan() {
        return maintenancePlan;
    }

    public String getClubPlan() {
        return clubPlan;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerNameAr() {
        return customerNameAr;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public String getCustomerSapPartnerId() {
        return customerSapPartnerId;
    }

    public String getUnitNameEn() {
        return unitNameEn;
    }

    public String getUnitNameAr() {
        return unitNameAr;
    }

    public Integer getProjectId() {
        return projectId;
    }
}


