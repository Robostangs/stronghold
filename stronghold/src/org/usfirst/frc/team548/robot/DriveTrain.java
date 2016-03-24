package org.usfirst.frc.team548.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain implements PIDSource, PIDOutput {
	private static DriveTrain instance = null;
	private static AHRS hyro;
	private static CANTalon leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack;
	private static PIDController pid;

	public static DriveTrain getInstance(){
		if(instance == null){
			instance = new DriveTrain();
		}
		return instance;
	}

	private DriveTrain(){
		leftFront = new CANTalon(Constants.DT_TALON_POS_LEFT_FRONT);
		leftMiddle = new CANTalon(Constants.DT_TALON_POS_LEFT_MID);
		leftBack = new CANTalon(Constants.DT_TALON_POS_LEFT_BACK);
		rightFront = new CANTalon(Constants.DT_TALON_POS_RIGHT_FRONT);
		rightMiddle = new CANTalon(Constants.DT_TALON_POS_RIGHT_MID);
		rightBack = new CANTalon(Constants.DT_TALON_POS_RIGHT_BACK);
		
		//rightFront.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		//leftBack.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		hyro = new AHRS(SerialPort.Port.kMXP);
		hyro.reset();
		
		pid = new PIDController(0, 0, 0, hyro, this);
		pid.disable();
		pid.setInputRange(-180.0f,  180.0f);
		pid.setOutputRange(-0.5f, 0.5f);
		pid.setAbsoluteTolerance(2f);
        pid.setContinuous(true);
          
        LiveWindow.addSensor("Drive", "Gyro", hyro);
	}
	
	public static void drive(double leftSpeed, double rightSpeed){
		leftFront.set(-leftSpeed);
		leftMiddle.set(-leftSpeed);
		leftBack.set(-leftSpeed);
		rightFront.set(rightSpeed);
		rightMiddle.set(rightSpeed);
		rightBack.set(rightSpeed);
	}

	public static void stop(){
		drive(0, 0);
	} 
	
	public static double getLeftEncoder() {
		return -leftBack.getEncPosition();
	}
	
	public static double getLeftRate() {
		return leftBack.getEncVelocity();
	}
	
	public static double getRightRate() {
		return rightFront.getEncVelocity();
	}
	
	public static double getRightEncoder() {
		return rightFront.getEncPosition();
	}
	
	public static double getEncoderAverage() {
		return (getRightEncoder() + getLeftEncoder()) / 2;
	}

	public static void encoderReset(){
		leftBack.setPosition(0);
		rightFront.setPosition(0);
	}	
	
	public static double getHyroAngle() {
		return hyro.pidGet();
	}
	
	public static void resetHyro() {
		hyro.reset();
	}

	public static void driveStraightHyro(double power) {
		if(getHyroAngle() > Constants.DT_HYRO_ERROR_THRESHOLD){
			drive(power * Constants.DT_DRIVE_STRAIGHT_LOWER_POWER_LEFT, power * Constants.DT_DRIVE_STRAIGHT_HIGHER_POWER_RIGHT);
		}
		else if(getHyroAngle() > -Constants.DT_HYRO_ERROR_THRESHOLD){
			drive(power * Constants.DT_DRIVE_STRAIGHT_HIGHER_POWER_LEFT, power * Constants.DT_DRIVE_STRAIGHT_LOWER_POWER_RIGHT);
		} else {
			drive(power, power);
		}
	}
	
	public static void driveDistanceNoPID(int distance, double power, int threshold) {
		if(getEncoderAverage() < (distance - threshold)) {
			driveStraightHyro(power);
		} else if(getEncoderAverage() < distance) {
			driveStraightHyro(power * Constants.DRIVE_DISTANCE_LOW_POWER);
		} else {
			stop();
		}
	}

	public static void humanDrive(double left, double right){
		disablePID();
		if(Math.abs(left) < 0.2) {
			left = 0;
		}
		if (Math.abs(right) < 0.2){
			right = 0;
		}
		drive(left, right);
	}
	
        
	private static PIDSourceType pidtype = PIDSourceType.kDisplacement;
	
	private static boolean gyroPID = true, pidInit = false;

	public void setPIDSourceType(PIDSourceType pidSource) {
		pidtype = pidSource;
	}

	public PIDSourceType getPIDSourceType() {
		return pidtype;
	}

	public double pidGet() {
		return hyro.getYaw();
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
//	public static boolean driveDistance(double setPoint, double pk, double ik, double dk) {
//		pid.setPID(pk, ik, dk);
//		pid.setSetpoint(setPoint);
//		pid.enable();
//		pid.setPercentTolerance(9999999); //IDK YET
//		return pid.onTarget();
//	}
	
	public static void resetPIDInit() {
		pidInit = false;
	}
	
	public static void turnAngle(double setPoint) {
		setPIDtoGyro();
		pid.enable();
		pid.setSetpoint(setPoint);
		
	}
	
	public static void turnSmallAngle(double setPoint) {
		setPIDtoSmallGyro();
		pid.enable();
		pid.setSetpoint(setPoint);
		
	}

	public static void disablePID() {
		if(pid.isEnabled()) pid.disable();
	}
	
	public static void setPIDtoGyro() {
		if(!pidInit) {
			gyroPID = true;
			pid = new PIDController(Constants.DT_PID_GYRO_KP, Constants.DT_PID_GYRO_KI, Constants.DT_PID_GYRO_KD, hyro, DriveTrain.getInstance());
			pid.setInputRange(-180.0f,  180.0f);
			pid.setOutputRange(-0.75f, 0.75f);
			pid.setAbsoluteTolerance(2f);
	        pid.setContinuous(true);
		}
        pidInit = true;
	}
	
	public static void setPIDtoSmallGyro() {
		if(!pidInit) {
			gyroPID = true;
			pid = new PIDController(Constants.DT_PID_SMALL_GYRO_KP, Constants.DT_PID_SMALL_GYRO_KI, Constants.DT_PID_SMALL_GYRO_KD, hyro, DriveTrain.getInstance());
			pid.setInputRange(-180.0f,  180.0f);
			pid.setOutputRange(-0.75f, 0.75f);
			pid.setAbsoluteTolerance(2f);
			pid.setContinuous(true);
		}
        pidInit = true;
	}

	public void pidWrite(double output) {
		drive(output, -output);
//		System.out.println("PID running");
	}
	
	public static double getLeftSetForLights() {
		return -leftMiddle.get();
	}
	
	public static double getRightSetForLights() {
		return rightMiddle.get();
	}
	
}
