package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class Arm {

	private static Arm instance = null;
	private static CANTalon leftArmMotor, rightArmMotor;
	
	public static Arm getInstance() {
		if(instance == null){
			instance = new Arm();
		}
		return instance;
	}
	
	private Arm() {
		leftArmMotor = new CANTalon(Constants.LEFT_ARM_TALON_POS);
		rightArmMotor = new CANTalon(Constants.RIGHT_ARM_TALON_POS);
		leftArmMotor.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
	}
	
	public static void setPower(double value) {
		leftArmMotor.changeControlMode(TalonControlMode.PercentVbus);
		rightArmMotor.changeControlMode(TalonControlMode.PercentVbus);
		leftArmMotor.set(value);
		rightArmMotor.set(-value);
		System.out.println(value);
	}
	
	public static void stopArm() {
		setPower(0);
	}
	
	public static double getEncoder() {
		return leftArmMotor.getAnalogInRaw();
	}
	
	public static void setArmPos(int setpoint) {
		rightArmMotor.reverseOutput(true);
		leftArmMotor.changeControlMode(TalonControlMode.Position);
		rightArmMotor.changeControlMode(TalonControlMode.Follower);
		rightArmMotor.set(leftArmMotor.getDeviceID());
		leftArmMotor.set(setpoint);
		
		//leftArmMotor.setPID(3, 0, 0);
		
		
	}
	
	public static void setSpeed(double value) {
		if(value < 0) {
			if(getEncoder() < Constants.ARM_MAX_POS) {
				setPower(0);
			} else if(getEncoder() < Constants.ARM_MAX_THRESHOLD){
				setPower(value * Constants.ARM_LOW_POWER);
			} else {
				setPower(value * Constants.ARM_POWER_COEFFICIENT);
			}
		} else if (value > 0) {
			if(getEncoder() > Constants.ARM_MIN_POS) {
				setPower(0);
			} else if(getEncoder() > Constants.ARM_MIN_THRESHOLD){
				setPower(value * Constants.ARM_LOW_POWER);
			} else {
				setPower(value * Constants.ARM_POWER_COEFFICIENT);
			}
		} else {
			setPower(0);
		}
	}
	
	
}
