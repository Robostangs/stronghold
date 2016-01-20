package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class ArmMotors {

	private static ArmMotors instance = null;
	private static CANTalon armMotor;
	
	public static ArmMotors getInstance(){
		if(instance == null){
			instance = new ArmMotors();
		}
		return instance;
	}
	
	public ArmMotors() {
		armMotor = new CANTalon(Constants.ARM_MOTOR_POS);
	}
	
	public void setArm(double value) {
		armMotor.set(value);
	}
	
	public void stopArm() {
		setArm(0);
	}
}
