package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DriveTrain implements PIDSource {
	private static DriveTrain instance = null;
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
		pid.setInputRange(-1000000, 1000000); //IDK YET
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
	public void setPIDSourceType(PIDSourceType pidSource) {//Don't worry about this yet
		// TODO Auto-generated method stub
		pidtype = pidSource;
	}
	
	
	@Override
	public PIDSourceType getPIDSourceType() { //Dont worry about this yet
		// TODO Auto-generated method stub
		return pidtype;
	}

	@Override
	public double pidGet() { //Gets the encoder vales for PID
		// TODO Auto-generated method stub
		return getEncoderAverage();
	}
	/**
	 * Drives a distance and returns true if within a percentage of the target
	 * RESET ENCODERS BEFOR USING
	 * @param setPoint
	 * @param pk
	 * @param ik
	 * @param dk
	 * @return is the setpoint in tolerance
	 */
	public static boolean driveDistance(double setPoint, double pk, double ik, double dk) {
		pid.setPID(pk, ik, dk);
		pid.setSetpoint(setPoint);
		pid.enable();
		pid.setPercentTolerance(9999999); //IDK YET
		return pid.onTarget();
	}
	/**
	 * Disables PID
	 */
	public static void disablePID() {
		pid.disable();
	}
	
}
