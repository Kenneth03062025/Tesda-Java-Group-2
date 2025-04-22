package com.app.service.interfaces;

import com.app.model.Response;
import com.app.model.dto.ListOfOrderItemsResponse;

public interface OrderItemService {
    ListOfOrderItemsResponse getOrderItems(String orderNumber);
}
