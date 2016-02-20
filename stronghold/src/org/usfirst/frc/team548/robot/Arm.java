package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.PIDOutput;

public class Arm implements PIDOutput {

	private static Arm instance = null;
	private static CANTalon leftArmMotor, rightArmMotor;
	private static PIDController pid;
	private static AnalogPotentiometer encoder;
	private static boolean armIsHigh = false;
	public static Arm getInstance() {
		if(instance == null){
			instance = new Arm();
		}
		return instance;
	}
	
	private Arm() {
		leftArmMotor = new CANTalon(Constants.LEFT_ARM_TALON_POS);
		rightArmMotor = new CANTalon(Constants.RIGHT_ARM_TALON_POS);
		leftArmMotor.changeControlMode(TalonControlMode.PercentVbus);
		rightArmMotor.changeControlMode(TalonControlMode.PercentVbus);
		encoder = new AnalogPotentiometer(0);
		pid = new PIDController(0,0,0, encoder, this);
		LiveWindow.addActuator("Arm", "RotateController", pid);
		LiveWindow.addSensor("Arm", "Pot", encoder);
	}
	
	public static void setPower(double value) {
		
		leftArmMotor.set(-value);
		rightArmMotor.set(value);
	}
	
	public static void stopArm() { }
	
	public static double getEncoder() {
		return encoder.get();
	}
	
	public static void setArmPos(double setpoint, double pk, double ik, double dk) {
		pid.enable();
		pid.setPID(pk, ik, dk);
		pid.setSetpoint(setpoint);		
	}
	
	public static void setArmUpToLow() {
		setArmPos(Constants.ARM_LOW_POS, Constants.ARM_UP_TO_LOW_P, Constants.ARM_UP_TO_LOW_I, Constants.ARM_UP_TO_LOW_D);
	}
	
	public static void setArmDownToLow() {
		setArmPos(Constants.ARM_LOW_POS, Constants.ARM_DOWN_TO_LOW_P, Constants.ARM_DOWN_TO_LOW_I, Constants.ARM_DOWN_TO_LOW_D);
	}
	
	public static void setArmUpToIng() {
		setArmPos(Constants.ARM_ING_POS, Constants.ARM_UP_TO_ING_P, Constants.ARM_UP_TO_ING_I, Constants.ARM_UP_TO_ING_D);
	}
	
	public static void setArmDownToIng() {
		setArmPos(Constants.ARM_ING_POS, Constants.ARM_DOWN_TO_ING_P, Constants.ARM_DOWN_TO_ING_I, Constants.ARM_DOWN_TO_ING_D);
	}
	
	public static void setArmUpToDef() {
		setArmPos(Constants.ARM_DEF_POS, Constants.ARM_UP_TO_DEF_P, Constants.ARM_UP_TO_DEF_I, Constants.ARM_UP_TO_DEF_D);
	}
	
	public static void setArmDownToDef() {
		setArmPos(Constants.ARM_DEF_POS, Constants.ARM_DOWN_TO_DEF_P, Constants.ARM_DOWN_TO_DEF_I, Constants.ARM_DOWN_TO_DEF_D);
	}
	
	public static void setArmShoot() {
		setArmPos(Constants.ARM_SHOOT_POS, Constants.ARM_SHOOT_P, Constants.ARM_SHOOT_I, Constants.ARM_SHOOT_D);
	}
	
	public static double snap = 0;
	
	public static boolean initSnap = false;
	
	public static double takeSnap() {
		if(!initSnap) {
			snap = getEncoder();
			initSnap = true;
		}
		return snap;
	}
	
	public static void resetSnap() {
		initSnap = false;
	}
	
	public static boolean isArmHigh() {
		if(takeSnap() < 0.650) {
			 return true;
		} else {
			return false;
		}
	}
	
	public static void setSpeed(double value) {
		pid.disable();
		if(value < 0) {
//			if(getEncoder() < Constants.ARM_MAX_POS) {
//				setPower(0);
//			} else if(getEncoder() < Constants.ARM_MAX_THRESHOLD){
//				setPower(value * Constants.ARM_LOW_POWER);
//			} else {
				setPower(value * Constants.ARM_POWER_COEFFICIENT);
//			}
		} else if (value > 0) {
//			if(getEncoder() > Constants.ARM_MIN_POS) {
//				setPower(0);
//			} else if(getEncoder() > Constants.ARM_MIN_THRESHOLD){
//				setPower(value * Constants.ARM_LOW_POWER);
//			} else {
				setPower(value * Constants.ARM_POWER_COEFFICIENT);
//			}
		} else {
			setPower(0);
		}
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		setPower(output);
	}
	
	
	
	
}
