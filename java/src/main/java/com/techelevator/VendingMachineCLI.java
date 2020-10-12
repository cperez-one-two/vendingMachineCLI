package com.techelevator;

import java.util.Scanner;

public class VendingMachineCLI {
	
	private static Scanner input = new Scanner(System.in);
	private static final int MAX_STOCK = 5;
	private static final String INVENTORY_FILENAME = "vendingmachine.csv";
	private static final String LOG_FILENAME = "Log.txt";

	public static void main(String[] args) {
		
		VendingMachine vendor = new VendingMachine(INVENTORY_FILENAME, LOG_FILENAME, MAX_STOCK);
		
		while(vendor.isRunning()){
			String in = pollInput(vendor.getDisplay());
			System.out.println(vendor.select(in));
		}
		input.close();
	}
	
	public static String pollInput(String prompt){
		System.out.println(prompt);
		System.out.print(">> ");
		return input.nextLine();
	}
}
