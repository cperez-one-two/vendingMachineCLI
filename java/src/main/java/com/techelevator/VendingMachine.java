package com.techelevator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;

public class VendingMachine {
	private Menu mainMenu;
	private Menu purchaseMenu;
	private boolean inPurchase;
	private boolean running;
	private int balance;
	private int maxStock;
	private Map<String, Product> slots = new HashMap<String, Product>();
	private Log log;
	private int profit;
	private boolean didPurchase;

	public void exit() {
		running = false;
		log.log("Shutting down...");
	}
	public boolean isRunning(){return running;}
	
	public VendingMachine(String inventoryFileName, String logFileName, int maxStock){
		running = true;
		inPurchase = false;
		this.maxStock = maxStock;
		mainMenu = new Menu(new Operation[]{
				new DisplayOperation(this),
				new PurchaseOperation(this),
				new ExitOperation(this),
				new SalesReportOperation(this)});
		purchaseMenu = new Menu(new Operation[]{
				new FeedMoneyOperation(this),
				new SelectProductOperation(this),
				new FinishTransactionOperation(this)});
		balance = 0;
		profit = 0;
		didPurchase = false;
		log = new Log(logFileName);
		log.log("Starting up...");
		loadInventory(inventoryFileName);
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
		String logMessage = "FEED MONEY: " + formatMoney(balance);
		balance += deposited;
		logMessage += " " + formatMoney(balance);
		log.log(logMessage);
	}
	
	public String getProductList() {
		if(slots.isEmpty()){
			return "No products in stock!";
		}
		
		String output = "";
		
		String[] slotList = slots.keySet().toArray(new String[0]);
		
		Arrays.sort(slotList);
		
		for(int i = 0; i < slotList.length; i++){
			Product current = slots.get(slotList[i]);
			int quantity = current.getStock();
			int price = current.getPrice();
			String name = current.getName();
			
			output += String.format("%s: %s (%s) ", slotList[i], name, formatMoney(price));
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
		Product purchased = slots.get(selection);
		if (purchased.getStock() < 1) {
			return "No products in stock!";
		}
		if( balance < purchased.getPrice() ) {
			return "Insufficient funds. Please insert more money.";
		}
		String logMessage = String.format("%s %s %s ", purchased.getName(), selection, formatMoney(balance));
		balance -= purchased.getPrice();
		didPurchase = true;
		profit += purchased.getPrice();
		logMessage += formatMoney(balance);
		purchased.setStock( purchased.getStock() - 1);
		log.log(logMessage);
		return purchased.getSelectionNoise();
	}
	
	public String formatMoney(int cents){
		return String.format("$%d.%02d", cents/100, cents%100);
	}
	
	public void beginPurchase(){
		inPurchase = true;
	}
	
	public String endPurchase(){
		inPurchase = false;
		String goodbye = "\n";
		if(didPurchase){
			goodbye = "Thank you for your purchase!\n";
			didPurchase = false;
		}
		if(balance == 0){
			return goodbye;
		}
		
		log.log(String.format("GIVE CHANGE: %s $0.00", formatMoney(balance)));
		
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
		return change + goodbye;
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
						slots.put(params[0], new Chips(params[1], parsedPrice, maxStock));
						break;
					case "Candy":
						slots.put(params[0], new Candy(params[1], parsedPrice, maxStock));
						break;
					case "Gum":
						slots.put(params[0], new Gum(params[1], parsedPrice, maxStock));
						break;
					case "Drink":
						slots.put(params[0], new Beverage(params[1], parsedPrice, maxStock));
						break;
					default:
						System.out.println(params[3]);
						throw new Exception("Something wrong with input file.");
				}

			}
		} catch (Exception e) {
			log.log("Error loading inventory. Shutting down...");
			System.out.println("Error loading inventory.");
			System.exit(1);
		}
	}
	public void generateSalesReport() {
		String[] names = new String[slots.size()];
		int[] sold = new int[slots.size()];
		String[] slotList = slots.keySet().toArray(new String[0]);
	
		Arrays.sort(slotList);
		
		for(int i = 0; i < slotList.length; i++){
			names[i] = slots.get(slotList[i]).getName();
			sold[i] = maxStock - slots.get(slotList[i]).getStock();
		}
		
		SalesReport report = new SalesReport(names, sold, formatMoney(profit));
		log.log(report.generateSalesReport());
	}
}
