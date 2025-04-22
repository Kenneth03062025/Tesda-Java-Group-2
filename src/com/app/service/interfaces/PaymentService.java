package com.app.service.interfaces;

import com.app.model.Order;
import com.app.model.OrderItem;
import com.app.model.Payment;
import com.app.model.Response;
import com.app.model.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    Response<?> createPayment(Payment payment);

    PaymentResponse placeOrderPayment(List<OrderItem> orderItems, String casheringNumber);
}
