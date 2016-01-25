package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class DriveTrain implements PIDSource, PIDOutput {
	private static DriveTrain instance = null;
	private static Encoder encoderLeft, encoderRight;
	private static Gyro hyro;
	private static CANTalon leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack;
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
		leftFront = new CANTalon(Constants.DT_TALON_POS_LEFT_FRONT);
		leftMiddle = new CANTalon(Constants.DT_TALON_POS_LEFT_MID);
		leftBack = new CANTalon(Constants.DT_TALON_POS_LEFT_BACK);
		rightFront = new CANTalon(Constants.DT_TALON_POS_RIGHT_FRONT);
		rightMiddle = new CANTalon(Constants.DT_TALON_POS_RIGHT_MID);
		rightBack = new CANTalon(Constants.DT_TALON_POS_RIGHT_BACK);
		//encoderLeft = new Encoder(Constants.LEFT_ENCODER_POS_1, Constants.LEFT_ENCODER_POS_2);
		//encoderRight = new Encoder(Constants.RIGHT_ENCODER_POS_1, Constants.RIGHT_ENCODER_POS_2);
		//hyro = new Gyro(Constants.GYRO_POS);
		//pid = new PIDController(0, 0, 0, this, this);
		//pid.setInputRange(-1000000, 1000000); //IDK YET
		//pid.disable();
	}
	
	public static void drive(double leftSpeed, double rightSpeed){
		leftFront.set(-leftSpeed);
		leftMiddle.set(-leftSpeed);
		leftBack.set(-leftSpeed);
		rightFront.set(rightSpeed);
		rightMiddle.set(rightSpeed);
		rightBack.set(rightSpeed);
	}
	
	/**
	 * Stops the drive motors
	 */
	public static void stop(){
		drive(0,0);
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
	
	public static void getHyro() {
		hyro.getAngle();
	}
	
	public static void resetHyro() {
		hyro.reset();
	}
	
	
	
	/**
	 * Drive straight method
	 * @param power
	 */
	public static void driveStraight(double power){
		if(encoderLeft.getRaw() - encoderRight.getRaw() > Constants.DT_ENCODER_ERROR_THRESHOLD){
			drive(power * Constants.DT_DRIVE_STRAIGHT_LOWER_POWER, power * Constants.DT_DRIVE_STRAIGHT_HIGHER_POWER);
		}
		else if(encoderLeft.getRaw() - encoderRight.getRaw() < -Constants.DT_ENCODER_ERROR_THRESHOLD){
			drive(power * Constants.DT_DRIVE_STRAIGHT_HIGHER_POWER, power * Constants.DT_DRIVE_STRAIGHT_LOWER_POWER);
		} else {
			drive(power, power);
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
		drive(left, right);
	}
	/*
	 * 
	 * 
	 * 
	 * PID STUFF vvvvvvvv
	 * 
	 * 
	 * 
	 */
	private static PIDSourceType pidtype = PIDSourceType.kDisplacement;
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {//Don't worry about this yet
		pidtype = pidSource;
	}
	
	
	@Override
	public PIDSourceType getPIDSourceType() { //Dont worry about this yet
		return pidtype;
	}

	@Override
	public double pidGet() { //Gets the encoder vales for PID
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
	
	public static boolean turnAngle(double setPoint, double pk, double ik, double dk) {
		//make robot turn using pid based off of gyro values
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

	@Override
	public void pidWrite(double output) {
		driveStraight(output);
	}
	
}
