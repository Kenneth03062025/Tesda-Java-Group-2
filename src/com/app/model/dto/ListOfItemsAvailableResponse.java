package com.app.model.dto;


import com.app.model.ItemAvailable;

import java.util.List;

public class ListOfItemsAvailableResponse {
    private String status;

    private String message;

    private List<ItemAvailable> itemsAvailable;

    public ListOfItemsAvailableResponse() {
    }

    public ListOfItemsAvailableResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ListOfItemsAvailableResponse(String status, String message, List<ItemAvailable> itemsAvailable) {
        this.status = status;
        this.message = message;
        this.itemsAvailable = itemsAvailable;
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

    public List<ItemAvailable> getItemsAvailable() {
        return itemsAvailable;
    }

    public void setItemsAvailable(List<ItemAvailable> itemsAvailable) {
        this.itemsAvailable = itemsAvailable;
    }
}
