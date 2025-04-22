package com.app.model;

import java.util.List;

public class Response<T> {

    private String status;

    private String message;

    private T data;

    public Response(String status, String message, String dataString) {
        this.status = status;
        this.message = message;
        this.dataString = dataString;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    private String dataString;

    private List<?> listOfItems;

    public Response(String status, String message, List<T> listOfItems) {
        this.status = status;
        this.message = message;
        this.listOfItems = listOfItems;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response() {
    }

    public Response(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<?> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(List<?> listOfItems) {
        this.listOfItems = listOfItems;
    }
}
