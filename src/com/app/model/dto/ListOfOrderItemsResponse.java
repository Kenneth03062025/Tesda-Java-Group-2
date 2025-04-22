package com.app.model.dto;

import com.app.model.Item;
import com.app.model.OrderItem;

import java.util.List;

public class ListOfOrderItemsResponse {
    private String status;

    private String message;

    private List<OrderItem> orderItems;

    public ListOfOrderItemsResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ListOfOrderItemsResponse(String status, String message, List<OrderItem> orderItems) {
        this.status = status;
        this.message = message;
        this.orderItems = orderItems;
    }

    public ListOfOrderItemsResponse() {

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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
