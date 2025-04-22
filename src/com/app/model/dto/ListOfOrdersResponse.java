package com.app.model.dto;

import com.app.model.Order;

import java.util.List;

public class ListOfOrdersResponse {
    private String status;

    private String message;

    private List<Order> orders;

    public ListOfOrdersResponse() {
    }

    public ListOfOrdersResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ListOfOrdersResponse(String status, String message, List<Order> orders) {
        this.status = status;
        this.message = message;
        this.orders = orders;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
