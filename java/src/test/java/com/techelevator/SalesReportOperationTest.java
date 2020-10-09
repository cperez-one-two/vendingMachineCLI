package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class SalesReportOperationTest {
	@Test
	public void testSalesReportOperation(){
		VendingMachine emptyMachine = new VendingMachine("testinventory.csv","", 5);
		SalesReportOperation operation = new SalesReportOperation(emptyMachine);
		Assert.assertEquals("_Sales Report", operation.getName());
		Assert.assertEquals(false, operation.needsInput());
		Assert.assertEquals("", operation.operation(""));
	}
}
