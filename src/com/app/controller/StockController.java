package com.app.controller;

import com.app.model.Response;
import com.app.model.Stocks;
import com.app.model.dto.ListOfItemsAvailableResponse;
import com.app.model.dto.ListOfStocksResponse;
import com.app.service.implementations.StockServiceImpl;
import com.app.service.interfaces.StockService;

import java.util.List;

public class StockController {

    static StockServiceImpl sImpl = new StockServiceImpl();
    public static ListOfStocksResponse getStocksbyCashering(String casheringNumber){
        ListOfStocksResponse res = sImpl.getStocksbyCashering(casheringNumber);
        return res;
    }

    public static Response<Stocks> addItemsToCashering(List<Stocks> stocks){
        Response<Stocks> res = sImpl.addItemsToCashering(stocks);
        return res;
    }

    public static ListOfItemsAvailableResponse getAvailableItems(String casheringNumber){
        ListOfItemsAvailableResponse res = sImpl.getAvailableItems(casheringNumber);
        return res;
    }
}
