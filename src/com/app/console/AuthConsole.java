package com.app.console;

import com.app.controller.AuthController;
import com.app.model.Response;
import com.app.model.User;
import com.app.state.AppState;

import java.util.Scanner;

public class AuthConsole {

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


    public static void getLogInUser(User logInUser) {
        Response<User> res = AuthController.logInUser(logInUser);
//        System.out.println(res.getData().getUserName());
//        System.out.println(res.getData().getRole());
        AppState.user = res.getData();
        if (res.getStatus().equals("success")) {
            //System.out.println(res.getData());
            DashBoard.init();
        } else {
            displayAuthFailed(res.getStatus(), res.getMessage());
            displayLogInForm();
        }
    }

    public static void createNewUser(User newUser) {
        Response<?> res = AuthController.createUser(newUser);
        if (res.getStatus().equals("success")) {
            System.out.println(res.getDataString());
//            System.out.println("LogIn");
//            displayLogInForm();
            createAgain();
        } else {
//            displayAuthFailed(res.getStatus(), res.getMessage());
            System.out.println("User Creation Failed");
            displayAuthMain();
        }
    }

    public static void displayCreateUserForm() {

        System.out.println(blue + "*=======================*");
        System.out.println(blue + "*    Create account     *");
        System.out.println(blue + "*=======================*");

        System.out.print(blue + "*Enter your firstname:");
        String firstName = sc.nextLine();

        System.out.print(blue + "*Enter your lastname:");
        String lastName = sc.nextLine();

        System.out.print(blue + "*Enter your middle name:");
        String middleName = sc.nextLine();

        System.out.print(blue + "*Enter your username:");
        String userName = sc.nextLine();

        System.out.print(blue + "*Enter your password:");
        String password = sc.nextLine();
        System.out.println(blue + "*=======================*");
        System.out.println("Your account was successfully created.\n");

        User user = new User(firstName, lastName, middleName, userName, password);
        createNewUser(user);

    }

    public static void displayAuthMain() {


        System.out.println(cyan + "*=======================*");
        System.out.println(red + "*       McJabili        *");
        System.out.println(yellow + "*=======================*" + reset);
        System.out.println(blue + "*    [1] Create account *");
        System.out.println(blue + "*    [2] Login          *");
        System.out.println(blue + "*    [3] Exit           *");
        System.out.println(blue + "*************************");
        System.out.print(blue + "Choose a number:");
        int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid! Input the number from 1 to 3.");
                sc.nextLine();
                displayAuthMain();
            }

        switch (choice) {
            case 1:
                displayCreateUserForm();
                break;
            case 2:
                displayLogInForm();
                break;
            case 3:
                exitProgram();
            default:
                System.out.println("Invalid! Input the number from 1 to 3.");
                displayAuthMain();
        }

    }

    public static void displayLogInForm() {
        System.out.print("Enter your username: ");
        String userLoginName = sc.nextLine();
        System.out.print("Enter your password: ");
        String userLoginPassword = sc.nextLine();
        try {
            User user = new User(userLoginName, userLoginPassword);
            getLogInUser(user);
        } catch (Exception e) {
            System.out.println("Invalid! Incorrect username or password");
            displayLogInForm();

        }

    }

    public static void displayAuthFailed(String status, String message) {
        System.out.println(status);
        System.out.println(message);

        displayLogInForm();
    }

    public static void createAgain() {
        System.out.println(blue + "*=======================*" + reset);
        System.out.println(blue + "*   Create User Again?    *");
        System.out.println(blue + "*=======================*");
        System.out.println(blue + "*[Y] YES                *");
        System.out.println(blue + "*" + red + "[N] NO                 " + blue + "*");
        System.out.println(blue + "*=======================*");
        System.out.print("Enter a choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            displayCreateUserForm();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            displayAuthMain();
//            System.out.println("Failed Exit");
        } else {
            System.out.println("Invalid! Input the Yes or No");
            exitProgram();
        }
    }

    public static void exitProgram() {
        System.out.println(blue + "*=======================*" + reset);
        System.out.println(blue + "*   Exit application    *");
        System.out.println(blue + "*=======================*");
        System.out.println(blue + "*[Y] YES                *");
        System.out.println(blue + "*" + red + "[N] NO                 " + blue + "*");
        System.out.println(blue + "*=======================*");
        System.out.print("Enter a choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            System.out.println("Thank you for using our application");
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            displayAuthMain();
//            System.out.println("Failed Exit");
        } else {
            System.out.println("Invalid! Input the Yes or No");
            exitProgram();
        }
    }
}
