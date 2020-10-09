package com.techelevator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
	private Menu mainMenu;
	private Menu purchaseMenu;
	private boolean inPurchase;
	private boolean running;
	private int balance;
	private Map<String, Product> slots = new HashMap<String, Product>();
	private static final int MAX_STOCK = 5;
	private static final String INVENTORY_FILENAME = "vendingmachine.csv";
	private static final String LOG_FILENAME = "log.log.txt";
	private Log log = new Log(LOG_FILENAME);

	public void exit() {running = false;}
	public boolean isRunning(){return running;}
	
	public VendingMachine(){
		running = true;
		inPurchase = false;
		mainMenu = new Menu(new Operation[]{
				new DisplayOperation(this),
				new PurchaseOperation(this),
				new ExitOperation(this)});
		purchaseMenu = new Menu(new Operation[]{
				new FeedMoneyOperation(this),
				new SelectProductOperation(this),
				new FinishTransactionOperation(this)});
		balance = 0;
		loadInventory(INVENTORY_FILENAME);
		log.log("log log test! log.");
	}
	
	
	public String getDisplay(){
		if(inPurchase){
			return purchaseMenu.getDisplayString() + "\nCurrent Money Provided: " + formatMoney(balance);
		}else{
			return mainMenu.getDisplayString();
		}
	}
	
	public String select(String input){
		if(inPurchase){return purchaseMenu.select(input);}
		return mainMenu.select(input);
	}
	
	public void addBalance(int deposited) {
		balance += deposited;
	}
	
	public String getProductList() {
		if(slots.isEmpty()){
			return "No products in stock!";
		}
		
		String output = "";
		
		for(String slot : slots.keySet()){
			Product current = slots.get(slot);
			int quantity = current.getStock();
			int price = current.getPrice();
			String name = current.getName();
			
			output += String.format("%s: %s (%s) ", slot, name, formatMoney(price));
			if(quantity == 0){
				output += "SOLD OUT\n";
			} else {
				output += String.format("%d\n", quantity);
			}
		}
		
		return output;
	}
	
	public String purchase(String input) {
		String selection = input.toUpperCase();
		if (!slots.containsKey(selection)) {
			return "Invalid selection. Please choose a valid slot.";
		}
		if (slots.get(selection).getStock() < 1) {
			return "No products in stock!";
		}
		if( balance < slots.get(selection).getPrice() ) {
			return "Insufficient funds. Please insert more money.";
		}
		balance -= slots.get(selection).getPrice();
		slots.get(selection).setStock( slots.get(selection).getStock() - 1);
		return slots.get(selection).getSelectionNoise();
	}
	
	public String formatMoney(int cents){
		return String.format("$%d.%02d", cents/100, cents%100);
	}
	
	//TODO
	public void beginPurchase(){
		inPurchase = true;
	}
	
	//TODO
	public String endPurchase(){
		inPurchase = false;
		if(balance == 0){
			return "Thank you for your purchase!";
		}
		int quarters = balance/25;
		balance -= quarters*25;
		int dimes = balance/10;
		balance -= dimes*10;
		int nickels = balance/5;
		
		String change = "Here's your change:\n";
		if(quarters > 0){
			change += String.format("%d quarters\n", quarters);
		}
		if(dimes > 0){
			change += String.format("%d dimes\n", dimes);
		}
		if(nickels > 0){
			change += String.format("%d nickels\n", nickels);
		}
		
		balance = 0;
		return change + "Thank you for your purchase!\n";
	}
	
	private void loadInventory(String filename) {
		
		File inventoryFile = new File(filename);
		try (Scanner scanner = new Scanner(inventoryFile)) {
			while(scanner.hasNext()) {
				String currentLine = scanner.nextLine();
				String[] params = currentLine.split("\\|");
				int parsedPrice = Integer.parseInt(params[2].replace(".", ""));
				switch(params[3]) {
					case "Chip":
						slots.put(params[0], new Chips(params[1], parsedPrice, MAX_STOCK));
						break;
					case "Candy":
						slots.put(params[0], new Candy(params[1], parsedPrice, MAX_STOCK));
						break;
					case "Gum":
						slots.put(params[0], new Gum(params[1], parsedPrice, MAX_STOCK));
						break;
					case "Drink":
						slots.put(params[0], new Beverage(params[1], parsedPrice, MAX_STOCK));
						break;
					default:
						System.out.println(params[3]);
						throw new Exception("Something wrong with input file.");
				}

			}
		} catch (Exception e) {
			System.out.println("Error loading inventory.");
			System.exit(1);
		}
	}
}
