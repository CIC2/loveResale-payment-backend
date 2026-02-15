package com.resale.homeflypayment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "c_bank_keys", schema = "vso_dev_db", catalog = "")
public class CBankKeys {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "bank_id")
    private int bankId;
    @Basic
    @Column(name = "project_id")
    private int projectId;
    @Basic
    @Column(name = "access_key")
    private String accessKey;
    @Basic
    @Column(name = "profile_id")
    private String profileId;
    @Basic
    @Column(name = "secret_key")
    private String secretKey;
    @Basic
    @Column(name = "extra_key")
    private String extraKey;
    @Basic
    @Column(name = "last_change_user_id")
    private int lastChangeUserId;
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

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getExtraKey() {
        return extraKey;
    }

    public void setExtraKey(String extraKey) {
        this.extraKey = extraKey;
    }

    public int getLastChangeUserId() {
        return lastChangeUserId;
    }

    public void setLastChangeUserId(int lastChangeUserId) {
        this.lastChangeUserId = lastChangeUserId;
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
}


