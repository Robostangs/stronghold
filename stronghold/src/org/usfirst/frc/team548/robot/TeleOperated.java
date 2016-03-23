package org.usfirst.frc.team548.robot;

import java.util.ArrayList;

import CameraStuff.RRCPSkinnyServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOperated {
	private static XboxController driver, manip;
	private static TeleOperated instance = null;
	// static boolean newDrive = false;
	static boolean latched = false;
	private static double shootingSpeed = 3000, headingSnapValue = 0;
	private static boolean distanceSnap = false, headingSnap = false;
	private static boolean gyroReset = false;

	public static TeleOperated getInstance() {
		if (instance == null) {
			instance = new TeleOperated();
		}
		return instance;
	}

	public TeleOperated() {
		driver = new XboxController(Constants.XBOX_DRIVER_POS);
		manip = new XboxController(Constants.XBOX_MANIP_POS);
	}

	public static void run() {

		/*
		 * Driver Controls
		 * 
		 * Tank drive on sticks
		 */

		// if(driver.getAButton()) {
		// DriveTrain.resetHyro();
		// } else if(driver.getBButton()) {
		// DriveTrain.driveStraightHyro(.5);
		// } else {
		// if(newDrive) {
		// DriveTrain.driveForza(driver.getLeftStickXAxis(),
		// driver.getBothTriggerAxis(), driver.getXButton());
		// } else {

		if (driver.getLeftBumper()) {
			if (!gyroReset) {
				DriveTrain.resetHyro();
				DriveTrain.setPIDtoSmallGyro();
				gyroReset = true;
			}
			if (!headingSnap) {
				headingSnapValue = RRCPSkinnyServer.getHeading();
				headingSnap = true;
			}
			DriveTrain.turnSmallAngle(headingSnapValue);
			// DriveTrain.turnAngle(RRCPSkinnyServer.getHeading());
		} else if (driver.getRightBumper()) {
			DriveTrain.humanDrive(driver.getLeftStickYAxis() * 0.75,
					driver.getRightStickYAxis() * 0.75);
			gyroReset = false;
			headingSnap = false;
		} else {

			DriveTrain.humanDrive(driver.getLeftStickYAxis(),
					driver.getRightStickYAxis());
			// System.out.println("driving");
			gyroReset = false;
			headingSnap = false;

		}
		
		if(driver.getYButton()) {
			DriveTrain.encoderReset();
		} else if(driver.getXButton()) {
			DriveTrain.resetHyro();
		}
		// }
		// }

		// if(driver.getStartButton()) newDrive = true;
		// if(driver.getBackButton()) newDrive = false;

		if (driver.getBButton()) {
			// DriveTrain.resetHyro();
			// DriveTrain.encoderReset();
			latched = false;
		}
		//
		if (driver.getAButton()) {
			// DriveTrain.driveStraightHyro(0.5);
			latched = true;
		}
		//
		if (latched) {
			if (Arm.getEncoder() > (Constants.ARM_LOW_POS - .05)) {
				Scaling.engageServo();
			}
		} else {
			Scaling.disengageServo();
		}

		/*
		 * Manip Controls
		 * 
		 * Right trigger: shooter speed Right bumper: exgest Left bumper: ingest
		 * Back button: inject
		 * 
		 * A button: set arm LOW X button: set arm INGEST B button: set arm
		 * DEFENSE Y button: set arm HIGH
		 * 
		 * Right stick: manual arm control
		 */

		if (Math.abs(manip.getLeftTriggerAxis()) < 0.1) {
			Shooter.setShooterSpeedNoPID(manip.getRightTriggerAxis());
		} else {
			Shooter.setShooterSpeedNoPID(manip.getLeftTriggerAxis() * 0.65);
		}

		if (manip.getRightBumper()) {
			Ingesting.exgest();
			Shooter.shooterExgest();
		} else if (manip.getLeftBumper()) {
			Ingesting.ingest();
			Shooter.shooterIngest();
		} else if (manip.getBackButton()) {
			if (manip.getRightTriggerAxis() > 0.5) {
				if (Shooter.getShooterEncoderVelocity() != 0) {
					Ingesting.injectAfterSpeed(Constants.MAX_SHOT_SPEED);
				} else {
					Ingesting.inject();
				}
			} else if (manip.getLeftTriggerAxis() > 0.5) {
				if (Shooter.getShooterEncoderVelocity() != 0) {
					Ingesting.injectAfterSpeed(Constants.BATTER_SHOT_SPEED);
				} else {
					Ingesting.inject();
				}
			}
		} else {
			Ingesting.holdBall();
			Ingesting.resetHasReachedSpeed();
			Ingesting.resetTimer();
		}

		if (manip.getAButton()) {
			Arm.setArmPos(Constants.ARM_POS.LOW);
			Arm.resetAdjustment();
		} else if (manip.getXButton()) {
			Arm.setArmPos(Constants.ARM_POS.ING);
			Arm.resetAdjustment();
		} else if (manip.getBButton()) {
			Arm.setArmPos(Constants.ARM_POS.DEF);
			Arm.resetAdjustment();
		} else if (manip.getYButton()) {
			Arm.setArmPos(Constants.ARM_POS.SHOOT);
			if (manip.getDPad() != 90 && manip.getDPad() != 270) {
				Arm.resetAdjustmentInit();
			}
			if (manip.getDPad() == 90) {
				Arm.changeAdjustment(Constants.POSITIVE_ARM_ADJUSTMENT);
			} else if (manip.getDPad() == 270) {
				Arm.changeAdjustment(Constants.NEGATIVE_ARM_ADJUSTMENT);
			}
		} else if (manip.getStartButton()) {
			if (!distanceSnap) {
				Arm.setArmAdjustmentFromDistance(RRCPSkinnyServer.getDistance());
				distanceSnap = true;
			}
			Arm.setArmPos(Constants.ARM_POS.SHOOT);
		} else {
			Arm.setSpeed(-manip.getRightStickYAxis());
			Arm.resetAdjustment();
			distanceSnap = false;
		}

		
		
		if (driver.getStartButton()) {
			if (!toggle) {
				if (lightThing) {
					lightThing = false;
				} else {
					lightThing = true;
				}
				toggle = true;
			}
		} else {
			toggle = false;
		}
		
		

		if (manip.getDPad() == 315 || manip.getDPad() == 0
				|| manip.getDPad() == 45) {
			Scaling.scale(1);
		} else if (manip.getDPad() == 90) {
			if (!manip.getYButton()) {
				Scaling.scale(0.3);
			}
		} else if (manip.getDPad() == 135 || manip.getDPad() == 180
				|| manip.getDPad() == 225) {
			Scaling.descale(1);
		} else {
			Scaling.stopScaling();
		}
		//
	}
	
	public static boolean lightThing = false, toggle = false;

	public static boolean getLightButtonThing() {
		return lightThing;
	}
}
