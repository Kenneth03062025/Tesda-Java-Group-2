package com.Collection.Cashering;

import com.View.Dashboard;
import java.sql.SQLException;
import java.util.Scanner;

public class CasheringDashboard {

    private final static Scanner sc = new Scanner(System.in);
    private final static Dashboard q = new Dashboard();
    private final static CreateCashering w = new CreateCashering();
    private final static UpdateCashering e = new UpdateCashering();
    private final static ViewStockCasheringDashBoard r = new ViewStockCasheringDashBoard();

    protected void casheringDashboard() throws SQLException {
        System.out.println("What do you want? ");
        System.out.println("    [1] Create New Cashering");
        System.out.println("    [2] Update Cashering");
        System.out.println("    [3] View Stock");
        System.out.println("    [4] Back to dashboard");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                // Create new cashering
                w.inputCashering();
                break;
            case "2":
                //Update Cashering
                e.inputUpdateCashering();
                break;
            case "3":
                // View Stock
                r.inputViewStockCashering();
                break;
            case "4":
                casheringDashboardBack();
                break;
            default:
                System.out.println("Invalid!");
                casheringDashboard();
        }
    }

    public void inputCashering() throws SQLException {
        while (true) {
            System.out.println("Do you want cashering dashboard? \n\t[Y] Yes \n\t[N] No");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
                casheringDashboard();
                System.exit(0);
            } else if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
                q.dashboardOption();
                System.exit(0);
            } else {
                System.out.println("Invalid!");
                inputCashering();
            }
        }
    }

    private void casheringDashboardBack() throws SQLException {
        System.out.println("Are you sure to exit? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            q.dashboardSystem();
            q.dashboardOption();
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            casheringDashboard();
        } else {
            System.out.println("Invalid!");
            casheringDashboardBack();
        }
    }
}
