package com.View;

import java.util.Scanner;
import com.Collection.Payment.DashBoardPayment;
import java.sql.SQLException;
public class Dashboard {

    private final static Scanner sc = new Scanner(System.in);
    private final static DashBoardPayment q = new DashBoardPayment();
//    public static void main(String[] args) {
//        d.dashboardSystem();
//        dashboardOption();
//    }

    public void dashboardSystem() {
        // ANSI escape codes for colors and styles
        String reset = "\033[0m";  // Reset color
        String bold = "\033[1m";   // Bold text
        String underline = "\033[4m"; // Underline text
        String red = "\033[31m";    // Red color
        String green = "\033[32m";  // Green color
        String yellow = "\033[33m"; // Yellow color
        String blue = "\033[34m";   // Blue color

        // Design the "Welcome" text with different ANSI styles
        System.out.println(blue + bold + "W" + reset + green + "e" + reset + red + "l" + reset + yellow + "c" + reset + blue + "o" + reset + green + "m" + reset + red + "e" + reset);

        // If you want it in a nice ASCII art style with colors, you could use something like this
        System.out.println(underline + " W   W  EEEEE  L       CCCC  OOO  M   M  EEEEE ");
        System.out.println(green + " W   W  E      L      C     O   O M   M  E     ");
        System.out.println(red + " W W W  EEEE   L      C     O   O M   M  EEEE  ");
        System.out.println(yellow + " WW WW  E      L      C     O   O M   M  E     ");
        System.out.println(blue + " W   W  EEEEE  LLLLL  CCCC  OOO  M   M  EEEEE " + reset);
    }

    public  void dashboardOption() throws SQLException {
        System.out.println("What do you want?");
        System.out.println("    [1] Items");
        System.out.println("    [2] Casherings");
        System.out.println("    [3] Orders");
        System.out.println("    [4] Payments");
        System.out.println("    [5] Logout");

        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                // Items
                break;
            case "2":
                // Casherings
                break;
            case "3":
                // Orders
                break;
            case "4":
                q.inputUser();
                break;
            case "5":
                logout();
                dashboardOption();
                break;
            default:
                System.out.println("Invalid!");
        }
    }

    private static void logout() {
        System.out.println("Are you sure to logout? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String accountSignOff = sc.nextLine();
        if (accountSignOff.equalsIgnoreCase("Yes") || accountSignOff.equalsIgnoreCase("y")) {
            System.out.println("Thank you to login");
            System.exit(0);
        }
        if (accountSignOff.equalsIgnoreCase("No") || accountSignOff.equalsIgnoreCase("n")) {
            System.out.println("Failed to exit");
        } else {
            System.out.println("Invalid!");
            logout();
        }
    }
}
