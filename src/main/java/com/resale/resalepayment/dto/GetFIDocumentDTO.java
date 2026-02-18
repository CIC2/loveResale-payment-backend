package com.resale.resalepayment.dto;

import lombok.Data;

@Data
public class GetFIDocumentDTO {
    private String msgType;
    private String message;
    private String message2;
    private String sapFIDocument;
    private String status;

    public String getSapFIDocument() {
        return sapFIDocument;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public void setSapFIDocument(String sapFIDocument) {
        this.sapFIDocument = sapFIDocument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


