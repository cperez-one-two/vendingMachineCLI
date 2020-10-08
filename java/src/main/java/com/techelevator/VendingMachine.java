package com.techelevator;

public class VendingMachine {
	private Menu mainMenu;
	private Menu purchaseMenu;
	private boolean inPurchase;
	private boolean running;
	private int balance;

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
}
