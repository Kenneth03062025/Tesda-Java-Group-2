package com.Collection.Payment;

import com.Database.DatabaseConnection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdatePayment extends DatabaseConnection {

    private static final Scanner sc = new Scanner(System.in);
    private final static DashBoardPayment output = new DashBoardPayment();

    private void updatePayment() throws SQLException {
        System.out.print("Payment Number: ");
        String paymentNo = sc.nextLine();

        System.out.print("Total: ");
        Double total = null;
        while (true) {
            try {
                total = sc.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid!");
                sc.nextLine();
                updatePayment();
            }
        }

        String query = "UPDATE tblpayment SET payment_total = ? WHERE payment_no = ?";
        try {
            dbConnection();
            do {
                preparedStatement = connection.prepareCall(query);
                preparedStatement.setDouble(1, total);
                preparedStatement.setString(2, paymentNo);
                //  additionalUpdatePayment();
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Payment Number " + paymentNo + " Update Successfully");
                } else {
                    System.out.println("No record found with Payment Number " + paymentNo);
                }
            } while (additionalUpdatePayment());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            checkingDatabase();
        }
    }

    public void inputUpdate() throws SQLException {
        System.out.println("Are you sure to update the payment? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            updatePayment();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            output.paymentChoices();
            System.exit(0);
        } else {
            System.out.println("Invalid!");
            inputUpdate();
        }
    }

    private boolean additionalUpdatePayment() throws SQLException {
        System.out.println("Do you want to update another payment? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String userInput = sc.nextLine();
        sc.nextLine();
        updatePayment();
        return userInput.equalsIgnoreCase("yes");
    }
}
