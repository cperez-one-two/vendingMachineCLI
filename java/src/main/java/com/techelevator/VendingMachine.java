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
	}
	
	
	public String getDisplay(){
		if(inPurchase){
			return purchaseMenu.getDisplayString() + "\nCurrent Money Provided: " + displayBalance();
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
	public int getBalance() {
		return 0;
	}
	public String getProductList() {
		return "No products in stock!";
	}
	public String purchase(String input) {
		return "No products in stock!";
	}
	
	public String displayBalance(){
		return String.format("$%d.%d", balance/100, balance%100);
	}
	
	public void beginPurchase(){
		inPurchase = true;
	}
	
	public void endPurchase(){
		inPurchase = false;
		balance = 0;
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
					case "Beverage":
						slots.put(params[0], new Beverage(params[1], parsedPrice, MAX_STOCK));
						break;
					default:
						throw new Exception("Something wrong with input file.");
				}

			}
		} catch (Exception e) {
			System.out.println("Error loading inventory.");
			System.exit(1);
		}
	}
}
