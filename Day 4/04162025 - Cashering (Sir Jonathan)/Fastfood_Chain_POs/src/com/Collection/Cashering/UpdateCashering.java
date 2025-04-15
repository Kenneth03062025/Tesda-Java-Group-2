package com.Collection.Cashering;

import com.Database.DatabaseConnection;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateCashering extends DatabaseConnection {

    private final static Scanner sc = new Scanner(System.in);
    private final static CasheringDashboard q = new CasheringDashboard();

    private void updateCashering() throws SQLException {
        System.out.print("Enter your item code: ");
        String itemCode = sc.nextLine();

        System.out.print("Enter your quantity: ");
        int quantity = 0;

        while (true) {
            try {
                quantity = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid!");
                System.out.print("Enter your quantity: ");
                sc.nextLine();
            }
        }

        String query = "UPDATE tblcashering SET cashering_quantity = ? WHERE cashering_items_code = ?";
        try {
            dbConnection();
            preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, itemCode);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Item Code " + itemCode + " Updated Successfully!");
                sc.nextLine();
                additionalUpdatePayment();
            } else {
                System.out.println("No record found with Item Code " + itemCode);
                sc.nextLine();
                additionalUpdatePayment();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            disconnectDB();
        }
    }

    private void updateCasheringDashboard() throws SQLException {
        System.out.println("What do you want? ");
        System.out.println("    [1] Update Cashering");
        System.out.println("    [2] Back");

        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                updateCashering();
                break;
            case "2":
                backCasheringDashboard();
                break;
            default:
                System.out.println("Invalid!");
                updateCasheringDashboard();
        }
    }

    public void inputUpdateCashering() throws SQLException {
        System.out.println("Are you sure to update cashering dashboard? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            updateCasheringDashboard();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            q.casheringDashboard();
            System.exit(0);
        } else {
            System.out.println("Invalid!");
            inputUpdateCashering();
        }
    }

    private void backCasheringDashboard() throws SQLException {

        System.out.println("Are you sure to exit? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            q.casheringDashboard();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            updateCasheringDashboard();
            System.exit(0);
        } else {
            System.out.println("Invalid!");
            backCasheringDashboard();
        }
    }

    private void additionalUpdatePayment() throws SQLException {
        System.out.println("Do you want to update cashering? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            updateCashering();
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            updateCasheringDashboard();
            System.exit(0);
        } else {
            System.out.println("Invalid!");
            additionalUpdatePayment();
        }
    }
}
