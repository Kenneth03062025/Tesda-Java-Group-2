package com.Collection.Cashering;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewStockCasheringDashBoard {

    private final static Scanner sc = new Scanner(System.in);
    private final static CasheringDashboard q = new CasheringDashboard();
    private final static ViewStockCashering w = new ViewStockCashering();
    private final static DeleteStockCashering e = new DeleteStockCashering();

    public void dashboardViewStockCashering() throws SQLException {
        System.out.println("What do you want?");
        System.out.println("    [1] View Stock");
        System.out.println("    [2] Delete Stock");
        System.out.println("    [3] Back");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                // View Stock
                w.choiceViewDashboard();
                break;
            case "2":
                // Delete Stock
                e.deleteStockCasheringInput();
                break;
            case "3":
                dashboardViewStockCasheringBack();
                break;
            default:
                System.out.println("Invalid!");
                dashboardViewStockCashering();
        }
    }

    public void inputViewStockCashering() throws SQLException {
        System.out.println("Are you sure to view stock cashering? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            dashboardViewStockCashering();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            q.casheringDashboard();
            System.exit(0);
        } else {
            System.out.println("Invalid!");
            inputViewStockCashering();
        }
    }
    private void dashboardViewStockCasheringBack() throws SQLException{
        System.out.println("Are you sure to exit? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        
        if(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")){
            q.casheringDashboard();
        } if(choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")){
            dashboardViewStockCashering();
        } else {
            System.out.println("Invalid!");
            dashboardViewStockCasheringBack();
        }
    }
}
