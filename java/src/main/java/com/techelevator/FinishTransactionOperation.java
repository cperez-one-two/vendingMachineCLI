package com.techelevator;

public class FinishTransactionOperation extends Operation {

	public FinishTransactionOperation(VendingMachine vendor){
		super(vendor, "Finish Transaction", false);
	}

	@Override
	public String operation(String input) {
		return vendor.endPurchase();
	}
}
