package com.techelevator.view;


import java.util.HashMap;
import java.util.Scanner;

public class PurchaseMenu extends MainMenu {
    private double totalCost = 0.0;

    public PurchaseMenu(VendingMachine vm, MoneyChanger moneyChanger) {
        super(vm, moneyChanger);
    }

    public void displayPurchaseMenu() {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("\n1) Feed Money\n2) Select Product\n3) Finish Transaction\n"); // allow user to input whole dollar amount repeatedly
            System.out.printf("Current Money Provided: $%.2f%n", VM.getBalance());
            System.out.print("Please Enter Selection: ");
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            switch (userInput) {
                default -> System.out.println("Invalid selection. Please try again.");
                // Feed Money
                case "1" -> feedMoney();
                // Select Product
                case "2" -> {
                    HashMap<String, VendingMachineItem> items = (HashMap<String, VendingMachineItem>) VM.getInventory();
                    displayItems(items);
                    selectItem(items);
                }
                // Finish Transaction
                case "3" -> {
                    String changeMessage = getChange(); // Store the change message
                    System.out.println(changeMessage); // Print the change message
                    VM.setBalance(0.0);

                    keepGoing = false;
                }
            }
        }
    }

    public void selectItem(HashMap<String, VendingMachineItem> items) {
        System.out.print("Please select item: ");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine().toUpperCase();
        if (!items.containsKey(selection)) {
            System.out.println("Invalid Selection. Please try Again.");
        } else if (items.get(selection).getQuantity() < 1) {
            System.out.println("Out of stock. Please make a different selection.");
        } else if (VM.getBalance() < items.get(selection).getPrice()) {
            System.out.println("Insufficient funds. Please try again");
        } else {
            double itemPrice = items.get(selection).getPrice();
            totalCost += itemPrice;
            VM.dispenseItem(selection);
            displayDispenseMessage(selection, itemPrice);
        }
    }

    public void displayDispenseMessage(String selection, double itemPrice) {
        System.out.println(VM.getInventory().get(selection).getProductName() + " is $" + itemPrice + ".");
        System.out.println(VM.getInventory().get(selection).getPurchaseMessage());
        System.out.println("You have $" + String.format("%.2f", VM.getBalance()) + " remaining. Would you like anything else?");
    }
}
