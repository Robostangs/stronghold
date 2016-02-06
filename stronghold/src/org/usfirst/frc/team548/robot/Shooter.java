package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class Shooter {

	private static Shooter instance = null;
	private static CANTalon shootingMotor;
	
	public static Shooter getInstance(){
		if(instance == null){
			instance = new Shooter();
		}
		return instance;
	}
	
	public Shooter() {
		shootingMotor = new CANTalon(Constants.SHOOTING_TALON_POS);
	}
	
	public static void setPower(double value) {
		//shootingMotor.changeControlMode(TalonControlMode.PercentVbus);
		shootingMotor.set(value);
	}
	
	public static void shooterIngest() {
		setPower(Constants.SHOOTER_INGEST_SPEED);
	}
	
	public static void shooterExgest() {
		setPower(Constants.SHOOTER_EXGEST_SPEED);
	}
	
	public static void stop() {
		setPower(0);
	}
	
	public static void setSpeed(double speed) {
		shootingMotor.changeControlMode(TalonControlMode.Speed);
		shootingMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shootingMotor.set(speed);
		shootingMotor.setPID(Constants.SHOOTING_PID_P, Constants.SHOOTING_PID_I, Constants.SHOOTING_PID_D);
		
	}
	
	public static void setShooterSpeedNoPID(double speed) {
		disablePID();
		shootingMotor.set(speed);
	}
	
	public static void disablePID() {
		shootingMotor.changeControlMode(TalonControlMode.PercentVbus);
	}
}
