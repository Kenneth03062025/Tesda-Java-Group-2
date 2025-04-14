package com.Collection.Payment;

public class MVCUpdatePayment {
    private String paymentNumber;
    private String orderNumber;
    private double total;

    public MVCUpdatePayment(String paymentNumber, String orderNumber, double total) {
        this.paymentNumber = paymentNumber;
        this.orderNumber = orderNumber;
        this.total = total;
    }
    
    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
