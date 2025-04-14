package com.Collection.Payment;

import java.util.Scanner;
import com.View.Dashboard;
import java.sql.SQLException;

public class DashBoardPayment {

    private final static Scanner sc = new Scanner(System.in);
    private final static Dashboard q = new Dashboard();
    private final static CreatePayment w = new CreatePayment();
    private final static UpdatePayment z = new UpdatePayment(); 
    
    public void paymentChoices() throws SQLException {

        System.out.println("What do you need?");
        System.out.println("    [1] Create Payment?");
        System.out.println("    [2] Update Payment?");
        System.out.println("    [3] Back to DashBoard");
        System.out.print("Enter your choice? ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                w.inputPayment();
                break;
            case "2":
                // Update Payment
                z.inputUpdate();
                break;
            case "3":
                backToDashBoard();
                break;
            default:
                System.out.println("Invalid!");
        }
    }

    public  void inputUser() throws SQLException {
        System.out.println("Are you sure to payment dashboard? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            paymentChoices();
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            q.dashboardSystem();
            q.dashboardOption();
        } else {
            System.out.println("Invalid!");
            inputUser();
        }
    }

    private void backToDashBoard() throws SQLException {
        System.out.println("Are you sure back to dashboard? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            q.dashboardSystem();
            q.dashboardOption();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            paymentChoices();
        } else {
            System.out.println("Invalid!");
            inputUser();
        }
    }
}
