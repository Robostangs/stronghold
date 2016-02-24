package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Scaling {
	
	private static CANTalon scalingMotor;
	private static Scaling instance = null;
//	private static DigitalInput scalingSwitch;
	
	public Scaling() {
		scalingMotor = new CANTalon(Constants.SCALING_TALON_POS);
//		scalingSwitch = new DigitalInput(Constants.SCALING_SWITCH_POS);
	}
	
	public static Scaling getInstance() {
		if(instance == null){
			instance = new Scaling();
		}
		return instance;
	}
	
//	public static boolean getSwitch() {
//		return scalingSwitch.get();
//	}
	
	public static void setSpeed(double value) {
		//if(!getSwitch()) {
			scalingMotor.set(value);
		//}
	}
	
	public static void stopScaling() {
		setSpeed(0);
	}
	
	public static void scale(double speed) {
		setSpeed(speed);
	}
	
	public static void descale(double speed) {
		setSpeed(-speed);
	}
	
	
}
