package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;

public class Shooter {

	private static Shooter instance = null;
	private static Encoder shooterEncoder;
	private static CANTalon shootingMotor;
	
	public static Shooter getInstance(){
		if(instance == null){
			instance = new Shooter();
		}
		return instance;
	}
	
	public Shooter() {
		shootingMotor = new CANTalon(Constants.SHOOTING_TALON_POS);
		shooterEncoder = new Encoder(Constants.SHOOTER_ENCODER_POS_1, Constants.SHOOTER_ENCODER_POS_2);
	}
	
	public static void setPower(double value) {
		shootingMotor.set(value);
	}
	
	public static void stop() {
		setPower(0);
	}
	
	public static void setSpeed(double speed) {
		
	}
	
	public static void disablePID() {
		
	}
}
