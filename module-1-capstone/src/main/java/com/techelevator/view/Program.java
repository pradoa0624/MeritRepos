package com.techelevator.view;

public class Program {
    static Stocker stocker;
    static MoneyChanger moneyChanger;
    static VendingMachine vm;
    static MainMenu menu;

    public static void main(String[] args) {
        vm = new VendingMachine(); // Create VendingMachine first
        moneyChanger = new MoneyChanger(vm); // Create MoneyChanger with VendingMachine instance
        stocker = new Stocker();
        menu = new MainMenu(vm, moneyChanger); // Create MainMenu with VendingMachine and MoneyChanger
        menu.setMoneyChanger(moneyChanger);
        String[] stockList = stocker.getStock();
        vm.fillSlots(stockList);
        menu.displayMainMenu();
    }
}


