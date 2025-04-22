package com.app.model;

public class Item {
    private int id;
    private String item_no;
    private String item_name;

    private String item_description;
    private double price;
    private String unit;
    private String status;

    public Item(int id, String item_no, String item_name, String item_description, double price, String unit, String status) {
        this.id = id;
        this.item_no = item_no;
        this.item_name = item_name;
        this.item_description = item_description;
        this.price = price;
        this.unit = unit;
        this.status = status;
    }

    public Item(String item_no, String item_name, String item_description, double price, String unit) {
        this.item_no = item_no;
        this.item_name = item_name;
        this.item_description = item_description;
        this.price = price;
        this.unit = unit;
    }

    public Item(String item_name, String item_description, double price, String unit) {
        this.item_name = item_name;
        this.item_description = item_description;
        this.price = price;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_no() {
        return item_no;
    }

    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item() {
    }


}
