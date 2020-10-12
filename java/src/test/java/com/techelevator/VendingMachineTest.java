package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class VendingMachineTest {
	@Test
	public void testVendingMachine(){
		VendingMachine vendor = new VendingMachine("testinventory.csv", "", 1);

		Assert.assertEquals(vendor.getDisplayHeader() + "\nA1: Test ($1.00) 1\n", vendor.getProductList());
		Assert.assertEquals("\n", vendor.endPurchase());
		vendor.addBalance(100);
		Assert.assertEquals("Here's your change:\n4 quarters\n\n", vendor.endPurchase());
		Assert.assertEquals("Invalid selection. Please choose a valid slot.\n", vendor.purchase("A2"));
		Assert.assertEquals("Insufficient funds. Please insert more money.\n", vendor.purchase("A1"));
		vendor.addBalance(100);
		vendor.purchase("A1");
		Assert.assertEquals("Thank you for your purchase!\n", vendor.endPurchase());
		Assert.assertEquals("$1.00", vendor.formatMoney(100));
		Assert.assertEquals("$0.50", vendor.formatMoney(50));
		Assert.assertEquals("$2.50", vendor.formatMoney(250));
		Assert.assertEquals(vendor.getDisplayHeader() + "\nA1: Test ($1.00) SOLD OUT\n", vendor.getProductList());
	}
}
