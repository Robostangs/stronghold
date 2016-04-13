package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.PIDOutput;

public class Arm implements PIDOutput {

	private static Arm instance = null;
	private static CANTalon leftArmMotor, rightArmMotor;
	private static PIDController pid;
	private static AnalogPotentiometer encoder;
	private static double shootingAdjustment = 0;
	private static boolean adjustInit = false;
//	private static boolean armIsHigh = false;
	
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
		encoder = new AnalogPotentiometer(1);
		pid = new PIDController(0,0,0, encoder, this);
		LiveWindow.addActuator("Arm", "RotateController", pid);
		LiveWindow.addSensor("Arm", "Pot", encoder);
	}
	
	public static void setPower(double value) {
		leftArmMotor.set(-value);
		rightArmMotor.set(value);
	}
	
	public static void stopArm() {
		setPower(0);
	}
	
	public static double getEncoder() {
		return encoder.get();
	}
	
	public static void setArmPID(double setpoint, double pk, double ik, double dk) {
		pid.enable();
		pid.setPID(pk, ik, dk);
		pid.setSetpoint(setpoint);
	}
	
	public static void adjustArmPID(double setpoint, double pk, double ik, double dk) {
		pid.enable();
		pid.setPID(pk, ik, dk);
		pid.setSetpoint(setpoint + getAdjustment());
		System.out.println(setpoint);
	}
	
	public static void setArmPos(Constants.ARM_POS pos) {
		if(pos == Constants.ARM_POS.LOW) {
			setArmPID(Constants.ARM_LOW_POS, Constants.ARM_LOW_P, Constants.ARM_LOW_I, Constants.ARM_LOW_D);
		} else if (pos == Constants.ARM_POS.DEF) {
			setArmPID(Constants.ARM_DEF_POS, Constants.ARM_DEF_P, Constants.ARM_DEF_I, Constants.ARM_DEF_D);
		}  else if (pos == Constants.ARM_POS.ING) {
			setArmPID(Constants.ARM_ING_POS, Constants.ARM_ING_P, Constants.ARM_ING_I, Constants.ARM_ING_D);
		} else if (pos == Constants.ARM_POS.SHOOT) {
			if(shootingAdjustment == 0) {
				setArmPID(Constants.ARM_SHOOT_POS, Constants.ARM_SHOOT_P, Constants.ARM_SHOOT_I, Constants.ARM_SHOOT_D);
			} else {
				adjustArmPID(Constants.ARM_SHOOT_POS, Constants.ARM_SHOOT_ADJUST_P, Constants.ARM_SHOOT_ADJUST_I, Constants.ARM_SHOOT_ADJUST_D);
			}
		} else if (pos == Constants.ARM_POS.AUTO_SHOOT) {
			setArmPID(Constants.ARM_AUTO_SHOOT_POS, Constants.ARM_AUTO_SHOOT_P, Constants.ARM_AUTO_SHOOT_I, Constants.ARM_AUTO_SHOOT_D);
		}
	}
	
	public static void changeAdjustment(double change) {
		if(!adjustInit) {
//			if(shootingAdjustment + change < 0 && change < 0) {
//				adjustInit = true;
//			} else {
				shootingAdjustment += change;
				adjustInit = true;
			//}
		}
	}
	
	public static void setAdjustment(double adjust) {
		shootingAdjustment = adjust;
	}
	
	public static void setAdjustmentToOuterWorks() {
		shootingAdjustment = .055;
	}
	
	public static double getAdjustment() {
		return shootingAdjustment;
	}
	
	public static void resetAdjustmentInit() {
		adjustInit = false;
	}
	
	public static void resetAdjustment() {
		shootingAdjustment = 0;
		adjustInit = false;
	}
	
//	public static double snap = 0;
//	
//	public static boolean initSnap = false;
//	
//	public static double takeSnap() {
//		if(!initSnap) {
//			snap = getEncoder();
//			initSnap = true;
//		}
//		return snap;
//	}
//	
//	public static void resetSnap() {
//		initSnap = false;
//	}
//	
//	public static boolean isArmHigh() {
//		if(takeSnap() < 0.650) {
//			 return true;
//		} else {
//			return false;
//		}
//	}
	
	public static void setSpeed(double value) {
		disablePID();
		if(value < 0) {
				setPower(value);
		} else if (value > 0) {
				setPower(value);
		} else {
			setPower(0);
		}
	}
	
	public static void setArmAdjustmentFromDistance(double distance) {
//		shootingAdjustment = (0.0003 * distance) + 0.054; // y = 0.0003x + 0.046, old
		shootingAdjustment = (-0.0004*distance) + 0.104; //data taken 4/12/2016
	}
	
	public static void disablePID() {
		if(pid.isEnabled()) {
			pid.reset();
		}
	}
	
	public void pidWrite(double output) {
		setPower(-output);
	}
}
