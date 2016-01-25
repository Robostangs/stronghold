package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class Arm {

	private static Arm instance = null;
	private static CANTalon armMotor;
	
	public static Arm getInstance() {
		if(instance == null){
			instance = new Arm();
		}
		return instance;
	}
	
	private Arm() {
		armMotor = new CANTalon(Constants.ARM_TALON_POS);
		armMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
	}
	
	public static void setPower(double value) {
		armMotor.set(value);
	}
	
	public static void stopArm() {
		setPower(0);
	}
	
	public static double getEncoder() {
		return armMotor.getEncPosition();
	}
}
