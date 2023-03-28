package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesReport {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyy-HH.mm.ss");
    LocalDateTime myDateObj = LocalDateTime.now();
    String formattedDate = myDateObj.format(formatter);


    private String fileName = "SalesReport" + formattedDate + ".txt";

   private File salesReport = new File(fileName);

    public BigDecimal printSalesReport(List<InventoryItem> finalInventory){
        BigDecimal totalSales = new BigDecimal("0.00");


        try(PrintWriter reportWriter = new PrintWriter(salesReport)){
            for(InventoryItem item : finalInventory){
                BigDecimal amountSold = new BigDecimal(item.getCAPACITY() - item.getItemsRemaining());
                reportWriter.println(item.getName() + "|" + amountSold);
                totalSales = totalSales.add((amountSold.multiply(item.getPrice())));
            }
            reportWriter.println();
            reportWriter.println("**TOTAL SALES** $" + totalSales);
            System.out.println("Sales report generated!");
        } catch (FileNotFoundException e){
            System.out.println("File not found :(");
        }
        catch (Exception e){
            System.out.println("Something went wrong");
        }
        return totalSales;
    }
}
