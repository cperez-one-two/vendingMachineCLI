package com.techelevator;

public class SalesReportOperation extends Operation{

	public SalesReportOperation(VendingMachine vendor) {
		super(vendor, "_Sales Report", false);
	}

	@Override
	public String operation(String input) {
		vendor.generateSalesReport();
		return "";
	}

}
