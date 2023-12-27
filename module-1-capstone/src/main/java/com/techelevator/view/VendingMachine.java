package com.techelevator.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
    LocalDateTime currentTime = LocalDateTime.now();
    private double balance;
    private final Map<String, VendingMachineItem> inventory = new HashMap<>();

    public VendingMachine() {

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Map<String, VendingMachineItem> getInventory() {
        return inventory;
    }

    public void fillSlots(String[] stockList) {
        for (String line : stockList) {
            String[] item = line.split("\\|");
            inventory.put(item[0], new VendingMachineItem(item[1], Double.parseDouble(item[2]), item[3]));
        }
    }

    public void dispenseItem(String selection) {
        balance -= inventory.get(selection).getPrice();
        inventory.get(selection).setQuantity(inventory.get(selection).getQuantity() - 1);
        Auditor.Audit((dateFormatter.format(currentTime) + (inventory.get(selection).getProductName() + " " + selection + " " + (balance + inventory.get(selection).getPrice()) + " " + String.format("%.2f", balance))));
    }

    public void feedMoney(int money) {
        balance += money;
        Auditor.Audit(dateFormatter.format(currentTime) + " FEED MONEY: " + money + " " + balance);
    }
}

