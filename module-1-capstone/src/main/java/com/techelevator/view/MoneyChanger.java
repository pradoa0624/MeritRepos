package com.techelevator.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoneyChanger {

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a"); // set format for log
    LocalDateTime currentTime = LocalDateTime.now(); // pull in current time
    private double currentMoneyProvided; // variable to hold money provided
    public VendingMachine VM;

    public MoneyChanger(VendingMachine VM) {
        this.VM = VM;
    }

    public void feedMoney() {
        Scanner scanner = new Scanner(System.in); // Scanner to read input
        boolean continueFeedingMoney = true; // when false, breaks out of loop
        while (continueFeedingMoney) {
            System.out.print("Please deposit money from your bank (Whole Dollars Only): ");
            try { // try block for user input
                double moneyProvided = Double.parseDouble(scanner.nextLine()); // double to hold fed money
                if (moneyProvided % 1 == 0) { // make sure moneyProvided is a whole number
                    currentMoneyProvided += moneyProvided; // hold moneyProvided in system
                    VM.setBalance(currentMoneyProvided); // Update the balance in VendingMachine
                    System.out.println("Current money provided: $" + currentMoneyProvided);
                    System.out.println("Would you like to feed additional money? (y/n)");
                    String answer = scanner.nextLine();
                    if (answer.toLowerCase().contains("n")) {
                        continueFeedingMoney = false; // if n, breaks out of loop
                    } else if (answer.toLowerCase().contains("y")) {
                        feedMoney(); // if y, go back to feedMoney();
                        break; // Exit the loop if the user enters anything other than 'y'
                    }
                }
            } catch (NumberFormatException e) { // catch incorrect input by user
                System.out.println("Please provide only whole dollar amounts");
                scanner.nextLine(); // Consume invalid input to prevent infinite loop
            }
        }
    }

    // get balance from VendingMachine
    public double getBalance() {
        return VM.getBalance();
    }

    public double getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public String getChange() {
        List<Coin> changes = new ArrayList<>();
        BigDecimal balance = BigDecimal.valueOf(getBalance()); // Use BigDecimal for balance

        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            // Calculate the number of each coin
            BigDecimal dollars = balance.divide(Coin.DOLLAR.getCoinType(), 0, RoundingMode.FLOOR);
            balance = balance.subtract(dollars.multiply(Coin.DOLLAR.getCoinType()));

            BigDecimal quarters = balance.divide(Coin.QUARTER.getCoinType(), 0, RoundingMode.FLOOR);
            balance = balance.subtract(quarters.multiply(Coin.QUARTER.getCoinType()));

            BigDecimal dimes = balance.divide(Coin.DIME.getCoinType(), 0, RoundingMode.FLOOR);
            balance = balance.subtract(dimes.multiply(Coin.DIME.getCoinType()));

            BigDecimal nickels = balance.divide(Coin.NICKEL.getCoinType(), 0, RoundingMode.FLOOR);
            balance = balance.subtract(nickels.multiply(Coin.NICKEL.getCoinType()));

            // Add the respective coins to the changes list
            addCoinsToList(changes, Coin.DOLLAR, dollars.intValue());
            addCoinsToList(changes, Coin.QUARTER, quarters.intValue());
            addCoinsToList(changes, Coin.DIME, dimes.intValue());
            addCoinsToList(changes, Coin.NICKEL, nickels.intValue());
        }

        // Format the output
        StringBuilder changeMessage = new StringBuilder("Your change is:");

        // Count the different types of coins
        int dollarCount = 0;
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;

        // Add Change to counts
        for (Coin coin : changes) {
            switch (coin) {
                case DOLLAR -> dollarCount++;
                case QUARTER -> quarterCount++;
                case DIME -> dimeCount++;
                case NICKEL -> nickelCount++;
            }
        }

        // Add dollar count to the message
        if (dollarCount > 0) {
            changeMessage.append(" ").append(dollarCount).append(dollarCount > 1 ? " Dollars" : " Dollar");
        }
        // Add quarter count to the message
        if (quarterCount > 0) {
            changeMessage.append(" ").append(quarterCount).append(quarterCount > 1 ? " Quarters" : " Quarter");
        }
        // Add dime count to the message
        if (dimeCount > 0) {
            changeMessage.append(" ").append(dimeCount).append(dimeCount > 1 ? " Dimes" : " Dime");
        }
        // Add nickel count to the message
        if (nickelCount > 0) {
            changeMessage.append(" ").append(nickelCount).append(nickelCount > 1 ? " Nickels" : " Nickel");
        }

        Auditor.Audit(String.format(dateFormatter.format(currentTime) + " GIVE CHANGE: %.2f", currentMoneyProvided));

        return changeMessage.toString();
    }

    private void addCoinsToList(List<Coin> list, Coin coin, int count) {
        for (int i = 0; i < count; i++) {
            list.add(coin);
        }
    }
}


