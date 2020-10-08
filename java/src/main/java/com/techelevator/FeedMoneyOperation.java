package com.techelevator;

public class FeedMoneyOperation extends Operation {

	public FeedMoneyOperation(VendingMachine vendor) {
		super(vendor, "Feed Money", true);
	}

	@Override
	public String operation(String input) {
		if(input.length() == 0){
			return "How much are you depositing?";
		}else{
			try{
				int deposited = Integer.parseInt(input);
				vendor.addBalance(deposited);
				return "";
			}catch(Exception e){
				return "Please enter an amount of money.";
			}
		}
	}
}
