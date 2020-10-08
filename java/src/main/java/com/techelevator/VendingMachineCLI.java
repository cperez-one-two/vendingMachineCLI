package com.techelevator;

import java.util.Scanner;

public class VendingMachineCLI {
	
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		VendingMachine vendor = new VendingMachine();
		
		while(vendor.isRunning()){
			String in = pollInput(vendor.getDisplay());
			System.out.println(vendor.select(in));
		}
	}
	
	public static String pollInput(String prompt){
		System.out.println(prompt);
		return input.nextLine();
	}
}
