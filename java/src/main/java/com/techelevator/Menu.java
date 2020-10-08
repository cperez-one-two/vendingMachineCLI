package com.techelevator;

public class Menu {
	private Operation[] operations;
	
	public Menu(Operation[] operations){this.operations = operations;}
	
	public String getDisplayString(){
		String display = "";
		for(int i = 0; i < operations.length; i++){
			display += String.format("(%d) %s\n", i + 1, operations[i].getName());
		}
		return display;
	}
	
	public String select(String input){
		try{
			int index = Integer.parseInt(input);
			if(index < 1 || index > operations.length){
				return "Please choose from the available options.";
			}else{
				String param = "";
				if(operations[index - 1].needsInput()){
					param = VendingMachineCLI.pollInput(operations[index - 1].operation(""));
				}
				return operations[index - 1].operation(param);
			}
		}catch(Exception e){
			return "Please enter the number of your choice.";
		}
	}
}
