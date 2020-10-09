package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class ChipsTest {

	@Test
	public void testChips() {

		Chips testChips = new Chips("Funyuns", 300, 5);
		Assert.assertEquals("Crunch Crunch, Yum!", testChips.getSelectionNoise());
		Assert.assertEquals("Funyuns", testChips.getName());
		Assert.assertEquals(300, testChips.getPrice());
		Assert.assertEquals(5, testChips.getStock());

	}
}
