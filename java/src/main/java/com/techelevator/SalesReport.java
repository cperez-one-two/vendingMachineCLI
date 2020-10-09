package com.techelevator;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalesReport {

	private File salesReportFile;
	private String productReport;

	public SalesReport(String[] products, int[] numberSold, String totalProfit) {
		productReport = "";
		for (int i = 0; i<products.length; i++ ) {
			productReport += products[i] + "|" + numberSold[i] + "\n";
		}
		productReport += "\nTOTAL SALES: " + totalProfit;
	}
	
	public String generateSalesReport() {
		DateTimeFormatter timeFormatter =
				DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		String completeFilename = 
				"SalesReport-" + LocalDateTime.now().format(timeFormatter) + ".txt";
		try {
			salesReportFile = new File(completeFilename);
			salesReportFile.createNewFile();
		} catch(Exception e) {
			return "Error generating sales report";
		}
		try (PrintWriter writer = new PrintWriter(salesReportFile)){
			writer.println(productReport);
		} catch (Exception e) {
			return "Error generating sales report";
		}
		
		return "Sales report saved to " + completeFilename;

	}

}
