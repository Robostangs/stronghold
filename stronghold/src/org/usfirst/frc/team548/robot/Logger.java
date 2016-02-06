package org.usfirst.frc.team548.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Logger {
	private static Logger instance = null;
	private static BufferedWriter writer;
	private static File file;
	
	public static Logger getInstance(){
		if(instance == null){
			instance = new Logger();
		}
		return instance;
	}
	
	private Logger(){
		file = new File(Calendar.getInstance().getTime().toString() + " log.txt");
		try {
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file, true));
			this.log("init", "robot init");
		} catch (IOException e) {
			System.out.println("why is this not working");
			e.printStackTrace();
		}
	}
	
	public void log(String key, String log) throws IOException{
		writer.write(Calendar.getInstance().getTime().toString() + " " + key + ": " + log);
	}
	
	public void closeWriter() throws IOException{
		writer.close();
	}
}
