package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class Ingesting {

	private static Ingesting instance = null;
	private static CANTalon ingestingMotor;
	private static boolean hasReachedSpeed = false;
	private static Timer injectingTimer;
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
		injectingTimer = new Timer();
	}
	
	public static void setIngesting(double value) {
		ingestingMotor.set(value);
	}
	
	public static void stopIngesting() {
		setIngesting(0);
	}
	
	public static void ingest() {
		setIngesting(Constants.INGESTING_IN_NORMAL_POWER);
		
	}

	public static void exgest() {
		setIngesting(Constants.INGESTING_OUT_NORMAL_POWER);
		
	}
	
//	public static boolean hasBall() {
//		return ballSwitch.get();
//	}
	
	public static void holdBall() {
		setIngesting(Constants.INGESTING_HOLDING_BALL_POWER);
	}
	
	public static void inject() {
		setIngesting(Constants.INJECTING_POWER);
	}
	
	
	public static void injectAfterSpeed(double speed) {
		if(Math.abs(Shooter.getShooterEncoderVelocity()) > speed) {
			hasReachedSpeed = true;
			startTimer();
		}
		
		if(hasReachedSpeed && getTimer() < 1) {
			inject();
		} else {
			Ingesting.resetTimer();
			Ingesting.resetHasReachedSpeed();
		}
	}
	
	public static void resetHasReachedSpeed() {
		hasReachedSpeed = false;
	}
	
	public static void resetTimer() {
		injectingTimer.stop();
		injectingTimer.reset();
	}
	
	public static void startTimer() {
		injectingTimer.start();
	}
	public static double getTimer() {
		return injectingTimer.get();
	}
}
