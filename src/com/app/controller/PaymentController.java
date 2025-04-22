package com.app.controller;

import com.app.model.Item;
import com.app.model.OrderItem;
import com.app.model.Payment;
import com.app.model.Response;
import com.app.model.dto.ListOfItemsAvailableResponse;
import com.app.model.dto.PaymentResponse;
import com.app.service.implementations.ItemServiceImpl;
import com.app.service.implementations.PaymentServiceImpl;

import java.util.List;

public class PaymentController {

    static PaymentServiceImpl pmt = new PaymentServiceImpl();

    public static Response<String> savePayment (Payment payment){
        Response<String> res = new Response<>();
//        Response<String> responseReturn = new Response<>();

        Response<?> serviceRes = pmt.createPayment(payment);
//        res = itm.saveItem(item);
//        System.out.println(res.getStatus());
//        System.out.println(res.getMessage());
        res.setStatus(serviceRes.getStatus());
        res.setMessage(serviceRes.getMessage());
        res.setData((String) res.getData());
        return res;
//        if(serviceRes.getStatus().equals("failed")){
//            return (Response<String>) res;
//        }
//        return (Response<String>) (res = new Response<>(res.getStatus(),res.getMessage(),(String) res.getData()));
    }

    public static PaymentResponse placeOrderPayment(List<OrderItem> orderItems, String casheringNumber){
        PaymentResponse res = pmt.placeOrderPayment(orderItems,casheringNumber);
        return res;
    }
}
