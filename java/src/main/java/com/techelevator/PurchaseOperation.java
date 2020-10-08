package com.techelevator;

public class PurchaseOperation extends Operation {

	public PurchaseOperation(VendingMachine vendor){
		super(vendor, "Purchase", false);
	}

	@Override
	public String operation(String input) {
		vendor.beginPurchase();
		return "";
	}

}
