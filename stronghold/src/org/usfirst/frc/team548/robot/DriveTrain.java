package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DriveTrain implements PIDSource {
	private static DriveTrain instance;
	private static Encoder encoderLeft, encoderRight;
	private static PIDController pid;
	
	/**
	 * Initializes
	 * @return instance
	 */
	public static DriveTrain getInstance(){
		if(instance == null){
			instance = new DriveTrain();
		}
		
		return instance;
	}
	
	/**
	 * Initializes the encoders
	 */
	private DriveTrain(){
		DriveMotors.getInstance();
		encoderLeft = new Encoder(Constants.LEFT_ENCODER_POS_1, Constants.LEFT_ENCODER_POS_2);
		encoderRight = new Encoder(Constants.RIGHT_ENCODER_POS_1, Constants.RIGHT_ENCODER_POS_2);
		pid = new PIDController(0, 0, 0, this, DriveMotors.getInstance());
		pid.setInputRange(-1000000, 1000000);
		pid.disable();
	}
	
	/**
	 * Gets the average of the left and right encoders
	 * @return 
	 */
	public static double getEncoderAverage(){
		return ((encoderLeft.getRaw() + encoderRight.getRaw()) / 2);
	}
	
	/**
	 * Resets the encoders
	 */
	public static void encoderReset(){
		encoderLeft.reset();
		encoderRight.reset();
	}
	
	/**
	 * Drive straight method
	 * @param power
	 */
	public static void driveStraight(double power){
		if(encoderLeft.getRaw() > encoderRight.getRaw()){
			DriveMotors.drive(power * Constants.DT_DRIVE_STRAIGHT_LOWER_POWER, power * Constants.DT_DRIVE_STRAIGHT_HIGHER_POWER);
		}
		else if(encoderLeft.getRaw() < encoderRight.getRaw()){
			DriveMotors.drive(power * Constants.DT_DRIVE_STRAIGHT_HIGHER_POWER, power * Constants.DT_DRIVE_STRAIGHT_LOWER_POWER);
		}
	}
	
	/**
	 * Makes deadzones on the controller
	 * @param left
	 * @param right
	 */
	public static void humanDrive(double left, double right){
		if(Math.abs(left) < 0.2) {
			left = 0;
		}
		if (Math.abs(right) < 0.2){
			right = 0;
		}
		DriveMotors.drive(left, right);
	}
	
	private static PIDSourceType pidtype = PIDSourceType.kDisplacement;
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		pidtype = pidSource;
	}
	
	
	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return pidtype;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getEncoderAverage();
	}
	
	public static boolean driveDistance(double setPoint, double pk, double ik, double dk) {
		pid.setPID(pk, ik, dk);
		pid.setSetpoint(setPoint);
		pid.enable();
		pid.setPercentTolerance(9999999);
		return pid.onTarget();
	}
	
	public static void disablePID() {
		pid.disable();
	}
	
}
