package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class Ingesting {

	private static Ingesting instance = null;
	private static CANTalon rollerMotor, beltsMotor;
	
	public static Ingesting getInstance(){
		if(instance == null){
			instance = new Ingesting();
		}
		return instance;
	}
	
	public Ingesting() {
		rollerMotor = new CANTalon(Constants.ROLLER_MOTOR_POS);
		beltsMotor = new CANTalon(Constants.BELTS_MOTOR_POS);
	}
	
	public void setRoller(double value) {
		rollerMotor.set(value);
	}
	
	public void stopRoller() {
		setRoller(0);
	}
	
	public void setBelts(double value) {
		beltsMotor.set(value);
	}
	
	public void stopBelts() {
		setBelts(0);
	}
	
	public void rollerIn() {
		setRoller(Constants.ROLLER_IN_NORMAL_POWER);
	}
	
	public void rollerOut() {
		setRoller(Constants.ROLLER_OUT_NORMAL_POWER);
	}
	
	public void beltsIn() {
		setBelts(Constants.BELTS_IN_NORMAL_POWER);
	}
	
	public void beltsOut() {
		setBelts(Constants.BELTS_OUT_NORMAL_POWER);
	}
	
}
