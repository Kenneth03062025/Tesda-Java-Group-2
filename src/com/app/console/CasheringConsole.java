package com.app.console;

import com.app.controller.CasheringController;
import com.app.controller.ItemController;
import com.app.controller.StockController;
import com.app.model.Cashering;
import com.app.model.Item;
import com.app.model.Response;
import com.app.model.Stocks;
import com.app.model.dto.CasheringItemResponse;
import com.app.model.dto.ListOfItemsResponse;
import com.app.model.dto.ListOfStocksResponse;
import com.app.state.AppState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CasheringConsole {
    private final static Scanner sc = new Scanner(System.in);

    private static List<Item> activeItems = new ArrayList<>();

    private static List<Stocks> casheringStocks = new ArrayList<>();

    private static String currentCasheringNumber;

    private static Cashering currentCashering;

    private static List<Stocks> temporaryList = new ArrayList<>();

    public static void init(){
        getCreatedCashering();
    }

    public static void getCasheringStocks(){
        ListOfStocksResponse res = StockController.getStocksbyCashering(currentCasheringNumber);
//        System.out.println(res.getMessage());

        if (res.getStatus().equals("success")) {
            casheringStocks = res.getStocks();
            AppState.stocksList = casheringStocks;
            displayStocks();
        } else {
            displayErrorConsole();
        }

    }

    public static void getCreatedCashering(){
        CasheringItemResponse item = CasheringController.getCreatedCashering();
        System.out.println(item.getStatus());
        System.out.println(item.getCashering().getOperationNumber());

        if(item.getStatus().equals("success")){
            //
            if(item.getCashering().getOperationNumber() == null){
                displayCasheringDashboard();
                return;
            }
            currentCasheringNumber = item.getCashering().getOperationNumber();
            currentCashering = item.getCashering();
            AppState.cashering = item.getCashering();

            displayCreatedCashering();
        } else {
            //
            displayCasheringDashboard();
        }
    }

    public static void getActiveItems(){
        ListOfItemsResponse res = ItemController.getActiveItems();
        if (res.getStatus().equals("success")) {
            activeItems = res.getItems();
            //displayAllItem();
            displayActiveItems();

        } else {
            displayErrorConsole();
        }
    }

    public static void addItemToTemporaryList(Stocks stocks){
        temporaryList.add(stocks);
    }

    public static void updateItem(Stocks stocks, Stocks newStocks){
        int index = temporaryList.indexOf(stocks);
        temporaryList.set(index,newStocks);
    }

    public static void deleteItem(Stocks stocks){
        int index = temporaryList.indexOf(stocks);
        temporaryList.remove(stocks);
    }

    public static void addItemsToCashering(){
        Response<Stocks> res = StockController.addItemsToCashering(temporaryList);
        System.out.println(res.getStatus());
        System.out.println(res.getMessage());
        if(res.getStatus().equals("success")){
            temporaryList.clear();
            displayCreatedCashering();
        } else {
            displayErrorConsole();
        }

    }
    public static void createCashering(){
        Response<Cashering> res = CasheringController.createCashering();
//        System.out.println(res.getData().getOperationNumber());
        currentCashering = res.getData();
        if(res.getStatus().equals("success")){
            getCreatedCashering();
        } else {
            displayErrorConsole();
            displayCasheringDashboard();
        }
    }

    public static void openCashering(){
        Response<Cashering> res = CasheringController.openCashering(currentCasheringNumber);
        if(res.getStatus().equals("success")){
            getCreatedCashering();
        } else {
            displayErrorConsole();
            displayCasheringOptions();
        }
    }

    public static void closeCashering(){
        Response<Cashering> res = CasheringController.closeCashering(currentCasheringNumber);
        if(res.getStatus().equals("success")){
            AppState.cashering = null;
            displayCasheringDashboard();
        } else {
            displayErrorConsole();
            displayCasheringOptions();
        }
    }

    public static void displayErrorConsole(){
        System.out.println("Something went wrong");
    }

    public static void displayCasheringDashboard(){
        System.out.println("What do you want? ");
        System.out.println("    [1] Create New Cashering");
        System.out.println("    [2] Back to dashboard");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice==1){
            createCashering();
        } else if (choice == 2) {
            DashBoard.init();
            //back Go to DashBoard
        } else {
            System.out.println("Invalid");
            displayCasheringDashboard();
        }

    }

    public static void displayCreatedCashering(){
        System.out.println("What do you want? ");
        System.out.println("    [1] " + currentCasheringNumber);
        System.out.println("    [2] Back to dashboard");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice==1){
            displayCasheringOptions();
        } else if (choice == 2) {
            //back
            DashBoard.init();
        } else {
            System.out.println("Invalid");
            displayCreatedCashering();
        }

    }

    public static void displayCasheringOptions(){
//        System.out.println("  " + currentCasheringNumber + "  ");
        if(currentCashering.getOpenAt() == null){
             System.out.print("Created but not yet Open");
        } else {
            System.out.print("Created and Open");
        }

        System.out.println("\n");
        System.out.println("    [1] View Stocks");

        if(currentCashering.getOpenAt() == null) {
            System.out.println("    [2] Open Cashering");
        } else {
            System.out.println("    [2] Close Cashering");
        }
        System.out.println("    [3] Back");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                System.out.println("Go to View Stocks");
                getCasheringStocks();
                break;
            case 2:
                displayConfirmatoryAction();
                break;
            case 3:
                displayCreatedCashering();
                break;
            default:
                System.out.println("Invalid");
                displayCasheringDashboard();
        }


    }

    public static void displayConfirmatoryAction(){
        System.out.println("Do you to ");
        if(currentCashering.getOpenAt() == null){
            System.out.print("Open Cashering");
        }
        else {
            System.out.print("Close Cashering");
        }

        System.out.println("    [1] Yes");
        System.out.println("    [2] No");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                if(currentCashering.getOpenAt() == null){
                    openCashering();
                }
                else {
                    closeCashering();
                }
                break;
            case 2:
                displayCasheringOptions();
                break;
            default:
                System.out.println("Invalid Input");
                displayCasheringOptions();
        }
    }

    public static void displayStocks(){
        System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", "Id", "Item Number", "Item Name","Quantity", "Items Sold");
        if(casheringStocks.size() > 0){
            for ( Stocks row: casheringStocks ) {
                System.out.printf("\n%-5s %-15s %-30s %-10s %-10s\n", row.getId(), row.getItemNumber(), row.getItemName(), row.getQuantity(), row.getItemSold());
            }
//            displayStockListOptions();
        } else {
            System.out.println("  ");
            System.out.println("Empty Stocks");
            displayStockListOptions();
//            displayEmptyStockList();
            //bulky add
        }
    }

    public static void displayTemporaryListItem(){
        System.out.printf("\n%-5s %-15s %-30s %-10s\n", "", "Item Number", "Item Name","Quantity");
        for ( Stocks row: temporaryList ) {
            System.out.printf("\n%-5s %-15s %-30s %-10s\n", "", row.getItemNumber(), row.getItemName(), row.getQuantity());
        }
        System.out.println(" ");

        System.out.println("    [1] Add Item Stocks");
        System.out.println("    [2] Update Item Quantity");
        System.out.println("    [3] Remove Item");
        System.out.println("    [4] Save this to Cashering Stocks");
        System.out.println("    [5] Back");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();


        switch (choice){
            case 1 :
                getActiveItems();
            break;
            case  2:
                displayChangeQuantity();
                //System.out.println("Update Item Quantity");
                break;
            case 3:
                displayRemoveItem();
                break;
            case 4:
                addItemsToCashering();
            default:
                displayCasheringDashboard();
//                System.out.println("Back Options");

        }
    }

    public static void displayStockListOptions(){

        System.out.println(" ");
        System.out.println("    [1] Add Item Stocks");
        System.out.println("    [2] Back");
//        System.out.println("    [3] Remove Item");
//        System.out.println("    Press Enter to Return");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                displayTemporaryListItem();
                break;
            case 2:
                displayCasheringOptions();
                break;
            default:
                displayStockListOptions();
        }

    }


    public static void displayActiveItems() {
        int selectedNumber;

        for ( Item itm: activeItems ) {
            System.out.println(" [" + (activeItems.indexOf(itm) + 1) + "] " + itm.getItem_no() + " " + itm.getItem_name());
        }

        System.out.print("Select an option: ");
        selectedNumber = sc.nextInt();
        sc.nextLine();

        //2. Get the Item from list
        Item selectedItem = activeItems.get(selectedNumber-1);

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        //3. Create Stock Object
        Stocks stockItem = new Stocks(currentCasheringNumber,selectedItem.getItem_no(),selectedItem.getItem_name(),quantity);

        //4 Add to temporary List
        addItemToTemporaryList(stockItem);
        displayTemporaryListItem();
    }

    public static void displayChangeQuantity(){
        System.out.printf("\n%-5s %-15s %-30s %-10s\n", "Index", "Item Number", "Item Name","Quantity");
        for ( Stocks row: temporaryList ) {
            System.out.printf("\n%-5s %-15s %-30s %-10s\n", temporaryList.indexOf(row)+1, row.getItemNumber(), row.getItemName(), row.getQuantity());
        }
        System.out.printf("\n%-5s %-15s\n",temporaryList.size()+1,"Back");
        System.out.println(" ");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice == temporaryList.size()+1){
            displayTemporaryListItem();
            return;
        }

        if(choice <= temporaryList.size()){
            System.out.print("Enter new Quantity: ");
            int quantity = sc.nextInt();
            Stocks selectedStocks = temporaryList.get(choice-1);
            selectedStocks.setQuantity(quantity);
            updateItem(temporaryList.get(choice-1),selectedStocks);
            sc.nextLine();
        }
        displayTemporaryListItem();
    }

    public static void displayRemoveItem(){
        System.out.printf("\n%-5s %-15s %-30s %-10s\n", "Index", "Item Number", "Item Name","Quantity");
        for ( Stocks row: temporaryList ) {
            System.out.printf("\n%-5s %-15s %-30s %-10s\n", temporaryList.indexOf(row)+1, row.getItemNumber(), row.getItemName(), row.getQuantity());
        }
        System.out.printf("\n%-5s %-15s\n",temporaryList.size()+1,"Back");
        System.out.println(" ");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice == temporaryList.size()+1){
            displayTemporaryListItem();
            return;
        }

        if(choice <= temporaryList.size()){
            deleteItem(temporaryList.get(choice-1));
        }
        displayTemporaryListItem();
    }
}
