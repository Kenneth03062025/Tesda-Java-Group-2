package com.app.model;

public class ItemAvailable {
    private int id;

    private String casheringNumber;

    private String itemNumber;

    private String itemName;

    private double price;

    private int available;

    public ItemAvailable() {
    }

    public ItemAvailable(int id, String casheringNumber, String itemNumber, String itemName, double price, int available) {
        this.id = id;
        this.casheringNumber = casheringNumber;
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.price = price;
        this.available = available;
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

    public void setCasheringNumber(String casheringNumber) {
        this.casheringNumber = casheringNumber;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
