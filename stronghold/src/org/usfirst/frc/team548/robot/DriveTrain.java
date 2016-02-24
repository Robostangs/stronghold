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
		
//		rightBack.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//		leftFront.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
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
		return leftFront.getEncPosition();
	}
	
	public static double getLeftRate() {
		return leftFront.getEncVelocity();
	}
	
	public static double getRightRate() {
		return rightBack.getEncVelocity();
	}
	
	public static double getRightEncoder() {
		return -rightBack.getEncPosition();
	}
	
	public static double getEncoderAverage() {
		return (getRightEncoder() + getLeftEncoder()) / 2;
	}

	public static void encoderReset(){
		leftFront.setPosition(0);
		rightBack.setPosition(0);
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
		if(Math.abs(left) < 0.2) {
			left = 0;
		}
		if (Math.abs(right) < 0.2){
			right = 0;
		}
		drive(left, right);
	}
	
	public static void driveForza(double wheel, double power, boolean turnQuick) {
        if(Math.abs(power) > .1) {
            double leftPower = 0;
            double rightPower = 0;
            if (turnQuick)
            {
                leftPower = (wheel*(power));
                rightPower = -wheel * ( power);

            }
            else if (wheel > 0) //right
            {
                leftPower = power ;
                rightPower = power * (1-wheel);
            }
            else if (wheel < 0)// left
            {
                leftPower = power * (1 - -wheel);
                rightPower = power;
            }
            else
            {
                leftPower = power;
                rightPower = power;
            }
            drive(leftPower, rightPower);
        } else {
             drive(0, 0);
        }
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

	public static void disablePID() {
		pid.disable();
	}
	
	public static void setPIDtoGyro() {
		if(!pidInit) {
			gyroPID = true;
			pid = new PIDController(Constants.DT_PID_GYRO_KP, Constants.DT_PID_GYRO_KI, Constants.DT_PID_GYRO_KD, hyro, DriveTrain.getInstance());
			pid.setInputRange(-180.0f,  180.0f);
			pid.setOutputRange(-0.5f, 0.5f);
			pid.setAbsoluteTolerance(2f);
	        pid.setContinuous(true);
		}
        pidInit = true;
	}
	
	public static void setPIDtoDrive() {
		gyroPID = false;
		pid = new PIDController(Constants.DT_PID_DRIVE_KP, Constants.DT_PID_DRIVE_KI, Constants.DT_PID_DRIVE_KD, DriveTrain.getInstance(), DriveTrain.getInstance());
		pidInit = true;
	}

	public void pidWrite(double output) {
		if(gyroPID) {
			drive(output, -output);	
		} else {
			driveStraightHyro(output);
		}
	}
	
}
