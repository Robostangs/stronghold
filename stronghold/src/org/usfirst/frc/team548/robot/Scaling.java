package org.usfirst.frc.team548.robot;

import javax.swing.plaf.SliderUI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

public class Scaling {
	
	private static CANTalon scalingMotor;
	private static Scaling instance = null;
	private static DigitalInput scalingSwitch, scalingSwitch2;
	private static DigitalInput descalingSwitch;
	private static Servo servo;
	
	public Scaling() {
		scalingMotor = new CANTalon(Constants.SCALING_TALON_POS);
		scalingSwitch = new DigitalInput(2);
		descalingSwitch = new DigitalInput(3);
		scalingSwitch2 = new DigitalInput(4);
		servo = new Servo(Constants.SCALING_SERVO_POS);
	}
	
	public static Scaling getInstance() {
		if(instance == null){
			instance = new Scaling();
		}
		return instance;
	}
	
	public static boolean getScalingSwitch() {
		return !scalingSwitch.get();
	}
	
	public static boolean getScalingSwitch2() {
		return scalingSwitch2.get();
	}
	
	public static boolean getDescalingSwitch() {
		return !descalingSwitch.get();
	}
	
	public static void setSpeed(double value) {
		if(value > 0) {
			if(!getDescalingSwitch()) {
				scalingMotor.set(value);
			} else {
				stopScaling();
			}
		} else {
			if(!getScalingSwitch2()) {
				scalingMotor.set(value);
			} else {
				stopScaling();
			}
		}
		
	}
	
	public static void stopScaling() {
		scalingMotor.set(0);
	}
	
	public static void scale(double speed) {
		
		    setSpeed(speed);
		
	}
	
	public static void descale(double speed) {
		
			setSpeed(-speed);
		
		
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
