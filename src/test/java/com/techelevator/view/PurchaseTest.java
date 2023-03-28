package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.Purchase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PurchaseTest {

//    Testing feed money
Purchase purchase = new Purchase();
Inventory inventory = new Inventory();

    @Test
    public void fedMoneyIsNegative(){
        int fedMoney = -2;
        BigDecimal expected = new BigDecimal(0);

        BigDecimal result = purchase.feedMoney(fedMoney);

        Assert.assertEquals(expected.compareTo(result), 0);
    }
    @Test
    public void fedMoneyIsPositive(){
        int fedMoney = 2;
        BigDecimal expected = new BigDecimal(2);

        BigDecimal result = purchase.feedMoney(fedMoney);

        Assert.assertEquals(expected.compareTo(result), 0);
    }

//    Testing selectProduct
    @Test
    public void choiceMatchesItem(){
//        Arrange
        inventory.inventoryList();
        purchase.feedMoney(5);
        BigDecimal expected = new BigDecimal("3.50");
//        Act
        BigDecimal result = purchase.selectProduct(inventory,"C2");
//        Assert
        Assert.assertEquals(expected.compareTo(result), 0);
    }
    @Test
    public void notEnoughMoney(){
//        Arrange
        inventory.inventoryList();
        purchase.feedMoney(1);
        BigDecimal expected = new BigDecimal("1");
//        Act
        BigDecimal result = purchase.selectProduct(inventory,"C2");
//        Assert
        Assert.assertEquals(expected.compareTo(result), 0);
    }
    @Test
    public void caseDoesntMatch(){
//        Arrange
        inventory.inventoryList();
        purchase.feedMoney(5);
        BigDecimal expected = new BigDecimal("3.50");
//        Act
        BigDecimal result = purchase.selectProduct(inventory,"c2");
//        Assert
        Assert.assertEquals(expected.compareTo(result), 0);
    }
    @Test
    public void codeNotFound(){
//        Arrange
        inventory.inventoryList();
        purchase.feedMoney(5);
        BigDecimal expected = new BigDecimal("5");
//        Act
        BigDecimal result = purchase.selectProduct(inventory,"aaaaaaahhhhh");
//        Assert
        Assert.assertEquals(expected.compareTo(result), 0);
    }
//    Testing finishTransaction
    @Test
    public void finalBalanceIsZero(){
//        Arrange
        inventory.inventoryList();
        purchase.feedMoney(5);
        BigDecimal expected = new BigDecimal("0.00");
//        Act
        BigDecimal result = purchase.finishTransaction();
//        Assert
        Assert.assertEquals(expected.compareTo(result), 0);
    }
}
