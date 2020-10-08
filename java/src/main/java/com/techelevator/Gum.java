package com.techelevator;

public class Gum extends Product {

	public Gum(String name, int price, int stock) {
		super(name, price, stock);
	}

	@Override
	public String getSelectionNoise() {
		return "Chew Chew, Yum!";
	}

}
