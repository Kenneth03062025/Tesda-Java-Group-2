package com.UserAccount;

import com.Database.DatabaseConnection;
import com.View.Dashboard;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginAccount extends DatabaseConnection {

    private final static Scanner sc = new Scanner(System.in);

    public void loginUserAccount() throws SQLException {
        System.out.print("Enter your username: ");
        String userLoginName = sc.nextLine();

        System.out.print("Enter your password: ");
        String userLoginPassword = sc.nextLine();

        String query = "SELECT user_name, user_password from tblaccount WHERE user_name = ? AND user_password = ?"; //AND archieved = 0

        try {
            dbConnection();
            preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, userLoginName);
            preparedStatement.setString(2, userLoginPassword);

            resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Login Successfully");
                    Dashboard d = new Dashboard();
                    d.dashboardSystem();
                    d.dashboardOption();
                } else {
                    System.out.println("Login Failed");
                    loginUserAccount();
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnectDB();
        }
    }
}
