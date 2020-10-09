package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class DisplayOperationTest {
	@Test
	public void testDisplayOperation(){
		VendingMachine emptyMachine = new VendingMachine("testinventory.csv","", 5);
		DisplayOperation operation = new DisplayOperation(emptyMachine);
		Assert.assertEquals("Display Vending Machine Items", operation.getName());
		Assert.assertEquals(false, operation.needsInput());
		Assert.assertEquals(emptyMachine.getProductList(), operation.operation(""));
	}
}
