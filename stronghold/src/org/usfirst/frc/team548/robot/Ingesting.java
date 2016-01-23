package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Ingesting {

	private static Ingesting instance = null;
	private static CANTalon rollerMotor;
	private static DigitalInput ballSwitch;
	
	public static Ingesting getInstance(){
		if(instance == null){
			instance = new Ingesting();
		}
		return instance;
	}
	
	private Ingesting() {
		rollerMotor = new CANTalon(Constants.ING_TALON_POS);
		ballSwitch = new DigitalInput(Constants.ING_SWITCH_POS);
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
	
	public static boolean hasBall() {
		return ballSwitch.get();
	}
	
}
