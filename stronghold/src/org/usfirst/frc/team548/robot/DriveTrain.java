package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DriveTrain implements PIDSource {
	private static DriveTrain instance;
	private static Encoder encoderLeft, encoderRight;
	
	public static DriveTrain getInstance(){
		if(instance == null){
			instance = new DriveTrain();
		}
		
		return instance;
	}
	
	private DriveTrain(){
		encoderLeft = new Encoder(1, 1);
		encoderRight = new Encoder(1, 1);
	}
	
	public static double getEncoderAverage(){
		return ((encoderLeft.getRaw() + encoderRight.getRaw()) / 2);
	}
	
	public static void encoderReset(){
		encoderLeft.reset();
		encoderRight.reset();
	}
	
	public static void driveStraight(double power){
		if(encoderLeft.getRaw() > encoderRight.getRaw()){
			DriveMotors.drive(power * Constants.DT_DRIVE_STRAIGHT_LOWER_POWER, power * Constants.DT_DRIVE_STRAIGHT_HIGHER_POWER);
		}
		else if(encoderLeft.getRaw() < encoderRight.getRaw()){
			DriveMotors.drive(power * Constants.DT_DRIVE_STRAIGHT_HIGHER_POWER, power * Constants.DT_DRIVE_STRAIGHT_LOWER_POWER);
		}
	}
	
	public static void humanDrive(double left, double right){
		if(left < 0.2 && right < 0.2){
			DriveMotors.stop();
		}
		else{
			DriveMotors.drive(left, right);
		}
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return 0;
	}
}
