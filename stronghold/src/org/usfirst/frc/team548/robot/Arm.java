package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;

public class Arm {

	private static Arm instance = null;
	private static CANTalon armMotor;
	private static Encoder armEncoder;
	
	public static Arm getInstance() {
		if(instance == null){
			instance = new Arm();
		}
		return instance;
	}
	
	private Arm() {
		armEncoder = new Encoder(Constants.ARM_ENCODER_POS_1, Constants.ARM_ENCODER_POS_2);
		armMotor = new CANTalon(Constants.ARM_TALON_POS);
	}
	
	public static void setArm(double value) {
		armMotor.set(value);
	}
	
	public static void stopArm() {
		setArm(0);
	}
	
	
}
