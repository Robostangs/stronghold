
package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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
		shootingMotor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//LiveWindow.addActuator("Shooter", "RotateController", pid);
		//LiveWindow.addSensor("Shooter", "Encoder", shootingEncoder);
	}
	
	public static void setPower(double value) {
		shootingMotor1.set(-value);
		shootingMotor2.set(-value);
	}
	
	public static double getShooterEncoderVelocity() {
//		return shootingEncoder.getRate();
		return shootingMotor1.getEncVelocity();
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
	


	public static void setShooterSpeedNoPID(double speed) {
		setPower(speed);
	}
	

	public void pidWrite(double output) {
		setPower(output);
	}
	
	
}
