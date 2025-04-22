package com.app.service.interfaces;

import com.app.model.Item;
import com.app.model.Response;
import com.app.model.dto.ListOfItemsResponse;

public interface ItemService {

    Response<Item> saveItem(Item item);
    ListOfItemsResponse getAllItems();

    ListOfItemsResponse getActiveItems();

    Response<?> updateItem(String itemNumber, Item item);

    Response<?> getAnItem(String itemNumber);

    Response<?> deleteAnItem(String itemNumber);

    Response<?> setItemStatus(String itemNumber, String status);
}
