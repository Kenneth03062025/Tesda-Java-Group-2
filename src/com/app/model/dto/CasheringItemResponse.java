package com.app.model.dto;

import com.app.model.Cashering;

public class CasheringItemResponse {
    private String status;

    private String message;

    private Cashering cashering;

    public CasheringItemResponse() {
    }

    public CasheringItemResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public CasheringItemResponse(String status, String message, Cashering cashering) {
        this.status = status;
        this.message = message;
        this.cashering = cashering;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Cashering getCashering() {
        return cashering;
    }

    public void setCashering(Cashering cashering) {
        this.cashering = cashering;
    }
}
