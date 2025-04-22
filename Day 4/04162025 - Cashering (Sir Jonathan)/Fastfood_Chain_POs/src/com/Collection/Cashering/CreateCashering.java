package com.Collection.Cashering;

import com.Database.DatabaseConnection;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateCashering extends DatabaseConnection {

    private final static Scanner sc = new Scanner(System.in);
    private final static CasheringDashboard q = new CasheringDashboard();

    protected void createCashering() throws SQLException {
        System.out.print("Enter your item code: ");
        String itemsCode = sc.nextLine();

        System.out.print("Enter your quantity: ");
        int quantity = 0;
        while (true) {
            try {
                quantity = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid! Input the number");
                sc.nextLine();
                System.out.print("Enter your quantity: ");
            }
        }
        sc.nextLine();

        String query = "INSERT INTO tblcashering(cashering_items_code, cashering_quantity) VALUES (?,?)";
        String checkDuplicateQuery = "SELECT COUNT(*) FROM tblcashering WHERE cashering_items_code = ?";

        try {
            dbConnection();
            preparedStatement = connection.prepareStatement(checkDuplicateQuery);
            preparedStatement.setString(1, itemsCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.out.println("Duplicate Item code " + itemsCode);
                additionalItemCode();
                return;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, itemsCode);
            preparedStatement.setInt(2, quantity);
            preparedStatement.executeUpdate();
            System.out.println("Item code " + itemsCode + " Created Successfully");
            preparedStatement = connection.prepareCall(checkDuplicateQuery);
            additionalItemCode();
        } catch (SQLException e) {
            System.out.println("Item code " + itemsCode + " Created Successfully");
            additionalItemCode();
        } finally {
            disconnectDB();
        }
    }

    public void inputCashering() throws SQLException {
        System.out.println("Are you sure to create cashering? \n\t[Y] Yes \n \t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            createCashering();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            q.casheringDashboard();
            System.exit(0);
        } else {
            System.out.println("Invalid!");
            inputCashering();
        }
    }

    private void additionalItemCode() throws SQLException {
        System.out.println("Do you want to add the item code? \n\t[Y] Yes \n \t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            createCashering();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            q.casheringDashboard();
            System.exit(0);
        } else {
            System.out.println("Invalid!");
            inputCashering();
        }
    }
}
