package com.techelevator;

public class DisplayOperation extends Operation {
	
	public DisplayOperation(VendingMachine vendor){
		super(vendor, "Display Vending Machine Items", false);
	}
	
	@Override
	public String operation(String input) {
		return vendor.getProductList();
	}

}
