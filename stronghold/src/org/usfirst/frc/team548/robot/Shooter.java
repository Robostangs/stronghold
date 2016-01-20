package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;

public class Shooter {

	private static Shooter instance = null;
	private static Encoder shooterEncoder;
	private static CANTalon shootingMotor;
	
	public static Shooter getInstance(){
		if(instance == null){
			instance = new Shooter();
		}
		return instance;
	}
	
	public Shooter() {
		shootingMotor = new CANTalon(Constants.SHOOTING_MOTOR_POS);
		shooterEncoder = new Encoder(Constants.SHOOTER_ENCODER_POS_1, Constants.SHOOTER_ENCODER_POS_2);
	}
	
	public void setShooter(double value) {
		shootingMotor.set(value);
	}
	
	public void stopShooting() {
		setShooter(0);
	}
}
