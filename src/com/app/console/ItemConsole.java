package com.app.console;

import com.app.controller.ItemController;
import com.app.model.Item;
import com.app.model.Response;
import com.app.model.dto.ListOfItemsResponse;
import com.app.state.AppState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemConsole {

    static final String reset = "\033[0m";
    static final String bold = "\033[1m";
    static final String underline = "\033[4m";
    static final String red = "\033[31m";
    static final String green = "\033[32m";
    static final String yellow = "\033[33m";
    static final String blue = "\033[34m";
    static final String cyan = "\033[36m";
    static final String magenta = "\033[35m";
    static List<Item> items = new ArrayList<>();
    static Item selectedItem = new Item();

//    static Item newItem = new Item();

    static Scanner sc = new Scanner(System.in);

    //callController or actions
    public static void getAllItem(){
        ListOfItemsResponse res = ItemController.getAllItems();

        if (res.getStatus().equals("success")) {
            items = res.getItems();
            if(AppState.user.getRole().equals("admin")){
                displayAllItem();
            }
            disAllItemCashier();
        } else {
            displayErrorConsole();
        }
    }

    public static void createItem(Item item){
        Response<?> res = ItemController.saveItem(item);
        if (res.getStatus().equals("success")) {
            getAllItem();
        } else {
            displayErrorConsole();
        }
    }

    public static void updateAnItem(String itemNumber,Item item){
        Response<?> res = ItemController.updateAnItem(itemNumber,item);
        if (res.getStatus().equals("success")) {
            getAllItem();
        } else {
            displayErrorConsole();
        }
    }

    public static void setItemStatus(String itemNumber, String status){
        Response<?> res = ItemController.setItemStatus(itemNumber, status);
        if (res.getStatus().equals("success")) {
            getAllItem();
        } else {
            displayErrorConsole();
        }
    }

    public static void deleteAnItem(String itemNumber){
        //deleteAnItem();
        Response<?> res = ItemController.deleteAnItem(itemNumber);
        if (res.getStatus().equals("success")) {
            System.out.println(res.getMessage());
            getAllItem();
        } else {
            displayErrorConsole();
        }
    }

    //templates

    public static void displayErrorConsole(){
        System.out.println("Something went wrong");
    }
    public static void displayAllItem(){
        //Scanner sc = new Scanner(System.in);
        int selectedNumber;

        for ( Item itm: items ) {
            System.out.println(" [" + (items.indexOf(itm) + 1) + "] " + itm.getItem_no() + " " + itm.getItem_name());
        }

        System.out.println( " [" + (items.size() + 1) + "] " + "Create New Item");
        System.out.println(" [" + (items.size() + 2) + "] " + "Exit");
        System.out.print("Select an option: ");
        selectedNumber = sc.nextInt();
        sc.nextLine();

        if(selectedNumber <= 0 || selectedNumber > items.size() + 2){
            System.out.println("Invalid Input");
            displayAllItem();
            return;
        }

        if(selectedNumber < items.size() + 1){
            selectedItem = items.get(selectedNumber-1);
            displayAnItemOptions();
            return;
        }

        if(selectedNumber == items.size() + 1){
            displayCreateForm();
            return;
        }

        if(selectedNumber == items.size() + 2 ){
            System.out.println("Exit");
            DashBoard.init();
            return;
        }

    }

    public static void disAllItemCashier(){
        int selectedNumber;

        for ( Item itm: items ) {
            System.out.println(" [" + (items.indexOf(itm) + 1) + "] " + itm.getItem_no() + " " + itm.getItem_name());
        }

        System.out.println(" [" + (items.size() + 1) + "] " + "Exit");
        System.out.print("Select an option: ");
        selectedNumber = sc.nextInt();
        sc.nextLine();

        if(selectedNumber <= 0 || selectedNumber > items.size() + 1){
            System.out.println("Invalid Input");
            disAllItemCashier();
            return;
        }

        if(selectedNumber < items.size() + 1){
            selectedItem = items.get(selectedNumber-1);
            displayAnItemOptions();
            return;
        }

        if(selectedNumber == items.size() + 1 ){
            System.out.println("Exit");
            DashBoard.init();
            return;
        }
    }

    public static Item displayForm(){
        Item item;
        System.out.println("Enter Name of an Item to sell.");
        String item_name = sc.nextLine();
        //Validation

        System.out.println("Enter Short Description of an Item");
        String item_description = sc.nextLine();
        //Validation

        System.out.println("Enter the Price of an Item");
        double price = sc.nextDouble();
        sc.nextLine();
        //Validation

        System.out.println("Enter the unit of an Item");
        String unit = sc.nextLine();
        //Validation
        item = new Item(item_name,item_description,price,unit);
        return item;
    }
    public static void displayCreateForm(){
        Item item = displayForm();
        createItem(item);
    }

    public static void displayAnItem(){

        System.out.println("\n" + selectedItem.getItem_no() + " " + selectedItem.getItem_name());
        System.out.println(selectedItem.getItem_description());
        System.out.printf("At P" + selectedItem.getPrice() + " per " + selectedItem.getUnit() +"\n");
        System.out.println(selectedItem.getStatus().equals("active") || selectedItem.getStatus() == null ? "Actively Serving this Item" : "Temporarily Not Serving");
        //displayAnItemOptions();
    }

    public static void displayAnItemOptions(){
        displayAnItem();
        int selectedNumber;

        if(AppState.user.getRole().equals("admin")) {
            System.out.println(" [ 1 ] " + "Update this Item");
            System.out.println(" [ 2 ] " + ((selectedItem.getStatus().equals("inactive")) ? "Set this Active" : "Deactivate This Item"));
            System.out.println(" [ 3 ] " + "Delete this Item");
            System.out.println(" [ 4 ] " + "Back");
            System.out.print("Select an option: ");
            selectedNumber = sc.nextInt();
            sc.nextLine();
            String inverseStatus = selectedItem.getStatus().equals("active") ? "inactive" : "active";

            switch (selectedNumber) {
                case 1:
                    displayItemUpdateForm();
                case 2:
                    setItemStatus(selectedItem.getItem_no(), inverseStatus);
                case 3:
                    displayConfirmDelete();
                case 4:
                    getAllItem();
                default:
                    System.out.println("Invalid Input");
            }
        } else {
            System.out.println("Press Any Key Enter to Go Back");
            sc.nextLine();
            getAllItem();
        }
    }

    public static void displayItemUpdateForm(){
        displayAnItem();
        Item item = displayForm();
        updateAnItem(selectedItem.getItem_no(),item);
    }

    public static void displayConfirmDelete(){


        System.out.println(blue+ "*=======================*" + reset);
        System.out.println(blue + "*   Delete this Item?   *");
        System.out.println(blue+"*=======================*");
        System.out.println(blue + "*[Y] YES                *");
        System.out.println(blue+"*"+red + "[N] NO                 "+ blue+"*");
        System.out.println(blue+"*=======================*");
        System.out.print("Enter a choice: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("y")) {
            deleteAnItem(selectedItem.getItem_no());

        }
        if (choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("n")) {
            displayAllItem();
        } else {
            System.out.println("Invalid! Input the Yes or No");
            displayConfirmDelete();
        }

    }



}
