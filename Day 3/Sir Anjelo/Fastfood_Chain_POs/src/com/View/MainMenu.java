package com.View;

import com.UserAccount.CreateAccount;
import com.UserAccount.LoginAccount;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu extends Dashboard { 

    private final static Scanner sc = new Scanner(System.in);
    private final static CreateAccount c = new CreateAccount();
    private final static LoginAccount l = new LoginAccount();
    private final static MainMenu firstChoice = new MainMenu();
    private final static MainMenu main = new MainMenu();

    public static void main(String[] args) throws SQLException {
        main.mainMenuUserChoice();
    }

    public void mainMenuUserChoice() throws SQLException {

        System.out.println("*** LOG IN ***");
        System.out.println("    [1] Create account");
        System.out.println("    [2] Login");
        System.out.println("    [3] Exit");

        System.out.print("Choose the number: ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                createUserChoices();
                c.createUserAccount();
                exitCreateUserAccount();
                break;
            case "2":
                loginChoices();
                l.loginUserAccount();
                break;
            case "3":
                exitProgram();
                mainMenuUserChoice();
                break;
            default:
                System.out.println("Invalid! Input the number from 1 to 3\n");
                mainMenuUserChoice();
        }
    }

    private void exitCreateUserAccount() throws SQLException {
        System.out.println("Are you sure you want to create useraccount? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter a choice: ");
        String createUserAccount = sc.nextLine();
        if (createUserAccount.equalsIgnoreCase("Yes") || createUserAccount.equalsIgnoreCase("y")) {
            c.createUserAccount();
        }
        if (createUserAccount.equalsIgnoreCase("No") || createUserAccount.equalsIgnoreCase("n")) {
            System.out.println("Thank you for create your user account");
            mainMenuUserChoice();
        } else {
            System.out.println("Invalid! ");
            exitCreateUserAccount();
        }
    }

    private static void exitProgram() {
        System.out.println("Are you sure you want to exit? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter a choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            System.out.println("Thank you for using our application");
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            System.out.println("Failed Exit");
        } else {
            System.out.println("Invalid! Input the Yes or No");
            exitProgram();
        }
    }

    private static void loginChoices() throws SQLException {
        System.out.println("Are you to login? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            l.loginUserAccount();
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            firstChoice.mainMenuUserChoice();
        }else{
            System.out.println("Invalid!");
            loginChoices();
        }
    }
    private static void createUserChoices() throws SQLException{
        System.out.println("Are you sure to create user? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")){
            c.createUserAccount();
        } if(choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")){
            firstChoice.mainMenuUserChoice();
        } else {
            System.out.println("Invalid!");
            createUserChoices();
                    
        }
    }
}
