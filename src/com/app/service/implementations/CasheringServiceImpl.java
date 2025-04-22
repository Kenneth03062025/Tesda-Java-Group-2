package com.app.service.implementations;

import com.app.model.*;
import com.app.model.dto.CasheringItemResponse;
import com.app.service.interfaces.CasheringService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.app.util.FunctionWithTryCatch.tryCatchAnyResponseExecute;
import static com.app.util.FunctionWithTryCatch.tryCatchTransactionalExecute;

public class CasheringServiceImpl implements CasheringService {

    @Override
    public Response<Cashering> createCashering() {
        String query = "INSERT INTO operations(operationNumber, date)"
                + "VALUES(?,?)";
        String query2 = "Select identification_prefix,current_number FROM setup WHERE id=?";
        String query3 = "UPDATE setup SET current_number = ? WHERE id=?";
        int setupId = 5;
        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<Cashering> res = new Response<>();
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

            PreparedStatement stmt2;
            String newNum =  setup.getNewNumber();
            String dateSTR = LocalDateTime.now().toString();
            stmt2 = con.getConnection().prepareStatement(query);
            stmt2.setString(1,newNum);
            stmt2.setString(2, dateSTR);
            stmt2.executeUpdate();

            PreparedStatement stmt3 = con.getConnection().prepareStatement(query3);
            stmt3.setInt(1,setup.getNextCurrentNumber());
            stmt3.setInt(2,setupId);
            stmt3.executeUpdate();

            Cashering createdCashering = new Cashering(newNum,dateSTR);

            res.setStatus("success");
            res.setMessage("Successfully Opened Operation");
            res.setData(createdCashering);
            res.setDataString(setup.getNewNumber());

            return res;
        };
        return (Response<Cashering>) tryCatchTransactionalExecute(con, func);
        //return null;
    }

    @Override
    public Response<Cashering> openCashering(String operationNumber) {
        String query = "UPDATE operations SET openAt = ? WHERE operationNumber=?";
        DBConnection con = new DBConnection();
        RiskyFunctionAnyType func = () -> {
            Response<?> res = new Response<>();
            PreparedStatement preparedStatement;
            preparedStatement = con.getConnection().prepareStatement(query);
            preparedStatement.setString(1,LocalDateTime.now().toString());
            preparedStatement.setString(2,operationNumber);
            preparedStatement.executeUpdate();
            return res = new Response<>("success","Successfully Opened Cashering");
        };

        return (Response<Cashering>) tryCatchAnyResponseExecute(con,func);
        //return null;
    }

    @Override
    public Response<Cashering> closeCashering(String operationNumber) {
        String query = "UPDATE operations SET closeAt = ? WHERE operationNumber=?";
        DBConnection con = new DBConnection();
        RiskyFunctionAnyType func = () -> {
            Response<?> res = new Response<>();
            PreparedStatement preparedStatement;
            preparedStatement = con.getConnection().prepareStatement(query);
            preparedStatement.setString(1,LocalDateTime.now().toString());
            preparedStatement.setString(2,operationNumber);
            preparedStatement.executeUpdate();
            return res = new Response<>("success","Successfully Closed Cashering");
        };

        return (Response<Cashering>) tryCatchAnyResponseExecute(con,func);
        //return null;
    }

    @Override
    public CasheringItemResponse getOpenCashering() {
        String query = "SELECT * FROM operations WHERE (openAt is NULL OR closeAt is NULL);" ;
//        String query = "SELECT * FROM items WHERE id=?";
        DBConnection con = new DBConnection();
        RiskyFunctionAnyType func = () -> {
            Cashering cashering = new Cashering();

            Statement statement;
            ResultSet resultSet;
            statement = con.getConnection().createStatement();
            resultSet =  statement.executeQuery(query);

            while(resultSet.next()){

                int id = resultSet.getInt(1);
                String operation_no = resultSet.getString(2) ;
                String date = resultSet.getString(3);
                String user_no = resultSet.getString(4);
                String openAt = resultSet.getString(5);
                String closeAt = resultSet.getString(6);

//                cashering = new Cashering(id,date,operation_no,user_no,openAt,closeAt);
                cashering = new Cashering(operation_no,date,user_no,openAt,closeAt);
            }

            //preparedStatement.executeUpdate();
            Response<Cashering> res = new Response<>("success","Successfully Fetch Cashering", cashering);
            return res;
        };
        Response<?> res = tryCatchAnyResponseExecute(con,func);
        CasheringItemResponse casheringItemResponse = new CasheringItemResponse(res.getStatus(), res.getMessage());
        if(res.getStatus().equals("success")){
            casheringItemResponse.setCashering((Cashering) res.getData());
        }
        return casheringItemResponse;
    }

    @Override
    public Response<?> addItemsToCashering(List<Stocks> stocks) {
        String query3 = "INSERT INTO order_items (cashering_no,item_no,quantity) VALUES(?,?,?)";
        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {

            Response<String> res = new Response<>();

            PreparedStatement stmt1 = con.getConnection().prepareStatement(query3);
            for (Stocks ite: stocks) {
                stmt1.setString(1,ite.getCasheringNumber());
                stmt1.setString(2,ite.getItemNumber());
                stmt1.setInt(3,ite.getQuantity());
                stmt1.addBatch();
                stmt1.executeUpdate();
            }
            res.setStatus("success");
            res.setMessage("Successfully Items to Cashering");
//            res.setData(setup.getNewNumber());
            return res;
        };

        return tryCatchTransactionalExecute(con, func);
    }

    //view stocks
}
