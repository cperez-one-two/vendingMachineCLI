package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class GumTest {

	@Test
	public void testGum() {

		Gum testGum = new Gum("Trident", 300, 5);
		Assert.assertEquals("Chew Chew, Yum!", testGum.getSelectionNoise());
		Assert.assertEquals("Trident", testGum.getName());
		Assert.assertEquals(300, testGum.getPrice());
		Assert.assertEquals(5, testGum.getStock());

	}

}
