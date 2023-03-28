package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    List<InventoryItem> currentInventory = new ArrayList<>();

    public List<InventoryItem> getCurrentInventory() {
        return currentInventory;
    }

    //    Method to populate inventory from vendingmachine.csv file
    public List<InventoryItem> inventoryList(){
        File inventoryFile = new File("vendingmachine.csv");
        try(Scanner inventoryScanner = new Scanner(inventoryFile)){
            while (inventoryScanner.hasNextLine()) {
                String inputLine = inventoryScanner.nextLine();
                String[] itemArray = inputLine.split("\\|");
                currentInventory.add(new InventoryItem(itemArray[0], itemArray[1], new BigDecimal(itemArray[2]), itemArray[3]));
            }
        } catch (FileNotFoundException e){
            System.err.println("File not found :(");
        }
        return currentInventory;
    }

    public void printInventory(){
        for(InventoryItem item : currentInventory){
            System.out.println(item.getSlotLocation() + " | " + item.getName() + " | "
                    + "$" + item.getPrice() + " | " + item.getItemsRemaining() + " remaining");
        }
    }
}
