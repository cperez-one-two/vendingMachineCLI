package com.techelevator;

public class Beverage extends Product {

	public Beverage(String name, int price, int stock) {
		super(name, price, stock);
	}
	
	@Override
	public String getSelectionNoise() {
		return "Glug Glug, Yum!";
	}


}
