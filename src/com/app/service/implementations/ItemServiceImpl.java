package com.app.service.implementations;

import com.app.model.Item;
import com.app.model.Response;
import com.app.model.Setup;
import com.app.model.dto.ListOfItemsResponse;
import com.app.service.interfaces.ItemService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.app.util.FunctionWithTryCatch.tryCatchAnyResponseExecute;
import static com.app.util.FunctionWithTryCatch.tryCatchTransactionalExecute;


public class ItemServiceImpl implements ItemService {
    @Override
    public Response<Item> saveItem(Item item) {
        String query1 = "INSERT INTO items(item_no, item_name, item_description, price, unit, status)"
                + "VALUES(?,?,?,?,?,?)";
        String query2 = "Select identification_prefix,current_number FROM setup WHERE id=?";
        String query3 = "UPDATE setup SET current_number = ? WHERE id=?";
        int setupId = 4;
        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<Item> res = new Response<>();
            Setup setup = null;
            PreparedStatement stmt1;
            ResultSet rs;
            stmt1 = con.getConnection().prepareStatement(query2);
            stmt1.setInt(1,setupId);
            rs = stmt1.executeQuery();

            while(rs.next()){
                String prefix = rs.getString(1);
                int currentNumber = rs.getInt(2);
                setup = new Setup(prefix,currentNumber);
            }

            PreparedStatement stmt2 = con.getConnection().prepareStatement(query1);
            stmt2.setString(1,setup.getNewNumber());
            stmt2.setString(2,item.getItem_name());
            stmt2.setString(3, item.getItem_description());
            stmt2.setDouble(4,item.getPrice());
            stmt2.setString(5,item.getUnit());
            stmt2.setString(6,"inactive");
            stmt2.executeUpdate();

            PreparedStatement stmt3 = con.getConnection().prepareStatement(query3);
            stmt3.setInt(1,setup.getNextCurrentNumber());
            stmt3.setInt(2,setupId);
            stmt3.executeUpdate();


            res.setStatus("success");
            res.setMessage("Successfully Item Saved");
            return res;
        };
        return (Response<Item>) tryCatchTransactionalExecute(con, func);

    }

    public ListOfItemsResponse getAllItems() {
        String query = "SELECT * FROM items";
        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<Item> res = new Response<>();
            List<Item> items = new ArrayList<>();
            Statement statement;
            ResultSet resultSet;
            statement = con.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String item_no = resultSet.getString(2);
                String item_name = resultSet.getString(3);
                String item_description = resultSet.getString(4);
                double price = resultSet.getDouble(5);
                String unit = resultSet.getString(6);
                String status = resultSet.getString(7);
                Item item = new Item(id, item_no, item_name, item_description, price, unit, status);
                items.add(item);
            }

            return res = new Response<>("success","Successfully fetch Items",items);
        };

        Response<?> res = tryCatchAnyResponseExecute(con,func);
        ListOfItemsResponse itemsResponse = new ListOfItemsResponse();
        itemsResponse.setStatus(res.getStatus());
        itemsResponse.setMessage(res.getMessage());
        if(res.getStatus().equals("success")){
            List<Item> items = new ArrayList<>();
            //itemsResponse.
            for (Object obj: res.getListOfItems()) {
                Item item = (Item) obj;
                items.add(item);
            }
            itemsResponse.setItems(items);
        }

        return itemsResponse;
//        return (Response<Item>) tryCatchAnyResponseExecute(con,func);
    }

    @Override
    public ListOfItemsResponse getActiveItems() {
        String query = "SELECT * FROM items WHERE status='active'";
        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<Item> res = new Response<>();
            List<Item> items = new ArrayList<>();
            Statement statement;
            ResultSet resultSet;
            statement = con.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String item_no = resultSet.getString(2);
                String item_name = resultSet.getString(3);
                String item_description = resultSet.getString(4);
                double price = resultSet.getDouble(5);
                String unit = resultSet.getString(6);
                String status = resultSet.getString(7);
//                Item item = new Item(id, item_no, item_name, item_description, price, unit, status);
                Item item = new Item(item_no,item_name,item_description,price,unit);
                items.add(item);
            }

            return res = new Response<>("success","Successfully fetch Items",items);
        };

        Response<?> res = tryCatchAnyResponseExecute(con,func);
        ListOfItemsResponse itemsResponse = new ListOfItemsResponse();
        itemsResponse.setStatus(res.getStatus());
        itemsResponse.setMessage(res.getMessage());
        if(res.getStatus().equals("success")){
            List<Item> items = new ArrayList<>();
            //itemsResponse.
            for (Object obj: res.getListOfItems()) {
                Item item = (Item) obj;
                items.add(item);
            }
            itemsResponse.setItems(items);
        }

        return itemsResponse;
    }

    @Override
    public Response<?> updateItem(String itemNumber, Item item) {
        String query = "UPDATE items SET item_name = ?, item_description = ?, price = ?, unit = ? WHERE item_no=?";
        DBConnection con = new DBConnection();
        RiskyFunctionAnyType func = () -> {
            Response<?> res = new Response<>();
            PreparedStatement preparedStatement;
            preparedStatement = con.getConnection().prepareStatement(query);
            preparedStatement.setString(1,item.getItem_name());
            preparedStatement.setString(2,item.getItem_description());
            preparedStatement.setDouble(3,item.getPrice());
            preparedStatement.setString(4,item.getUnit());
            preparedStatement.setString(5,itemNumber);
            preparedStatement.executeUpdate();
            return res = new Response<>("success","Successfully Updated an Item");
        };

        return tryCatchAnyResponseExecute(con,func);
    }

    @Override
    public Response<?> getAnItem(String itemNumber) {
        String query = "SELECT * FROM items WHERE item_no=?" ;
//        String query = "SELECT * FROM items WHERE id=?";
        DBConnection con = new DBConnection();
        RiskyFunctionAnyType func = () -> {
            Response<?> res = new Response<>();
            Item it = new Item();
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            preparedStatement = con.getConnection().prepareStatement(query);
            preparedStatement.setString(1, itemNumber);
            resultSet =  preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String item_no = resultSet.getString(2) ;
                String item_name = resultSet.getString(3);
                String item_description = resultSet.getString(4);
                double price = resultSet.getDouble(5);
                String unit = resultSet.getString(6);
                String status = resultSet.getString(7);
                System.out.println(id);

                it = new Item(id,item_no,item_name,item_description,price,unit,status);
            }
            //preparedStatement.executeUpdate();
            return res = new Response<>("success","Successfully Fetch an Item", it);
        };
        return tryCatchAnyResponseExecute(con,func);
    }

    @Override
    public Response<?> deleteAnItem(String itemNumber) {
        String query = "DELETE from items WHERE item_no=?" ;
        DBConnection con = new DBConnection();
        RiskyFunctionAnyType func = () -> {
            Response<?> res = new Response<>();
            PreparedStatement preparedStatement;
            preparedStatement = con.getConnection().prepareStatement(query);
            preparedStatement.setString(1, itemNumber);
            preparedStatement.executeUpdate();
            return res = new Response<>("success","Successfully Deleted an Item");
        };
        return tryCatchAnyResponseExecute(con,func);
    }

    @Override
    public Response<?> setItemStatus(String itemNumber, String status) {
        String query = "UPDATE items SET status = ? WHERE item_no=?";
        DBConnection con = new DBConnection();
        RiskyFunctionAnyType func = () -> {
            Response<?> res = new Response<>();
            PreparedStatement preparedStatement;
            preparedStatement = con.getConnection().prepareStatement(query);
            preparedStatement.setString(1,status);
            preparedStatement.setString(2,itemNumber);
            preparedStatement.executeUpdate();
            return res = new Response<>("success","Successfully Updated an Item Status");
        };

        return tryCatchAnyResponseExecute(con,func);

    }

}
