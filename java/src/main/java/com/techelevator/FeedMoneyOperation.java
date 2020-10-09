package com.techelevator;


public class FeedMoneyOperation extends Operation {

	public FeedMoneyOperation(VendingMachine vendor) {
		super(vendor, "Feed Money", true);
	}

	@Override
	public String operation(String input) {
		if(input.length() == 0){
			return "Please insert bill (1s, 2s, 5s, and 10s accepted)";
		}else{
			try{
				if(input.startsWith("$")){
					input = input.substring(1);
				}
				int deposited = Integer.parseInt(input);
				if (deposited != 1 && deposited != 2 &&
						deposited != 5 && deposited != 10) {
					return "Sorry, that bill is not accepted.";
				}
				vendor.addBalance(deposited * 100);
				return "";
			}catch(Exception e){
				return "Please enter an amount of money.";
			}
		}
	}
}
