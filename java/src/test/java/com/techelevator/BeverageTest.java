package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class BeverageTest {

	@Test
	public void testBeverage() {
		
		Beverage testBeverage = new Beverage("Dr. Pepper", 300, 5);
		Assert.assertEquals("Glug Glug, Yum!", testBeverage.getSelectionNoise());
		Assert.assertEquals("Dr. Pepper", testBeverage.getName());
		Assert.assertEquals(300, testBeverage.getPrice());
		Assert.assertEquals(5, testBeverage.getStock());

	}
}
