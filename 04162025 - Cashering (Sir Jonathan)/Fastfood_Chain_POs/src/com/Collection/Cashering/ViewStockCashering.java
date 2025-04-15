package com.Collection.Cashering;

import com.Database.DatabaseConnection;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewStockCashering extends DatabaseConnection {

    private final static Scanner sc = new Scanner(System.in);
    private final static ViewStockCasheringDashBoard q = new ViewStockCasheringDashBoard();

    private void viewStockCashering() throws SQLException {
        String query = "SELECT * FROM tblcashering WHERE cashering_archieved = 0";
        try {
            dbConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.printf("\n%-5s %-15s %-10s %-10s\n", "Id", "ItemsCode", "Quantity", "Items Sold");
            System.out.println("------------------------------------------------------");
            while (resultSet.next()) {
                int id = resultSet.getInt("cashering_id");
                String itemsCode = resultSet.getString("cashering_items_code");
                int quantity = resultSet.getInt("cashering_quantity");
                int itemsSold = resultSet.getInt("cashering_items_sold");
                System.out.printf("%-5d %-15s %-10d %-10d\n", id, itemsCode, quantity, itemsSold);
                if (quantity <= 10) {
                    System.out.println("WARNING! " + "The total quantity of " + quantity + " for item code " + itemsCode);
                }
            }
            choiceViewDashboard();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnectDB();
        }
    }

    private void searchStockCashering() throws SQLException {
        System.out.print("Search Items Code: ");
        String itemsCodeKW = sc.nextLine();
        String query = "SELECT * FROM tblcashering WHERE cashering_items_code like ?";
        try {
            dbConnection();
            preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, "%" + itemsCodeKW + "%");
            resultSet = preparedStatement.executeQuery();
            System.out.printf("\n%-5s %-15s %-10s %-12s\n", "Id", "ItemsCode", "Quantity", "Items Sold");
            System.out.println("------------------------------------------------------");

            boolean found = true;
            while (resultSet.next()) {
                int id = resultSet.getInt("cashering_id");
                String itemsCode = resultSet.getString("cashering_items_code");
                int quantity = resultSet.getInt("cashering_quantity");
                int itemsSold = resultSet.getInt("cashering_items_sold");
                System.out.printf("%-5d %-15s %-10d %-12d\n", id, itemsCode, quantity, itemsSold);
                found = false;
            }
            if (found) {
                System.out.println("\"No items found for code: " + itemsCodeKW);
            }
            boolean continueSearch = searchStock();
            if (continueSearch) {
                choiceViewDashboard();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            checkingDatabase();
        }
    }

    public void choiceViewDashboard() throws SQLException {
        System.out.println("\nWhat do you want? ");
        System.out.println("    [1] View");
        System.out.println("    [2] Search");
        System.out.println("    [3] Back");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                viewStockCashering();
                break;
            case "2":
                searchStockCashering();
                break;
            case "3":
                choiceViewDashboardBack();
                break;
            default:
                System.out.println("Invalid!");
                choiceViewDashboard();
                break;
        }
    }

    private boolean searchStock() throws SQLException {
        System.out.println("\nDo you want to search again? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            searchStockCashering();
            System.exit(0);
            return true;
        } else if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            choiceViewDashboard();
            System.exit(0);
            return false;
        } else {
            System.out.println("Invalid! Enter [Y] Yes or [N] No");
            return searchStock();
        }
    }

    private void choiceViewDashboardBack() throws SQLException {
        System.out.println("Are you sure to exit? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            q.dashboardViewStockCashering();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            choiceViewDashboard();
        } else {
            System.out.println("Invalid!");
            choiceViewDashboardBack();
        }
    }
}
