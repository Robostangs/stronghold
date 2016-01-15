package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDOutput;

public class DriveMotors implements PIDOutput{
	private static DriveMotors instance;
	private static CANTalon leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack;
	
	public static DriveMotors getInstance(){
		if(instance == null){
			instance = new DriveMotors();
		}
		
		return instance;
	}
	
	private DriveMotors(){
		leftFront = new CANTalon(Constants.DT_TALON_POS_LEFT_FRONT);
		leftMiddle = new CANTalon(Constants.DT_TALON_POS_LEFT_MID);
		leftBack = new CANTalon(Constants.DT_TALON_POS_LEFT_BACK);
		rightFront = new CANTalon(Constants.DT_TALON_POS_RIGHT_FRONT);
		rightMiddle = new CANTalon(Constants.DT_TALON_POS_RIGHT_MID);
		rightBack = new CANTalon(Constants.DT_TALON_POS_RIGHT_BACK);
	}
	
	public static void drive(double leftSpeed, double rightSpeed){
		leftFront.set(leftSpeed);
		leftMiddle.set(leftSpeed);
		leftBack.set(leftSpeed);
		rightFront.set(rightSpeed);
		rightMiddle.set(rightSpeed);
		rightBack.set(rightSpeed);
	}
	
	public static void stop(){
		drive(0,0);
	} 

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
	
}
