package com.app.model;

public class OrderItem {
    private int id;

    private String orderNumber;

    private String itemNumber;

    private String itemName;

    private int quantity;

    private double itemTotal;

    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderItem(String itemNumber, int quantity, double price) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(int id, String orderNumber, String itemNumber, int quantity, double itemTotal) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    public OrderItem(int id, String itemNumber, String itemName, int quantity, double price, double itemTotal) {
        this.id = id;
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
        this.price = price;
    }

    public OrderItem(String orderNumber, String itemNumber, int quantity) {
        this.orderNumber = orderNumber;
        this.itemNumber = itemNumber;
        this.quantity = quantity;
    }

    public OrderItem(String itemNumber, int quantity) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
    }

    public OrderItem() {
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

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemTotal(double v) {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
