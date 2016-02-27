package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Shooter implements PIDOutput{

	private static Shooter instance = null;
	private static CANTalon shootingMotor1, shootingMotor2;
	private static PIDController pid;
//	private static Encoder shootingEncoder;
	
	public static Shooter getInstance(){
		if(instance == null){
			instance = new Shooter();
		}
		return instance;
	}
	
	public Shooter() {
		shootingMotor1 = new CANTalon(Constants.SHOOTING_TALON_POS_1);
		shootingMotor2 = new CANTalon(Constants.SHOOTING_TALON_POS_2);
//		shootingEncoder = new Encoder(Constants.SHOOTER_ENC_POS_1, Constants.SHOOTER_ENC_POS_2);
//		shootingEncoder.setPIDSourceType(PIDSourceType.kRate);
//		shootingMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		//pid = new PIDController(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D, shootingEncoder, this);	
		//LiveWindow.addActuator("Shooter", "RotateController", pid);
//		LiveWindow.addSensor("Shooter", "Encoder", shootingEncoder);
	}
	
	public static void setPower(double value) {
		shootingMotor1.set(value);
		shootingMotor2.set(-value);
	}
	
//	public static double getShooterEncoderVelocity() {
//		return shootingMotor1.getEncVelocity();
//	}
//	
//	public static void resetShooterEncoder() {
//		shootingEncoder.reset();
//	}
		
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
		shootingMotor2.set(14);
		shootingMotor2.reverseOutput(true);
		shootingMotor1.setPID(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D);
		shootingMotor1.set(speed);
		//pid.enable();
		//pid.setSetpoint(speed);
	}

	public static void setShooterSpeedNoPID(double speed) {
		disablePID();
		setPower(speed);
	}
	
	public static void disablePID() {
		//pid.disable();
	}

	public void pidWrite(double output) {
		setPower(output);
	}
}
