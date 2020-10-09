package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class MenuTest {
	@Test
	public void testMenu(){
		Menu menu = new Menu(new Operation[0]);
		
		Assert.assertEquals("", menu.getDisplayString());
		Assert.assertEquals("Please choose from the available options.", menu.select("1"));
		Assert.assertEquals("Please enter the number of your choice.", menu.select("afoin"));
	}
}
