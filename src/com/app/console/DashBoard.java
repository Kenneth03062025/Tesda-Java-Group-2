package com.app.console;

import com.app.controller.CasheringController;
import com.app.model.dto.CasheringItemResponse;
import com.app.state.AppState;

import java.util.Scanner;

public class DashBoard {

    static Scanner sc = new Scanner(System.in);

    static final String reset = "\033[0m";
    static final String bold = "\033[1m";
    static final String underline = "\033[4m";
    static final String red = "\033[31m";
    static final String green = "\033[32m";
    static final String yellow = "\033[33m";
    static final String blue = "\033[34m";
    static final String cyan = "\033[36m";
    static final String magenta = "\033[35m";


    public static void init(){


        CasheringItemResponse item = CasheringController.getCreatedCashering();
//        System.out.println(item.getStatus());
//        System.out.println(item.getCashering().getOperationNumber());
//        System.out.println(item.getCashering().getCloseAt());

        AppState.cashering = item.getCashering();
        System.out.println(AppState.user.getUserNumber() + " from App State");
        System.out.println(AppState.user.getRole() + " from App State");

        if(AppState.cashering.getOpenAt() == null){
            displayUnopenCasheringDashbord();
        } else {
            displayDashboard();
        }

//        if(item.getStatus().equals("success")){
//            //
//
//            AppState.cashering = item.getCashering();
//
//
//        } else {
//            //
//            displayCasheringDashboard();
//        }

        displayDashboard();

    }

    public static void displayDashboard(){
        int  selectedNumber;

        System.out.println(blue + "What do you want?");
        System.out.println(blue +"    [1] Items");
        System.out.println(blue +"    [2] Casherings");
        System.out.println(blue +"    [3] Orders");
        System.out.println(blue +"    [4] Logout");

        selectedNumber = sc.nextInt();
        sc.nextLine();

        switch(selectedNumber){
            case 1 : ItemConsole.getAllItem();
                break;
            case 2 :
                System.out.println("Cashering");
                CasheringConsole.init();
                break;
            case 3 :
                System.out.println("Orders");
                OrderConsole.init();
                break;
            case 4:
                logout();

                break;
            default:
                System.out.println("Invalid Input");
        }

    }

    public static void displayUnopenCasheringDashbord(){
        int  selectedNumber;

        System.out.println(blue + "What do you want?");
        System.out.println(blue +"    [1] Items");
        System.out.println(blue +"    [2] Casherings");
        System.out.println(blue +"    [3] Logout");
        System.out.print("Select an option: ");
        selectedNumber = sc.nextInt();
        sc.nextLine();

        switch(selectedNumber){
            case 1 : ItemConsole.getAllItem();
                break;
            case 2 :
                System.out.println("Cashering");
                CasheringConsole.init();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    private static void logout() {
        System.out.println();
        System.out.println(cyan + "=====================================================");
        System.out.println("         Are you sure you want to logout?     ");
        System.out.println("=====================================================" + reset);
        System.out.println("[y] YES");
        System.out.println("[N] NO");
        String accountSignOff = sc.nextLine();
        if (accountSignOff.equalsIgnoreCase("Yes") || accountSignOff.equalsIgnoreCase("y")) {
//            sc.close();
            AuthConsole.displayAuthMain();
        }
        if (accountSignOff.equalsIgnoreCase("No") || accountSignOff.equalsIgnoreCase("n")) {
            displayDashboard();
        } else {
            System.out.println("Invalid!");
            logout();
        }
    }
}
