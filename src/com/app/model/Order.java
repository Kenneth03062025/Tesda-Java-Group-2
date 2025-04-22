package com.app.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private String orderNumber;

    private String casheringNumber;
    private LocalDateTime placeAt;

    private String status;

    public Order(int id, String orderNumber, String casheringNumber, LocalDateTime placeAt, String status) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.casheringNumber = casheringNumber;
        this.placeAt = placeAt;
        this.status = status;
    }

    public Order(int id, String orderNumber, String casheringNumber, String status) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.casheringNumber = casheringNumber;
        this.status = status;
    }

    public Order(String orderNumber, String scheduleNumber) {
        this.orderNumber = orderNumber;
        this.casheringNumber = casheringNumber;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCasheringNumber() {
        return casheringNumber;
    }

    public void setCasheringNumber(String casheringNumber) {
        this.casheringNumber = casheringNumber;
    }

    public LocalDateTime getPlaceAt() {
        return placeAt;
    }

    public void setPlaceAt(LocalDateTime placeAt) {
        this.placeAt = placeAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
