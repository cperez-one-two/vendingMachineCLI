package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class SelectProductOperationTest {
	@Test
	public void testSelectProductOperation(){
		VendingMachine emptyMachine = new VendingMachine("testinventory.csv","", 5);
		SelectProductOperation operation = new SelectProductOperation(emptyMachine);
		Assert.assertEquals("Select Product", operation.getName());
		Assert.assertEquals(true, operation.needsInput());
		Assert.assertEquals("Please enter the slot of the product you would like to purchase.", operation.operation(""));
	}
}
