package com.app.service.interfaces;

import com.app.model.Response;
import com.app.model.Stocks;
import com.app.model.dto.ListOfItemsAvailableResponse;
import com.app.model.dto.ListOfStocksResponse;

import java.util.List;

public interface StockService {
    ListOfStocksResponse getStocksbyCashering(String casheringNumber);

    Response<Stocks> addItemsToCashering(List<Stocks> stocks);

    ListOfItemsAvailableResponse getAvailableItems(String casheringNumber);
}
