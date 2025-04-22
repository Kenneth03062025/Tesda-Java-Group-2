package com.app.model.dto;

import com.app.model.Stocks;

import java.util.List;

public class ListOfStocksResponse {
    private String status;
    private String message;

    private List<Stocks> stocks;

    public ListOfStocksResponse() {
    }

    public ListOfStocksResponse(String status, String message, List<Stocks> stocks) {
        this.status = status;
        this.message = message;
        this.stocks = stocks;
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

    public List<Stocks> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stocks> stocks) {
        this.stocks = stocks;
    }
}
