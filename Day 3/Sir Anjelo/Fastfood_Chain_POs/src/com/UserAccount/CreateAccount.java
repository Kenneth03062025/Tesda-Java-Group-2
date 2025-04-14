package com.UserAccount;

import java.util.Scanner;
import com.Database.DatabaseConnection;
import java.sql.SQLException;

public class CreateAccount extends DatabaseConnection {

    private final static Scanner sc = new Scanner(System.in);

    public void createUserAccount() throws SQLException {
        System.out.println("\nCreate account");

        System.out.print("Enter your firstname: ");
        String firstName = sc.nextLine();

        System.out.print("Enter your lastname: ");
        String lastName = sc.nextLine();

        System.out.print("Enter your middle name: ");
        String middleName = sc.nextLine();

        System.out.print("Enter your username: ");
        String userName = sc.nextLine();

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        String query = "INSERT INTO tblaccount (`user_firstname`, `user_lastname`, `user_middle_name`, `user_name`, `user_password`) VALUES (?,?,?,?,?)";

        try {
            dbConnection();
            preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, middleName);
            preparedStatement.setString(4, userName);
            preparedStatement.setString(5, password);

            if (firstName.isEmpty() || lastName.isEmpty() || middleName.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                String errorMessage = "Fields cannot be empty";
                System.out.println(errorMessage);
                createUserAccount();
            } else {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnectDB();
        }
    }
}
