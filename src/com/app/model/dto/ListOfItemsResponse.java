package com.app.model.dto;

import com.app.model.Item;

import java.util.List;

public class ListOfItemsResponse {
    private String status;

    private String message;

    private List<Item> items;

    public ListOfItemsResponse() {
    }

    public ListOfItemsResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public ListOfItemsResponse(String status, String message, List<Item> items) {
        this.status = status;
        this.message = message;
        this.items = items;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
