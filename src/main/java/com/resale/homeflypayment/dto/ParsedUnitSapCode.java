package com.resale.homeflypayment.dto;

import lombok.Data;

@Data
public class ParsedUnitSapCode {
    private String companyCode;
    private String businessEntity;
    private String unit;

    public ParsedUnitSapCode(String companyCode, String businessEntity, String unit) {
        this.companyCode = companyCode;
        this.businessEntity = businessEntity;
        this.unit = unit;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}


