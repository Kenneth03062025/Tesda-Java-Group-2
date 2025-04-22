package com.app.service.implementations;

import com.app.model.Item;
import com.app.model.Response;
import com.app.model.Setup;
import com.app.model.User;
import com.app.service.interfaces.AuthService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import static com.app.util.FunctionWithTryCatch.tryCatchAnyResponseExecute;
import static com.app.util.FunctionWithTryCatch.tryCatchTransactionalExecute;

public class AuthServiceImpl implements AuthService {


    @Override
    public Response<?> createUserAccount(User user) {
        DBConnection con = new DBConnection();
        String query1 = "Select identification_prefix,current_number FROM setup WHERE id=?";
        String query2 = "INSERT INTO users(user_no, first_name, last_name, middle_name, user_name, password, role)"
                + "VALUES(?,?,?,?,?,?,?)";
        String query3 = "UPDATE setup SET current_number = ? WHERE id=?";
        int setupId = 2;
        RiskyFunctionAnyType func = () -> {
            Response<Item> res = new Response<>();
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
            String setUpNewUser = setup.getNewNumber();

            PreparedStatement stmt2 = con.getConnection().prepareStatement(query2);
            stmt2.setString(1,setUpNewUser);
            stmt2.setString(2, user.getFirstName());
            stmt2.setString(3, user.getLastName());
            stmt2.setString(4, user.getMiddleName());
            stmt2.setString(5, user.getUserName());
            stmt2.setString(6,user.getPassword());
            stmt2.setString(7,"cashier");
            stmt2.executeUpdate();

            PreparedStatement stmt3 = con.getConnection().prepareStatement(query3);
            stmt3.setInt(1,setup.getNextCurrentNumber());
            stmt3.setInt(2,setupId);
            stmt3.executeUpdate();


            res.setStatus("success");
            res.setMessage("Successfully Created New User");
            res.setDataString(setUpNewUser);
            return res;
        };

        return tryCatchTransactionalExecute(con,func);
    }

    @Override
    public Response<User> logInUserAccount(User logInUser) {
        DBConnection con = new DBConnection();
        String query1 = "SELECT * from users WHERE user_name = '" + logInUser.getUserName() + "' AND password = '" + logInUser.getPassword() + "'";
        RiskyFunctionAnyType func = () -> {
            Response<User> res = new Response<>();
            //User user = new User();

            Statement stmt = con.getConnection().createStatement();
            ResultSet resultSet = stmt.executeQuery(query1);


            while(resultSet.next()){
//                int id = resultSet.getInt(1);
                String user_no = resultSet.getString(2) ;
                String first_name = resultSet.getString(3);
                String last_name = resultSet.getString(4);
                String middle_name = resultSet.getString(5);
                String user_name = resultSet.getString(6);
                String role = resultSet.getString(8);
                System.out.println(user_no);

//                User user = new User(user_no,first_name,last_name,middle_name,user_name,role);
                res.setData(new User(user_no,first_name,last_name,middle_name,user_name,role));
            }

            res.setStatus("success");
            res.setMessage("Login Successfully");

            return res;
        };

        return (Response<User>) tryCatchAnyResponseExecute(con,func);
    }
}
