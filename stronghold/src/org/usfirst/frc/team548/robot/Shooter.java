package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class Shooter {

	private static Shooter instance = null;
	private static CANTalon shootingMotor1, shootingMotor2;
	
	public static Shooter getInstance(){
		if(instance == null){
			instance = new Shooter();
		}
		return instance;
	}
	
	public Shooter() {
		shootingMotor1 = new CANTalon(Constants.SHOOTING_TALON_POS_1);
		shootingMotor2 = new CANTalon(Constants.SHOOTING_TALON_POS_2);
		
		shootingMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		
	}
	
	public static void setPower(double value) {
		shootingMotor1.set(value);
		shootingMotor2.set(value);
	}
	
	public static double getShooterEncoderPosition() {
		return shootingMotor1.getEncPosition();
	}
	
	public static double getShooterEncoderVelocity() {
		return shootingMotor1.getEncVelocity();
	}
	
	public static void resetShooterEncoder() {
		shootingMotor1.setEncPosition(0);
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
		shootingMotor1.changeControlMode(TalonControlMode.Speed);
		shootingMotor2.changeControlMode(TalonControlMode.Follower);
		shootingMotor2.set(Constants.SHOOTING_TALON_POS_1);
		shootingMotor2.reverseOutput(true);
		shootingMotor1.set(speed);
		shootingMotor1.setPID(Constants.SHOOTING_PID_P, Constants.SHOOTING_PID_I, Constants.SHOOTING_PID_D);
		
	}

	public static void setShooterSpeedNoPID(double speed) {
		disablePID();
		setPower(speed);
	}
	
	public static void disablePID() {
		shootingMotor1.changeControlMode(TalonControlMode.PercentVbus);
	}
}
