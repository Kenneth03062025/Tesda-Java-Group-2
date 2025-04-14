package com.Collection.Payment;

import com.Database.DatabaseConnection;
import java.sql.SQLException;
import java.util.Scanner;

public class CreatePayment extends DatabaseConnection {

    private final static Scanner sc = new Scanner(System.in);
    private final static DashBoardPayment output = new DashBoardPayment();

    private void paymentCreate() throws SQLException {
        System.out.print("Payment Number: ");
        String paymentNo = sc.nextLine();

        System.out.print("Order Number: ");
        String orderNo = sc.nextLine();

        System.out.print("Total: ");
        Double total = null;
        while(true){
            try {
                total = sc.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid!");
                System.out.print("Total: ");
                sc.nextLine();
            }
        }
        
        String query = "INSERT INTO tblpayment(payment_no,payment_order_no,payment_total) VALUES (?,?,?)";
        try {
            dbConnection();
            preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, paymentNo);
            preparedStatement.setString(2, orderNo);
            preparedStatement.setDouble(3, total);
            preparedStatement.executeUpdate();
            System.out.println("Payment Number " + paymentNo + " Added Successfully");
            sc.nextLine();
            System.out.println("Are you sure to additional payment? \n\t[Y] Yes \n\t[N] No");

            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
                paymentCreate();
            }
            if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
                output.paymentChoices();
                System.exit(0);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            paymentCreate();
        } finally {
            disconnectDB();
        }
    }

    public void inputPayment() throws SQLException {
        System.out.println("Are you sure create a payment? \n\t[Y] Yes \n\t[N] No");
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            paymentCreate();
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            output.paymentChoices();
            System.exit(0);
        } else {
            System.out.println("Invalid!");
            inputPayment();
        }
    }
}
