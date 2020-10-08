package com.techelevator;

public class Chips extends Product {

	public Chips(String name, int price, int stock) {
		super(name, price, stock);
	}

	@Override
	public String getSelectionNoise() {
		return "Crunch Crunch, Yum!";
	}

}
