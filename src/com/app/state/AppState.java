package com.app.state;

import com.app.model.*;

import java.util.List;

public class AppState {

    public static Cashering cashering;
    public static List<Stocks> stocksList;
    public static User user;
    public static Item item = new Item();

    public static Order order = new Order();
}
