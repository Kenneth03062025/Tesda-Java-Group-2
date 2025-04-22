package com.app.model;

import java.time.LocalDateTime;

public class Payment {

    private int id;
    private String paymentNumber;
    private String orderNumber;
    private double total;

    private LocalDateTime createdAt;

    private String status;

    private LocalDateTime voidedAt;

    public Payment() {
    }

    public Payment(int id, String paymentNumber, String orderNumber, double total, LocalDateTime createdAt, String status, LocalDateTime voidedAt) {
        this.id = id;
        this.paymentNumber = paymentNumber;
        this.orderNumber = orderNumber;
        this.total = total;
        this.createdAt = createdAt;
        this.status = status;
        this.voidedAt = voidedAt;
    }

    public Payment(String orderNumber, double total) {
        this.orderNumber = orderNumber;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getVoidedAt() {
        return voidedAt;
    }

    public void setVoidedAt(LocalDateTime voidedAt) {
        this.voidedAt = voidedAt;
    }
}
