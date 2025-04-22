package com.Collection.Cashering;

import com.Database.DatabaseConnection;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteStockCashering extends DatabaseConnection {

    private final static Scanner sc = new Scanner(System.in);
    private final static ViewStockCasheringDashBoard q = new ViewStockCasheringDashBoard();

    private void deleteStockCashering() throws SQLException {
        System.out.print("Enter item code that you want to archieve or restore: ");
        String itemCode = sc.nextLine();

        System.out.print("Enter 1 for achieve or 0 for restore: ");
        int choice = 0;
        boolean validInput = true;
        while (validInput) {
            try {
                choice = sc.nextInt();
                if (choice < 0 || choice > 1) {
                    System.out.println("Input the number from 1 achieve or 0 restore");
                    System.out.print("Enter 1 for achieve or 0 for restore: ");
                } else {
                    validInput = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid! Input the number from 1 achieve or 0 restore");
                System.out.print("Enter 1 for achieve or 0 for restore: ");
                sc.nextLine();
            }
        }
        System.out.println("Your result: " + (choice == 1 ? "Archieve" : "Restore"));

        String query = "UPDATE tblcashering SET cashering_archieved = ? WHERE cashering_items_code = ?";
        try {
            dbConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, choice);
            preparedStatement.setString(2, itemCode);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item code " + itemCode + " updated successfully");
                sc.nextLine();
                deleteStockCasheringDashboard();
            } else {
                System.out.println("No matching item code found\n");
                sc.nextLine();
                deleteStockCasheringDashboard();
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            connection.close();
        }
    }

    private void deleteStockCasheringDashboard() throws SQLException {
        System.out.println("What do you want?");
        System.out.println("    [1] Delete");
        System.out.println("    [2] Back");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                deleteStockCashering();
                break;
            case "2":
                deleteStockCasheringDashboardBack();
                break;
            default:
                System.out.println("Invalid!");
                deleteStockCasheringDashboard();
        }
    }

    public void deleteStockCasheringInput() throws SQLException {
        while (true) {
            System.out.println("Are your sure to delete stock? \n\t[Y] Yes \n\t[N] No");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
                deleteStockCasheringDashboard();
                System.exit(0);
            }
            if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
                q.dashboardViewStockCashering();
                System.exit(0);
            } else {
                System.out.println("Invalid");
                deleteStockCasheringInput();
            }
        }
    }

    private void deleteStockCasheringDashboardBack() throws SQLException {
        System.out.println("Are you sure to exit? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            q.dashboardViewStockCashering();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            deleteStockCasheringDashboard();
        } else {
            System.out.println("Invalid!");
            deleteStockCasheringDashboardBack();
        }
    }
}
