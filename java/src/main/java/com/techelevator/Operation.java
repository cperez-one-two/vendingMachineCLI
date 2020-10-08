package com.techelevator;

public abstract class Operation {
	
	private String name;
	private boolean needsInput;
	protected VendingMachine vendor;
	
	public boolean needsInput(){return needsInput;}
	
	public Operation(VendingMachine vendor, String name, boolean needsInput){
		this.vendor = vendor;
		this.name = name;
		this.needsInput = needsInput;
	}
	
	public String getName(){return name;}
	
	public abstract String operation(String input);
}
