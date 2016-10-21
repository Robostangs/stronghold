package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class Ingesting {

	private static Ingesting instance = null;
	private static CANTalon injectingMotor;
	private static boolean hasReachedSpeed = false;
	private static Timer injectingTimer;
	
	public static Ingesting getInstance(){
		if(instance == null){
			instance = new Ingesting();
		}
		return instance;
	}
	
	private Ingesting() {
		injectingMotor = new CANTalon(Constants.INJECTING_TALON_POS);
		//ballSwitch = new DigitalInput(Constants.INGESTING_SWITCH_POS);
		injectingTimer = new Timer();
	}
	
	public static void setInjecting(double value) {
		injectingMotor.set(value);
	}
	
	public static void stopIngesting() {
		setInjecting(0);
	}
	
	public static void ingest() {
		setInjecting(Constants.INJECTING_IN_NORMAL_POWER);
		
	}

	public static void exgest() {
		setInjecting(Constants.EXGESTING_NORMAL_POWER);
		
	}
	
	
	public static void holdBall() {
		setInjecting(Constants.INJECTING_HOLDING_BALL_POWER);
	}
	
	public static void inject() {
		setInjecting(Constants.INJECTING_POWER);
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
