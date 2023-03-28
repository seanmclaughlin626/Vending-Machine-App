package com.techelevator;

import com.techelevator.view.VendingMenu;



public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private VendingMenu menu;

	public VendingMachineCLI(VendingMenu menu) {
		this.menu = menu;
	}

	public void run() {
		boolean running = true;
		boolean purchasing = true;
		Inventory inventory = new Inventory();
		inventory.inventoryList();

		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inventory.printInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				Purchase purchase = new Purchase();
				purchasing = true;
				while(purchasing) {
					System.out.println("Current Money Provided: $" + purchase.getCurrentMoneyProvided());
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						purchase.feedMoney(purchase.feedScanner());
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
						purchase.selectProduct(inventory, purchase.selectProductScanner(inventory));
					}
					else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						purchase.finishTransaction();
						purchasing = false;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				running = false;
				System.out.println("Goodbye!");
			} else if (choice.equals(MAIN_MENU_SECRET_OPTION)){
				SalesReport salesReport = new SalesReport();
				salesReport.printSalesReport(inventory.getCurrentInventory());
			}
		}
	}

	public static void main(String[] args) {
		VendingMenu menu = new VendingMenu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
