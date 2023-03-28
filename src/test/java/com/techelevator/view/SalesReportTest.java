package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.Purchase;
import com.techelevator.SalesReport;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class SalesReportTest {
    SalesReport salesReport = new SalesReport();
    Inventory inventory = new Inventory();
    Purchase purchase = new Purchase();
//    Testing printSalesReport
    @Test
    public void reportsSalesMade(){
//        Arrange
        inventory.inventoryList();
        purchase.feedMoney(5);
        purchase.selectProduct(inventory, "C2");
        purchase.selectProduct(inventory, "d4");
        BigDecimal expected = new BigDecimal(2.25);
//       Act
        BigDecimal result = salesReport.printSalesReport(inventory.getCurrentInventory());
//       Assert
        Assert.assertEquals(expected.compareTo(result), 0);
    }
    @Test
    public void reportsNoSalesMade(){
//        Arrange
        inventory.inventoryList();
        BigDecimal expected = new BigDecimal(0);
//       Act
        BigDecimal result = salesReport.printSalesReport(inventory.getCurrentInventory());
//       Assert
        Assert.assertEquals(expected.compareTo(result), 0);
    }
}
