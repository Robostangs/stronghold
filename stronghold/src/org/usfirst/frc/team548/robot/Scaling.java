package org.usfirst.frc.team548.robot;

import javax.swing.plaf.SliderUI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

public class Scaling {
	
	private static CANTalon scalingMotor;
	private static Scaling instance = null;
	private static DigitalInput scalingSwitch;
	private static DigitalInput descalingSwitch;
	private static Servo servo;
	
	public Scaling() {
		scalingMotor = new CANTalon(Constants.SCALING_TALON_POS);
		scalingSwitch = new DigitalInput(Constants.SCALING_SWITCH_POS);
		descalingSwitch = new DigitalInput(Constants.DESCALING_SWITCH_POS);
		servo = new Servo(Constants.SCALING_SERVO_POS);
	}
	
	public static Scaling getInstance() {
		if(instance == null){
			instance = new Scaling();
		}
		return instance;
	}
	
	public static boolean getScalingSwitch() {
		return scalingSwitch.get();
	}
	
	public static boolean getDescalingSwitch() {
		return descalingSwitch.get();
	}
	
	public static void setSpeed(double value) {
//		if(value < 0) {
//			if(!getDescalingSwitch()) {
				scalingMotor.set(value);
//			} else {
//				stopScaling();
//			}
//		} else {
//			if(!getScalingSwitch()) {
//				scalingMotor.set(value);
//			} else {
//				stopScaling();
//			}
//		}
		
	}
	
	public static void stopScaling() {
		setSpeed(0);
	}
	
	public static void scale(double speed) {
		if(scalingSwitch.get()) {
		    setSpeed(speed);
		} else {
			stopScaling();
		}
	}
	
	public static void descale(double speed) {
		if(descalingSwitch.get()) {
			setSpeed(-speed);
		} else {
			stopScaling();
		}
		
	}
	
	public static void engageServo() {
		servo.setAngle(0);
	}
	
	public static void disengageServo() {
		servo.setAngle(180);
	}
	
	public static double getScallingSetForLights() {
		return scalingMotor.get();
	}
	
	
}
