package com.app.model.dto;

import com.app.model.OrderItem;
import com.app.model.Payment;
import com.app.model.Stocks;

import java.util.List;

public class PaymentResponse {

    private String status;
    private String message;

    private Payment payment;

    private List<OrderItem> orderItems;

    public PaymentResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public PaymentResponse(String status, String message, Payment payment, List<OrderItem> orderItems) {
        this.status = status;
        this.message = message;
        this.payment = payment;
        this.orderItems = orderItems;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
