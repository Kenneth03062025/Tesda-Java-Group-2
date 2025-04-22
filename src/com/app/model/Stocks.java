package com.app.model;

public class Stocks {

    private int id;

    private String casheringNumber;

    private String itemNumber;

    private String itemName;

    private int quantity;

    private int itemSold;

    public Stocks(String casheringNumber, String itemNumber, String itemName , int quantity) {
        this.casheringNumber = casheringNumber;
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.itemName = itemName;
    }

    public Stocks(int id, String casheringNumber, String itemNumber, String itemName, int quantity, int itemSold) {
        this.id = id;
        this.casheringNumber = casheringNumber;
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemSold = itemSold;
    }

    public Stocks(String casheringNumber, String itemNumber, int quantity) {
        this.casheringNumber = casheringNumber;
        this.itemNumber = itemNumber;
        this.quantity = quantity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCasheringNumber() {
        return casheringNumber;
    }

    public void setCasheringNumber(String scheduleNumber) {
        this.casheringNumber = casheringNumber;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemSold() {
        return itemSold;
    }

    public void setItemSold(int itemSold) {
        this.itemSold = itemSold;
    }
}
