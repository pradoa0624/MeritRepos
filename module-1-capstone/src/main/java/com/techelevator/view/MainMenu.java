package com.techelevator.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MainMenu {

    public VendingMachine VM;
    private MoneyChanger moneyChanger; // Declare MoneyChanger instance

    public MainMenu(VendingMachine vm, MoneyChanger moneyChanger) {
        this.VM = vm;
        this.moneyChanger = moneyChanger;
    }

    public void displayMainMenu() {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println();
            displayLogo();
            System.out.println("1) Display Vending Machine Items\n2) Purchase\n3) Exit");
            System.out.print("Please Enter Selection: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                default -> System.out.println("Invalid Selection. Please try again.");
                // Display Vending Machine Items
                case "1" -> {
                    Map<String, VendingMachineItem> items = VM.getInventory();
                    displayItems((HashMap<String, VendingMachineItem>) items);
                }
                // Purchase
                case "2" -> {
                    PurchaseMenu pm = new PurchaseMenu(VM, moneyChanger);
                    pm.displayPurchaseMenu();
                }
                // Exit
                case "3" -> keepGoing = false;

            }
        }
    }

    public void feedMoney() {
        moneyChanger.feedMoney(); // Call the feedMoney method of MoneyChanger
    }

    public void displayLogo() {
        String vendoMaticLogo = "\n" +
                "                      _                          _   _         ___   ___   ___  \n" +
                " /\\   /\\___ _ __   __| | ___         /\\/\\   __ _| |_(_) ___   ( _ ) / _ \\ / _ \\ \n" +
                " \\ \\ / / _ \\ '_ \\ / _` |/ _ \\ _____ /    \\ / _` | __| |/ __|  / _ \\| | | | | | |\n" +
                "  \\ V /  __/ | | | (_| | (_) |_____/ /\\/\\ \\ (_| | |_| | (__  | (_) | |_| | |_| |\n" +
                "   \\_/ \\___|_| |_|\\__,_|\\___/      \\/    \\/\\__,_|\\__|_|\\___|  \\___/ \\___/ \\___/ \n" +
                "                                                                                \n";

        System.out.println(vendoMaticLogo);
    }

    // This code Displays Items for Display Items in main menu, as well as in purchase menu
    public void displayItems(HashMap<String, VendingMachineItem> items) {

        for (Map.Entry<String, VendingMachineItem> entry : items.entrySet()) {
            var quantity = "";
            if (entry.getValue().getQuantity() == 0) {
                quantity = "SOLD OUT";
            } else {
                quantity = Integer.toString(entry.getValue().getQuantity());
            }
            System.out.printf("%-5s %-18s $%-5.2f quantity remaining: (%s)\n", entry.getKey(), entry.getValue().getProductName(), entry.getValue().getPrice(), quantity);

        }
        System.out.println("\t\t------------------------------------------------");
    }

    protected MoneyChanger getMoneyChanger() {
        return moneyChanger;
    }
    public void setMoneyChanger(MoneyChanger moneyChanger) {
        this.moneyChanger = moneyChanger;
    }

    public String getChange() {
        return moneyChanger.getChange(); // Call the getChange method of MoneyChanger
    }

}


