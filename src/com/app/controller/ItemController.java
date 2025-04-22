package com.app.controller;

import com.app.model.Item;
import com.app.model.Response;
import com.app.model.dto.ListOfItemsResponse;
import com.app.service.implementations.ItemServiceImpl;
import com.app.service.interfaces.ItemService;

import java.util.ArrayList;
import java.util.List;

public class ItemController {
    static ItemServiceImpl itm = new ItemServiceImpl();
//    ItemService itemService = new ItemService();
    public static Response<Item> saveItem (Item item){
        Response<Item> res = new Response<>();
        Response<Item> responseReturn = new Response<>();
        ItemServiceImpl itm = new ItemServiceImpl();
        res = itm.saveItem(item);
        System.out.println(res.getStatus());
        System.out.println(res.getMessage());
        return responseReturn = new Response<>(res.getStatus(),res.getMessage(),(Item) res.getData());

//        itemService.saveItem(item);
    }

    public static ListOfItemsResponse getAllItems(){
        ListOfItemsResponse res = itm.getAllItems();

        return res;

    }

    public static ListOfItemsResponse getActiveItems(){
        ListOfItemsResponse res = itm.getActiveItems();
        return res;
    }

    public static Response<?> updateAnItem(String itemNumber,Item item){
        Response<?> res = new Response<>();
//        ItemServiceImpl itm = new ItemServiceImpl();
//        Item item = new Item("Tangerin 2","Sour Fruit",500,"pcs");
        res = itm.updateItem(itemNumber,item);
        System.out.println(res.getStatus());
        System.out.println(res.getMessage());
        return res;
    }

    public static <Item> Response<Item> getAnItem(String item_no){
        Response<?> res;
        ItemServiceImpl itm = new ItemServiceImpl();
        res = itm.getAnItem(item_no);

        Response<Item> resToConsole = new Response<>();
        resToConsole.setStatus(res.getStatus());
        resToConsole.setMessage(res.getMessage());
        resToConsole.setData((Item) res.getData());
//        System.out.println(res.getStatus());
//        System.out.println(res.getMessage());
        Item item = (Item) res.getData();
//        System.out.println(item.getItem_name());
        return resToConsole;
    }

    public static Response<?> deleteAnItem(String itemNumber){
        Response<?> res = new Response<>();
        ItemServiceImpl itm = new ItemServiceImpl();
        res = itm.deleteAnItem(itemNumber);
        System.out.println(res.getStatus());
        System.out.println(res.getMessage());
        return res;
    }

    public static Response<?> setItemStatus(String itemNumber, String status){
//        Response<?> res = new Response<>();
        ItemServiceImpl itm = new ItemServiceImpl();
        Response<?> res = itm.setItemStatus(itemNumber,status);
        System.out.println(res.getStatus());
        System.out.println(res.getMessage());
        return res;
    }
}
