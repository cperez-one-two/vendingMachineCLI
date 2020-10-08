package com.techelevator;

public class SelectProductOperation extends Operation {

	public SelectProductOperation(VendingMachine vendor){
		super(vendor, "Select Product", true);
	}

	@Override
	public String operation(String input) {
		if(input == ""){
			return "Plese enter the slot of the product you would like to purchase.";
		}else{
			return vendor.purchase(input);
		}
	}

}
