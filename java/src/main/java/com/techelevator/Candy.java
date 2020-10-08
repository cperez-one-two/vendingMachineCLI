package com.techelevator;

public class Candy extends Product {

	public Candy(String name, int price, int stock) {
		super(name, price, stock);
	}

	@Override
	public String getSelectionNoise() {
		return "Munch Munch, Yum!";
	}

}
