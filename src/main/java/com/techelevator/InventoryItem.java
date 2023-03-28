package com.techelevator;

import java.math.BigDecimal;

public class InventoryItem extends Inventory {
    private final int CAPACITY = 5;
    private String slotLocation;
    private String name;
    private BigDecimal price;
    private String type;
    private int itemsRemaining = CAPACITY;

    public InventoryItem(String slotLocation, String name, BigDecimal price, String type) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getItemsRemaining() {
        return itemsRemaining;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public int dispenseItem(){
    switch (this.type){
        case "Chip":
            System.out.println("Crunch Crunch, It's Yummy!");
            break;
        case "Candy":
            System.out.println("Munch Munch, Mmm Mmm Good!");
            break;
        case "Drink":
            System.out.println("Glug Glug, Chug Chug!");
            break;
        case "Gum":
            System.out.println("Chew Chew, Pop!");
            break;
    }
    this.itemsRemaining--;
    return itemsRemaining;
}
}
