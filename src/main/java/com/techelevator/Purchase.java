package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Purchase {
    private final BigDecimal NICKEL = new BigDecimal("0.05");
    private final BigDecimal DIME = new BigDecimal("0.10");
    private final BigDecimal QUARTER = new BigDecimal("0.25");
    private BigDecimal currentMoneyProvided = new BigDecimal("0.00");
    File logFile = new File ("Log.txt");

    public BigDecimal getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public int feedScanner(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter money in whole dollar amount \n Enter 0 to return to previous page. ");
        int fedMoney = input.nextInt();
        return fedMoney;
    }

    public BigDecimal feedMoney(int fedMoney) {
        try {
            if(fedMoney >= 0) {
                currentMoneyProvided = currentMoneyProvided.add(new BigDecimal(fedMoney));
                try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile, true))) {
                    logWriter.println(LocalDate.now() + " " + LocalTime.now()+ " FEED MONEY: $" + fedMoney + " $" + currentMoneyProvided);
                } catch (FileNotFoundException fileError) {
                    System.err.println("Error: file not found.");
                }
            } else {
                System.out.println("Please enter a positive number");
            }
        } catch (InputMismatchException e) {
            System.err.println("Error: invalid type");
        }
        return currentMoneyProvided;
    }

    public String selectProductScanner(Inventory inventory){
        Scanner itemInput = new Scanner(System.in);
        inventory.printInventory();
        System.out.println();
        System.out.println("Select your item by ID: ");
        System.out.println("Enter 0 to return to purchase menu");
        String choice = itemInput.nextLine();

        return choice;
    }

    public BigDecimal selectProduct(Inventory inventory, String choice){
        Map<String, InventoryItem> invMap = new HashMap<>();
        for(InventoryItem item : inventory.getCurrentInventory()){
            invMap.put(item.getSlotLocation(), item);
        }
        if(!choice.equals("0")) {
            if (invMap.containsKey(choice.toUpperCase())) {
                if (invMap.get(choice.toUpperCase()).getItemsRemaining() > 0) {
                    if (currentMoneyProvided.compareTo(invMap.get(choice.toUpperCase()).getPrice()) == 1
                            || currentMoneyProvided.compareTo(invMap.get(choice.toUpperCase()).getPrice()) == 0) {
                        currentMoneyProvided = currentMoneyProvided.subtract(invMap.get(choice.toUpperCase()).getPrice());
                        invMap.get(choice.toUpperCase()).dispenseItem();
                        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile, true))) {
                            logWriter.println(LocalDate.now() + " " + LocalTime.now() + " " + invMap.get(choice.toUpperCase()).getName() + " "
                                    + invMap.get(choice.toUpperCase()).getSlotLocation() + " $" + invMap.get(choice.toUpperCase()).getPrice() + " $" + currentMoneyProvided);
                        } catch (FileNotFoundException fileError) {
                            System.err.println("Error: file not found.");
                        }
                    } else {
                        System.out.println("Please feed in more money to purchase this item");
                    }

                } else {
                    System.out.println("Item sold out. Please select another item.");
                }
            } else {
                System.out.println("Item not found. Please select a valid item.");
            }
        } else {
            System.out.println();
        }
        return currentMoneyProvided;
    }

    public BigDecimal finishTransaction(){
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        BigDecimal changeGiven = new BigDecimal(0);

        while(currentMoneyProvided.compareTo(QUARTER) == 0 || currentMoneyProvided.compareTo(QUARTER) == 1){
            currentMoneyProvided = currentMoneyProvided.subtract(QUARTER);
            changeGiven = changeGiven.add(QUARTER);
            quarterCount++;
        }
        while(currentMoneyProvided.compareTo(DIME) == 0 || currentMoneyProvided.compareTo(DIME) == 1){
            currentMoneyProvided = currentMoneyProvided.subtract(DIME);
            changeGiven = changeGiven.add(DIME);
            dimeCount++;
        }
        while(currentMoneyProvided.compareTo(NICKEL) == 0 || currentMoneyProvided.compareTo(NICKEL) == 1){
            currentMoneyProvided = currentMoneyProvided.subtract(NICKEL);
            changeGiven = changeGiven.add(NICKEL);
            nickelCount++;
        }
        System.out.println("Your change is:");
        System.out.println("Quarters: " + quarterCount);
        System.out.println("Dimes: " + dimeCount);
        System.out.println("Nickels: " + nickelCount);
        try (PrintWriter logWriter = new PrintWriter( new FileOutputStream(logFile, true))){
            logWriter.println(LocalDate.now() + " " + LocalTime.now()+ " " + "GIVE CHANGE" + " $" + changeGiven + " $" + currentMoneyProvided);
        } catch(FileNotFoundException fileError){
            System.err.println("Error: file not found.");
        }
        return currentMoneyProvided;
    }
}
