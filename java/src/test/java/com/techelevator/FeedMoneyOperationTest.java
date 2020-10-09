package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class FeedMoneyOperationTest {
	@Test
	public void testFeedMoneyOperation(){
		VendingMachine emptyMachine = new VendingMachine("testinventory.csv","", 5);
		FeedMoneyOperation operation = new FeedMoneyOperation(emptyMachine);
		Assert.assertEquals("Feed Money", operation.getName());
		Assert.assertEquals(true, operation.needsInput());
		Assert.assertEquals("Please insert bill (1s, 2s, 5s, and 10s accepted)", operation.operation(""));
		Assert.assertEquals("", operation.operation("2"));
		Assert.assertEquals("", operation.operation("$1"));
		Assert.assertEquals("Please enter an amount of money.", operation.operation("q"));
		Assert.assertEquals("Sorry, that bill is not accepted.", operation.operation("3"));
	}

}
