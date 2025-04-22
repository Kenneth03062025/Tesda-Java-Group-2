package com.app.controller;

import com.app.model.dto.ListOfOrderItemsResponse;
import com.app.service.implementations.OrderItemServiceImpl;
import com.app.service.implementations.OrderServiceImpl;

public class OrderItemController {

    static OrderItemServiceImpl oImpl = new OrderItemServiceImpl();
    public static ListOfOrderItemsResponse showOrderItems(String orderNumber){
        ListOfOrderItemsResponse res = oImpl.getOrderItems(orderNumber);

        System.out.println(res.getStatus());
//        System.out.println(res.getOrderItems().get(1).getItemNumber());
        return res;

    }
}
