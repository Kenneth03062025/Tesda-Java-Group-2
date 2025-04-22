package com.app.service.implementations;

import com.app.model.*;
import com.app.model.dto.PaymentResponse;
import com.app.service.interfaces.PaymentService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import static com.app.util.FunctionWithTryCatch.tryCatchTransactionalExecute;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public Response<?> createPayment(Payment payment) {
        String query1 = "Select identification_prefix,current_number FROM setup WHERE id=?";

        //Order query String

//        String query1 = "Select identification_prefix,current_number FROM setup WHERE id=?";
//        String query2 = "INSERT INTO orders (order_no, cashering_no, placeAt, status)"
//                + "VALUES(?,?,?,?)";
//        String query3 = "INSERT INTO order_items (order_no,item_no,quantity,item_total) VALUES(?,?,?,?)";
//        String query4 = "UPDATE setup SET current_number = ? WHERE id=?";


        String query2 = "INSERT INTO payments (payment_number, order_number, total, createdAt, status)"
                + "VALUES(?,?,?,?,?)";
        String query3 = "UPDATE setup SET current_number = ? WHERE id=?";
        String query4 = "UPDATE orders SET status = 'paid' WHERE order_no=?";
        int setupId = 1;
        //orederSetUp ID
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

            PreparedStatement stmt2 = con.getConnection().prepareStatement(query2);
            stmt2.setString(1,setup.getNewNumber());
            stmt2.setString(2,payment.getOrderNumber());
            stmt2.setDouble(3, payment.getTotal());
            stmt2.setString(4, LocalDateTime.now().toString());
            stmt2.setString(5,"paid");

            stmt2.executeUpdate();

            PreparedStatement stmt3 = con.getConnection().prepareStatement(query3);
            stmt3.setInt(1,setup.getNextCurrentNumber());
            stmt3.setInt(2,setupId);
            stmt3.executeUpdate();

            PreparedStatement stmt4 = con.getConnection().prepareStatement(query4);
            stmt4.setString(1, payment.getOrderNumber());


            res.setStatus("success");
            res.setMessage("Successfully Saved Payment");
            res.setData(setup.getNewNumber());
            return res;
        };
        return tryCatchTransactionalExecute(con, func);
        //return null;
    }

    @Override
    public PaymentResponse placeOrderPayment(List<OrderItem> orderItems, String casheringNumber) {


//        String query4 = "UPDATE orders SET status = 'paid' WHERE order_no=?";
        int setupId = 1;
        //orederSetUp ID
        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<String> res = new Response<>();
//            Setup setup = null;
            String dateNow = LocalDateTime.now().toString();
            ResultSet rs = null;

            //SetUp String
            String numberQuery = "Select identification_prefix,current_number FROM setup WHERE id=1";
            String numberQuery2 = "Select identification_prefix,current_number FROM setup WHERE id=3";

            Setup setupPayment = null;
            Setup setupOrder = null;

            Statement setUpstmt1 = con.getConnection().createStatement();
            rs = setUpstmt1.executeQuery(numberQuery);

            while(rs.next()){
                String prefix = rs.getString(1);
                int currentNumber = rs.getInt(2);
                setupPayment = new Setup(prefix,currentNumber);
            }
            rs.close();

            Statement setUpstmt2 = con.getConnection().createStatement();
            rs = setUpstmt2.executeQuery(numberQuery2);

            while(rs.next()){
                String prefix = rs.getString(1);
                int currentNumber = rs.getInt(2);
                setupOrder = new Setup(prefix,currentNumber);
            }
            rs.close();

            //Create Order and update the stock table
            //Order query String

        String insertOrder = "INSERT INTO orders (order_no, cashering_no, placeAt, status) VALUES(?,?,?,?)";
        String inserOrderItem = "INSERT INTO order_items (order_no,item_no,quantity,item_total) VALUES(?,?,?,?)";

            String orderNewNum = setupOrder.getNewNumber();

            PreparedStatement orderStmt = con.getConnection().prepareStatement(insertOrder);
            orderStmt.setString(1,orderNewNum);
            orderStmt.setString(2,casheringNumber);
            orderStmt.setString(3, dateNow);
            orderStmt.setString(4,"paid");
            orderStmt.executeUpdate();



            PreparedStatement orderItemStmt = con.getConnection().prepareStatement(inserOrderItem);
            double paymentTotal = 0;
            for (OrderItem ite: orderItems) {
                orderItemStmt.setString(1,orderNewNum);
                orderItemStmt.setString(2,ite.getItemNumber());
                orderItemStmt.setInt(3,ite.getQuantity());
                orderItemStmt.setDouble(4,ite.getQuantity()*ite.getPrice());
                paymentTotal = paymentTotal + ite.getQuantity()*ite.getPrice();
                orderItemStmt.addBatch();
                orderItemStmt.executeUpdate();

                //Update Stock table
            }



            //Place the Payment
            String insertPayment = "INSERT INTO payments (payment_number, order_number, total, createdAt, status) VALUES(?,?,?,?,?)";

            PreparedStatement placePaymentstmt2 = con.getConnection().prepareStatement(insertPayment);
            placePaymentstmt2.setString(1,setupPayment.getNewNumber());
            placePaymentstmt2.setString(2,setupOrder.getNewNumber());
            placePaymentstmt2.setDouble(3, paymentTotal);
            placePaymentstmt2.setString(4, dateNow);
            placePaymentstmt2.setString(5, "paid");

            placePaymentstmt2.executeUpdate();

            //Update SetUp String
            String newnumberUpdate = "UPDATE setup SET current_number = ? WHERE id=?";

            //Update the setUp Table
            PreparedStatement paymentRow = con.getConnection().prepareStatement(newnumberUpdate);
            paymentRow.setInt(1,setupPayment.getNextCurrentNumber());
            paymentRow.setInt(2,1);
            paymentRow.executeUpdate();
            paymentRow.close();

            PreparedStatement OrderRow = con.getConnection().prepareStatement(newnumberUpdate);
            OrderRow.setInt(1,setupOrder.getNextCurrentNumber());
            OrderRow.setInt(2,3);
            OrderRow.executeUpdate();
            OrderRow.close();


            res.setStatus("success");
            res.setMessage("Successfully Saved Payment");
//            res.setData();
//            res.setData(setup.getNewNumber());
            return res;
        };
        Response<Payment> res = (Response<Payment>) tryCatchTransactionalExecute(con, func);

        PaymentResponse payRes = new PaymentResponse(res.getStatus(), res.getMessage());

        return payRes;
    }
}
