package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class PurchaseOperationTest {
	@Test
	public void testPurchaseOperation(){
		VendingMachine emptyMachine = new VendingMachine("testinventory.csv","", 5);
		PurchaseOperation operation = new PurchaseOperation(emptyMachine);
		Assert.assertEquals("Purchase", operation.getName());
		Assert.assertEquals(false, operation.needsInput());
		Assert.assertEquals("", operation.operation(""));
	}
}
