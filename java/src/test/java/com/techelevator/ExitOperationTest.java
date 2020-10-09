package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class ExitOperationTest {
	@Test
	public void testExitOperation(){
		VendingMachine emptyMachine = new VendingMachine("testinventory.csv","", 5);
		ExitOperation operation = new ExitOperation(emptyMachine);
		Assert.assertEquals("Exit", operation.getName());
		Assert.assertEquals(false, operation.needsInput());
		Assert.assertEquals("Exiting...", operation.operation(""));
	}

}
