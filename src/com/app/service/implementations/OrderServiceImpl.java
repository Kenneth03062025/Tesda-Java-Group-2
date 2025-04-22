package com.app.service.implementations;

import com.app.model.*;
import com.app.model.dto.ListOfItemsResponse;
import com.app.model.dto.ListOfOrdersResponse;
import com.app.service.interfaces.OrderService;
import com.app.state.Constants;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.app.util.FunctionWithTryCatch.tryCatchAnyResponseExecute;
import static com.app.util.FunctionWithTryCatch.tryCatchTransactionalExecute;

public class OrderServiceImpl implements OrderService {
    @Override
    public Response<?> createOrder(ArrayList<OrderItem> orderItems, String casheringNumber) {
        String query1 = "Select identification_prefix,current_number FROM setup WHERE id=?";
        String query2 = "INSERT INTO orders (order_no, cashering_no, placeAt, status)"
                + "VALUES(?,?,?,?)";
        String query3 = "INSERT INTO order_items (order_no,item_no,quantity,item_total) VALUES(?,?,?,?)";
        String query4 = "UPDATE setup SET current_number = ? WHERE id=?";
        int setupId = 3;

        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<String> res = new Response<>();
            Setup setup = null;
            PreparedStatement stmt1;
            ResultSet rs;
            stmt1 = con.getConnection().prepareStatement(query1);
            stmt1.setInt(1,setupId);
            rs = stmt1.executeQuery();

            while(rs.next()){
                String prefix = rs.getString(1);
                int currentNumber = rs.getInt(2);
                setup = new Setup(prefix,currentNumber);
            }

            String newNumber = setup.getNewNumber();

            PreparedStatement stmt2 = con.getConnection().prepareStatement(query2);
            stmt2.setString(1,newNumber);
            stmt2.setString(2,casheringNumber);
            stmt2.setString(3, LocalDateTime.now().toString());
            stmt2.setString(4,"created");
            stmt2.executeUpdate();

            PreparedStatement stmt3 = con.getConnection().prepareStatement(query3);
            for (OrderItem ite: orderItems) {
                stmt3.setString(1,setup.getNewNumber());
                stmt3.setString(2,ite.getItemNumber());
                stmt3.setInt(3,ite.getQuantity());
                stmt3.setDouble(4,ite.getQuantity()*ite.getPrice());
                stmt3.addBatch();
                stmt3.executeUpdate();

//                String itemSum = "SELECT stocks.cashering_no, stocks.item_no, order_items.order_no, SUM(order_items.quantity) from stocks " +
//                                "INNER JOIN order_items on stocks.item_no=order_items.item_no WHERE " +
//                        "(stocks.cashering_no ='" + casheringNumber + "' AND stocks.item_no='" + ite.getItemNumber() + "')";
//
//
//                Statement sumStmt = con.getConnection().createStatement();
//                ResultSet rsSum = sumStmt.executeQuery(itemSum);
//
//                if(rsSum.next()){
//                    int sumQuantity = rsSum.getInt(4);
//                    System.out.println(sumQuantity);
//                    String updateStocks = "UPDATE stocks set items_sold=? WHERE item_no='?' AND cashering_no='?'";
//                    PreparedStatement stockUpdate = con.getConnection().prepareStatement(updateStocks);
//                    stockUpdate.setInt(1,sumQuantity);
//                    stockUpdate.setString(2,ite.getItemNumber());
//                    stockUpdate.setString(3,casheringNumber);
//                    stmt3.addBatch();
//                    stmt3.executeUpdate();
//                }
//                rsSum.close();

            }

//            String itemSum = "SELECT stocks.cashering_no, stocks.item_no, order_items.order_no, SUM(order_items.quantity) from stocks " +
//                                "INNER JOIN order_items on stocks.item_no=order_items.item_no WHERE " +
//                        "(stocks.cashering_no =? AND stocks.item_no=?)";
//            PreparedStatement sumQuantity = con.getConnection().prepareStatement(itemSum);
//
//            for (OrderItem ite: orderItems) {
//                ResultSet rsSum = sumQuantity.executeQuery(itemSum);
//                sumQuantity.setString(1,casheringNumber);
//                sumQuantity.setString(2,ite.getItemNumber());
////                stmt3.setInt(3,ite.getQuantity());
//                sumQuantity.addBatch();
//                System.out.println(rsSum);
//            }

            PreparedStatement stmt4 = con.getConnection().prepareStatement(query4);
            stmt4.setInt(1,setup.getNextCurrentNumber());
            stmt4.setInt(2,setupId);
            stmt4.executeUpdate();


            res.setStatus("success");
            res.setMessage("Successfully Saved Payment");
            res.setDataString(newNumber);
            return res;
        };
        return tryCatchTransactionalExecute(con, func);
    }

    @Override
    public ListOfOrdersResponse getUnpaidOrders(String casheringNumber) {
        String query = "SELECT * FROM orders WHERE cashering_no = '" + casheringNumber +"' AND status='created'";
//        String query = "SELECT * FROM orders WHERE cashering_no=? ";
        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<Order> res = new Response<>();
            List<Order> orders = new ArrayList<>();
            PreparedStatement preparedStatement;
            ResultSet resultSet;

//            preparedStatement = con.getConnection().prepareStatement(query);
//            preparedStatement.setString(1, casheringNumber);
//            resultSet =  preparedStatement.executeQuery();
//            System.out.println(resultSet.next());
            Statement stmt = con.getConnection().createStatement();
            resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String order_no = resultSet.getString(2);
                String cashering_no = resultSet.getString(3);
//                LocalDateTime placeAt = LocalDateTime.parse(resultSet.getString(4), Constants.formatter);
//                LocalDateTime placeAt = resultSet.getDate(4);
                String status = resultSet.getString(5);
                Order order = new Order(id,order_no,cashering_no,status);
//                Item item = new Item(item_no,item_name,item_description,price,unit);
                orders.add(order);
            }

            return res = new Response<>("success","Successfully fetch Orders",orders);
        };

        Response<?> res = tryCatchAnyResponseExecute(con,func);
//        ListOfItemsResponse itemsResponse = new ListOfItemsResponse();
        ListOfOrdersResponse ordersResponse = new ListOfOrdersResponse();
        ordersResponse.setStatus(res.getStatus());
        ordersResponse.setMessage(res.getMessage());
        if(res.getStatus().equals("success")){
            List<Order> orders = new ArrayList<>();
            //itemsResponse.
            for (Object obj: res.getListOfItems()) {
                Order order = (Order) obj;
                orders.add(order);
            }
            ordersResponse.setOrders(orders);
        }

        return ordersResponse;
//        return null;
    }

    //getItemByOrderNumber
}
