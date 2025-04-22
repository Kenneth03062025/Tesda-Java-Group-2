package com.app.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Cashering {
    private int id;

    private String date;

    private String operationNumber;

    private String userNumber;

    private String openAt;

    private String closeAt;

    public Cashering (String date, String operationNumber) {
        this.date = date;
        this.operationNumber = operationNumber;
    }

    public Cashering() {
    }

    public Cashering(int id , String operationNumber, String date, String userNumber, String openAt, String closeAt) {
        this.id = id;
        this.date = date;
        this.operationNumber = operationNumber;
        this.userNumber = userNumber;
        this.openAt = openAt;
        this.closeAt = closeAt;
    }

    public Cashering(String operationNumber, String date, String userNumber, String openAt, String closeAt) {
        this.date = date;
        this.operationNumber = operationNumber;
        this.userNumber = userNumber;
        this.openAt = openAt;
        this.closeAt = closeAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getOpenAt() {
        return openAt;
    }

    public void setOpenAt(String openAt) {
        this.openAt = openAt;
    }

    public String getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(String closeAt) {
        this.closeAt = closeAt;
    }
}
