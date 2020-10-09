package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class CandyTest {

	@Test
	public void testCandy() {

		Candy testCandy = new Candy("Kit-Kat", 300, 5);
		Assert.assertEquals("Munch Munch, Yum!", testCandy.getSelectionNoise());
		Assert.assertEquals("Kit-Kat", testCandy.getName());
		Assert.assertEquals(300, testCandy.getPrice());
		Assert.assertEquals(5, testCandy.getStock());

	}
}
