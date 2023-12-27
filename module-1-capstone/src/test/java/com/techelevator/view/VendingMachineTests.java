package com.techelevator.view;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class VendingMachineTests {

    @Test
    public void FillSlotsTests() {
        //Arrange
        VendingMachine vm = new VendingMachine();
        //Act
        vm.fillSlots(new String[]{"A1|Potato Crisps|3.05|Chip"});
        //Assert
        assertTrue(vm.getInventory().containsKey("A1"));
        assertFalse(vm.getInventory().containsKey("E3"));
    }

    @Test
    public void FeedMoneyTests() {
        //Arrange
        VendingMachine vm = new VendingMachine();
        //Act
        vm.feedMoney(5);
        //Assert
        assertEquals(5.00, vm.getBalance(), 0);
    }

    @Test
    public void DispenseItemsTests() {
        //Arrange
        VendingMachine vm = new VendingMachine();
        //Act
        vm.fillSlots(new String[]{"A1|Potato Crisps|3.05|Chip"});
        vm.dispenseItem("A1");
        //Assert
        assertEquals(4, vm.getInventory().get("A1").getQuantity());
        assertEquals(3.05, vm.getInventory().get("A1").getPrice(), 0);
        assertEquals(-3.05, vm.getBalance(), 0);
    }

    @Test
    public void CorrectChangeTest() {
        //Arrange
        VendingMachine vm = new VendingMachine();
        MoneyChanger moneyChanger = new MoneyChanger(vm);
        // Add the item "A3" to the vending machine's inventory with sufficient quantity
        vm.getInventory().put("A4", new VendingMachineItem("Cloud Popcorn", 3.65, "Chips"));
        vm.getInventory().put("D2", new VendingMachineItem("Little League Chew", 0.95, "Gum"));
        vm.getInventory().get("A4").setQuantity(5);
        vm.getInventory().get("D2").setQuantity(5);
        //Act
        vm.setBalance(7.0);
        vm.dispenseItem("A4");
        vm.dispenseItem("D2");
        String change;
        change = moneyChanger.getChange();
        BigDecimal balanceAfterPurchase = BigDecimal.valueOf(moneyChanger.getBalance());
        //Assert
        assertEquals("Your change is: 2 Dollars 1 Quarter 1 Dime 1 Nickel", change);
        assertEquals(0.0, moneyChanger.getCurrentMoneyProvided(), 0.001);

    }
}
