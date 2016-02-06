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
		leftArmMotor = new CANTalon(Constants.RIGHT_ARM_TALON_POS);
		rightArmMotor = new CANTalon(Constants.LEFT_ARM_TALON_POS);
		leftArmMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
	}
	
	public static void setPower(double value) {
		leftArmMotor.changeControlMode(TalonControlMode.PercentVbus);
		leftArmMotor.set(value);
		rightArmMotor.set(-value);
	}
	
	public static void stopArm() {
		setPower(0);
	}
	
	public static double getEncoder() {
		return leftArmMotor.getEncPosition();
	}
	
	public static void setArmPos(int setpoint) {
		leftArmMotor.set(setpoint);
		leftArmMotor.changeControlMode(TalonControlMode.Position);
		leftArmMotor.setPID(0, 0, 0);
	}
	
	public static void setSpeed(double value) {
		if(value > 0) {
			if(getEncoder() < Constants.ARM_MAX_POS) {
				setPower(value * Constants.ARM_POWER_COEFFICIENT);
			} else {
				setPower(0);
			}
		} else if (value < 0) {
			if(getEncoder() > Constants.ARM_MIN_POS) {
				setPower(value * Constants.ARM_POWER_COEFFICIENT);
			} else {
				setPower(0);
			}
		} else {
			setPower(0);
		}
	}
	
	
}
