package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

	private File logFile;
	
	public Log(String logFilePath) {
		if(logFilePath.length() > 0){
			logFile = new File(logFilePath);
			try {
				logFile.createNewFile();
			} catch (Exception e) {
				System.out.println("Couldn't create log file");
				System.exit(1);
			}
		}else{
			logFile = null;
		}
	}
	
	public void log(String message) {
		if(logFile != null){
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");
			String finalMessage = LocalDateTime.now().format(timeFormatter) + " " + message;
			
			try(PrintWriter output = 
					new PrintWriter(new FileOutputStream(logFile, true))) {
				output.write(finalMessage + "\n");
				
			} catch (FileNotFoundException e) {
				System.out.println("Couldn't write to log file!");
				System.exit(1);
			}
		}
	}
}
