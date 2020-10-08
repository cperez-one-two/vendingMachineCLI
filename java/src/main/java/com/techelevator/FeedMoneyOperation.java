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
//				if(input.contains(".")){
//					if(input.indexOf('.') < input.length() - 3){
//						throw new Exception();
//					}
//					if(input.substring(input.indexOf('.') + 1).contains(".")){
//						throw new Exception();
//					}
//					input = input.replace(".","");
//				}
//				if(input.startsWith("$")){
//					input = input.substring(1);
//				}
				//FIXME
				int deposited = Integer.parseInt(input) * 100;
				vendor.addBalance(deposited);
				return "";
			}catch(Exception e){
				return "Please enter an amount of money.";
			}
		}
	}
}
