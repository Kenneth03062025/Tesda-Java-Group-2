package com.app.console;

import com.app.controller.OrderController;
import com.app.controller.OrderItemController;
import com.app.controller.PaymentController;
import com.app.controller.StockController;
import com.app.model.*;
import com.app.model.dto.ListOfItemsAvailableResponse;
import com.app.model.dto.ListOfOrderItemsResponse;
import com.app.model.dto.ListOfOrdersResponse;
import com.app.model.dto.PaymentResponse;
import com.app.state.AppState;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderConsole {



    static final String reset = "\033[0m";
    static final String bold = "\033[1m";
    static final String underline = "\033[4m";
    static final String red = "\033[31m";
    static final String green = "\033[32m";
    static final String yellow = "\033[33m";
    static final String blue = "\033[34m";
    static final String cyan = "\033[36m";
    static final String magenta = "\033[35m";

    static List<Order> orderList;

    static String selectedOrderNumber;

    static Order selectedOrder;

    static OrderItem orderItems;

    static List<OrderItem> orderItemList = new ArrayList<>();

    static List<ItemAvailable> availables;

    private static Scanner sc = new Scanner(System.in);

    public static void init(){
        getAvailableStocks();
    }


    public static void creatOrder(){

        PaymentResponse res = PaymentController.placeOrderPayment(orderItemList,"CSH-1012");
//        Response<?> res = OrderController.createOrder(ordItems,"CSH-1001");
        if(res.getStatus().equals("success")){
            System.out.println("Your Payment Has Place");
            orderItemList.clear();
            displayOrderOptions();
        }
        else {
            displayTemporaryList();
        }

    }

    public static void addItemToList(OrderItem item){
        orderItemList.add(item);
        displayListOptions();
    }

    public static void removeItem(OrderItem item){

        orderItemList.remove(item);
        displayListOptions();

    }

    public static void changeQuantity(int index, OrderItem item){
        orderItemList.set(index,item);
        displayListOptions();
    }


    public static void getAvailableStocks(){
        ListOfItemsAvailableResponse res = StockController.getAvailableItems(AppState.cashering.getOperationNumber());
        if(res.getStatus().equals("success")){
            availables = res.getItemsAvailable();
            displayOrderOptions();
        } else {
            System.out.println("Error");
        }
    }


    public static void displayAvailableStocks(){
        System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", "Id", "Item Number", "Item Name","Price", "Available");
        if(availables.size() > 0) {
            for (ItemAvailable row : availables) {
                System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", availables.indexOf(row)+1 , row.getItemNumber(), row.getItemName(), row.getPrice() ,row.getAvailable());
            }

        } else {
            System.out.println("No Available Stocks");
        }
    }

    public static void displayOrderOptions(){
        displayAvailableStocks();
        System.out.println("  ");
        System.out.println("  ");
        System.out.println(" [ 1. ]  Create Order");
        System.out.println(" [ 2. ]  Exit");

        System.out.println("Enter Your Choice");


            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1: //create order
                    displayListOptions();
                    break;
                case 2:
                    dispose();
                    break;
                case 3:
                    System.out.println("Invalid Input");
                    displayOrderOptions();
            }

    }


    public static void displayTemporaryList(){

        if(orderItemList.size() > 0) {
            System.out.println("Your Item Selected");
            System.out.println("");
            System.out.printf("\n%-5s %-15s %-30s %-10s %-10s %-10s\n", " ", "Item Number", "Item Name","Quantity", "Price " ,"Items Total");
            for (OrderItem row : orderItemList) {
                System.out.printf("\n%-5s %-15s %-30s %-10s %-10s %-10s\n", orderItemList.indexOf(row) +1, row.getItemNumber(), row.getItemName(), row.getQuantity() ,row.getPrice(), row.getPrice()* row.getQuantity());
            }
        } else {
            System.out.println("No Items on This List");
        }

    }

    public static void displayListOptions(){

        displayTemporaryList();

        System.out.println("  ");
        System.out.println("  ");
        System.out.println(" [ 1. ]  Add Item to List");
        System.out.println(" [ 2. ]  Change Quantity");
        System.out.println(" [ 3. ]  Remove Item");
        System.out.println(" [ 4. ]  Proceed to Payment");
        System.out.println(" [ 5. ]  Exit");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                displayAddItem();
                break;
            case 2:
                displayChangeQuntity();
                break;
            case 3:
                displayRemoveItem();
            case 4:
                displayConfirmatoryPayment();
            case    5:
                displayOrderOptions();
            default:
                displayListOptions();
        }
    }

    public static void displayChangeQuntity(){
        displayTemporaryList();


        System.out.println("  ");
        System.out.println("  ");
        System.out.print("Select Item to Change Quantity: ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter New Quantity");
        int newQuantity = sc.nextInt();
        sc.nextLine();


        if(choice < orderItemList.size()){
            OrderItem selected = orderItemList.get(choice-1);
            OrderItem newItem = selected;
            newItem.getItemTotal(selected.getPrice() * newQuantity);
            newItem.setQuantity(newQuantity);
            changeQuantity(choice-1,newItem);

        } else {
            System.out.println("Invalid Item");
            displayChangeQuntity();
        }

    }

    public static void displayAddItem(){
        displayAvailableStocks();
        System.out.println("  ");
        System.out.println("  ");
        System.out.print("Select Item to Add to List: ");
        int choice = sc.nextInt();
        sc.nextLine();
//        OrderItem item = new OrderItem();
        System.out.print("\nQuantity: ");
        int quantity = sc.nextInt();

        //get the item
        ItemAvailable selected = availables.get(choice-1);

        //initiate the OrderItem
        OrderItem item1 = new OrderItem(selected.getItemNumber(),quantity,selected.getPrice());
        item1.setItemName(selected.getItemName());

        //add the item to list
        addItemToList(item1);

    }

    public static void displayRemoveItem(){
        displayTemporaryList();



        System.out.println("  ");
        System.out.println("  ");
        System.out.print("Select Item to Remove: ");
        int choice = sc.nextInt();
        sc.nextLine();


        if(choice <= orderItemList.size()){
            OrderItem item = orderItemList.get(choice-1);
            removeItem(item);

        } else {
            System.out.println("Invalid Item");
            displayRemoveItem();
        }
    }

    public static void displayConfirmatoryPayment(){
        System.out.println("");
        System.out.println("Do You Want To Proceed to Payment");

        System.out.println(blue+ "*=======================*" + reset);
        System.out.println(blue + "*  Proceed to Payment?  *");
        System.out.println(blue+"*=======================*");
        System.out.println(blue + "*[Y] YES                *");
        System.out.println(blue+"*"+red + "[N] NO                 "+ blue+"*");
        System.out.println(blue+"*=======================*");
        System.out.print("Enter a choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            creatOrder();
            //Place the Payment
//            System.out.println("Thank you for using our application");
//            System.exit(0);
        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            displayListOptions();
            System.out.println("Failed Exit");
        } else {
            System.out.println("Invalid! Input the Yes or No");
            displayListOptions();
//            exitProgram();
        }
    }

    public static void dispose(){
        //close the scanner
//        sc.close();
        DashBoard.init();
    }

}
