package com.resale.resalepayment.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "bank_transfers", schema = "vso_dev_db", catalog = "")
public class BankTransfers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "offer_id")
    private int offerId;
    @Basic
    @Column(name = "amount")
    private int amount;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "transfer_date")
    private String transferDate;
    @Basic
    @Column(name = "customer_bank_name")
    private String customerBankName;
    @Basic
    @Column(name = "tmg_bank_name")
    private String tmgBankName;
    @Basic
    @Column(name = "tmg_bank_account")
    private String tmgBankAccount;
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    @Basic
    @Column(name = "image_content_type")
    private String imageContentType;
    @Basic
    @Column(name = "file_name")
    private String fileName;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getCustomerBankName() {
        return customerBankName;
    }

    public void setCustomerBankName(String customerBankName) {
        this.customerBankName = customerBankName;
    }

    public String getTmgBankName() {
        return tmgBankName;
    }

    public void setTmgBankName(String tmgBankName) {
        this.tmgBankName = tmgBankName;
    }

    public String getTmgBankAccount() {
        return tmgBankAccount;
    }

    public void setTmgBankAccount(String tmgBankAccount) {
        this.tmgBankAccount = tmgBankAccount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

        BankTransfers that = (BankTransfers) o;

        if (id != that.id) return false;
        if (offerId != that.offerId) return false;
        if (amount != that.amount) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (transferDate != null ? !transferDate.equals(that.transferDate) : that.transferDate != null) return false;
        if (customerBankName != null ? !customerBankName.equals(that.customerBankName) : that.customerBankName != null)
            return false;
        if (tmgBankName != null ? !tmgBankName.equals(that.tmgBankName) : that.tmgBankName != null) return false;
        if (tmgBankAccount != null ? !tmgBankAccount.equals(that.tmgBankAccount) : that.tmgBankAccount != null)
            return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;
        if (imageContentType != null ? !imageContentType.equals(that.imageContentType) : that.imageContentType != null)
            return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + offerId;
        result = 31 * result + amount;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (transferDate != null ? transferDate.hashCode() : 0);
        result = 31 * result + (customerBankName != null ? customerBankName.hashCode() : 0);
        result = 31 * result + (tmgBankName != null ? tmgBankName.hashCode() : 0);
        result = 31 * result + (tmgBankAccount != null ? tmgBankAccount.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (imageContentType != null ? imageContentType.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}


