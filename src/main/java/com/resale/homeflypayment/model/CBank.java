package com.resale.homeflypayment.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "c_bank", schema = "vso_dev_db", catalog = "")
public class CBank {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "bank_name")
    private String bankName;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Basic
    @Column(name = "last_change_user_id")
    private int lastChangeUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getLastChangeUserId() {
        return lastChangeUserId;
    }

    public void setLastChangeUserId(int lastChangeUserId) {
        this.lastChangeUserId = lastChangeUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CBank cBank = (CBank) o;

        if (id != cBank.id) return false;
        if (lastChangeUserId != cBank.lastChangeUserId) return false;
        if (bankName != null ? !bankName.equals(cBank.bankName) : cBank.bankName != null) return false;
        if (createdAt != null ? !createdAt.equals(cBank.createdAt) : cBank.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(cBank.updatedAt) : cBank.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bankName != null ? bankName.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + lastChangeUserId;
        return result;
    }
}


