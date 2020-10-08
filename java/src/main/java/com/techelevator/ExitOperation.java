package com.techelevator;

public class ExitOperation extends Operation {

	public ExitOperation(VendingMachine vendor){
		super(vendor, "Exit", false);
	}

	@Override
	public String operation(String input) {
		vendor.exit();
		return "Exiting...";
	}

}
