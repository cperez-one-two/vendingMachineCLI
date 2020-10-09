package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class FinishTransactionOperationTest {
	@Test
	public void testFinishTransactionOperation(){
		VendingMachine emptyMachine = new VendingMachine("testinventory.csv","", 5);
		FinishTransactionOperation operation = new FinishTransactionOperation(emptyMachine);
		Assert.assertEquals("Finish Transaction", operation.getName());
		Assert.assertEquals(false, operation.needsInput());
		Assert.assertEquals(emptyMachine.endPurchase(), operation.operation(""));
	}
}
