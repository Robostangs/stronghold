package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Ingesting {

	private static Ingesting instance = null;
	private static CANTalon ingestingMotor;
	//private static DigitalInput ballSwitch;
	
	public static Ingesting getInstance(){
		if(instance == null){
			instance = new Ingesting();
		}
		return instance;
	}
	
	private Ingesting() {
		ingestingMotor = new CANTalon(Constants.INGESTING_TALON_POS);
		//ballSwitch = new DigitalInput(Constants.INGESTING_SWITCH_POS);
	}
	
	//Set rollers and injector wheels to speed
	public static void setIngesting(double value) {
		ingestingMotor.set(value);
	}
	
	public static void stopIngesting() {
		setIngesting(0);
	}
	
	//ingest ball with ingesting and shooter wheel
	public static void ingest() {
		setIngesting(Constants.INGESTING_IN_NORMAL_POWER);
		Shooter.shooterIngest();
	}
	
	//exgest ball with ingesting and shooter wheel
	public static void exgest() {
		setIngesting(Constants.INGESTING_OUT_NORMAL_POWER);
		Shooter.shooterExgest();
	}
	
	//use limit switch to determine if we're holding a ball
//	public static boolean hasBall() {
//		return ballSwitch.get();
//	}
	
	//run ingesting inwards constantly at slow speed to hold ball
	public static void holdBall() {
		setIngesting(Constants.INGESTING_HOLDING_BALL_POWER);
	}
	
	public static void inject() {
		setIngesting(Constants.INJECTING_POWER);
	}
	
}
