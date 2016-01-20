package org.usfirst.frc.team548.robot;

public class Autonomous {

	private static Autonomous instance = null;
	private static int autoMode = 1;
	
	public Autonomous() {
		
	}
	
	public static Autonomous getInstance(){
		if(instance == null){
			instance = new Autonomous();
		}
		return instance;
	}
	
	public static void setAutoMode(int mode) {
		Autonomous.autoMode = mode;
	}
	
	public static int getAutoMode() {
		return Autonomous.autoMode;
	}
	
	public static void run() {
		
		switch(Autonomous.getAutoMode()) {
		
		case 1:
			//auto options goes here
			break;
		//case 2: more options can go here
		default:
			//default auto goes here
			break;
		}
	}
}
