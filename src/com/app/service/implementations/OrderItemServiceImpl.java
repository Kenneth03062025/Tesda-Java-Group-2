package com.app.service.implementations;

import com.app.model.OrderItem;
import com.app.model.Response;
import com.app.model.dto.ListOfOrderItemsResponse;
import com.app.service.interfaces.OrderItemService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.app.util.FunctionWithTryCatch.tryCatchAnyResponseExecute;

public class OrderItemServiceImpl implements OrderItemService {
    @Override
    public ListOfOrderItemsResponse getOrderItems(String orderNumber) {
//        System.out.println("Implementation " + orderNumber);

        String query = "SELECT order_items.id, items.item_no, items.item_name, " +
                "order_items.quantity, items.price, order_items.item_total, " +
                "order_items.order_no FROM order_items " +
                "JOIN items ON order_items.item_no = items.item_no " +
                "WHERE order_items.order_no='" + orderNumber +"' ";

        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {

            List<OrderItem> ordersItems = new ArrayList<>();
            PreparedStatement preparedStatement;
            ResultSet resultSet;

            Statement stmt = con.getConnection().createStatement();
            resultSet = stmt.executeQuery(query);
//            System.out.println(resultSet.getInt(1));

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String itemNumber = resultSet.getString(2);
                String itemName = resultSet.getString(3);
                int quantity = resultSet.getInt(4);
                double price = resultSet.getDouble(5);
                double item_total = resultSet.getDouble(6);
                System.out.println(itemName);

                OrderItem item = new OrderItem(id,itemNumber,itemName,quantity,price,item_total);
                ordersItems.add(item);

            }
            Response<OrderItem> res = new Response<>("success","Successfully fetch Orders Items",ordersItems);
            return res ;
        };

        Response<?> res = tryCatchAnyResponseExecute(con,func);
//        System.out.println(res.getStatus());

        ListOfOrderItemsResponse items = new ListOfOrderItemsResponse();
        items.setStatus(res.getStatus());
        items.setMessage(res.getMessage());
        if(res.getStatus().equals("success")){
            List<OrderItem> orderItems = new ArrayList<>();

            for (Object obj: res.getListOfItems()) {
                OrderItem orderItem = (OrderItem) obj;
                System.out.println(orderItem.getItemNumber());
                orderItems.add(orderItem);
            }
            items.setOrderItems(orderItems);
        }

        return items;
    }
}
