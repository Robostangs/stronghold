package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class Ingesting {

	private static Ingesting instance = null;
	private static CANTalon rollerMotor;
	
	public static Ingesting getInstance(){
		if(instance == null){
			instance = new Ingesting();
		}
		return instance;
	}
	
	private Ingesting() {
		rollerMotor = new CANTalon(Constants.ROLLER_TALON_POS);
	}
	
	public static void setRoller(double value) {
		rollerMotor.set(value);
	}
	
	public static void stopRoller() {
		setRoller(0);
	}
	
	
	public static void rollerIn() {
		setRoller(Constants.ROLLER_IN_NORMAL_POWER);
	}
	
	public static void rollerOut() {
		setRoller(Constants.ROLLER_OUT_NORMAL_POWER);
	}
	
}
