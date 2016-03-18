package org.usfirst.frc.team548.robot;

import java.util.ArrayList;

import CameraStuff.RRCPSkinnyServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOperated {
	private static XboxController driver, manip;
	private static TeleOperated instance = null;
	// static boolean newDrive = false;
	private static int shootingSpeed = 3000;
	private static boolean distanceSnap = false;
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

	static String data = "";

	public static void run() {

		/*
		 * Driver Controls
		 * 
		 * Tank drive on sticks
		 */

		 if(driver.getAButton()) {
			 DriveTrain.resetHyro();
		 }
		 if(driver.getBButton()) {
			 DriveTrain.turnAngle(30);
		 }
		// } else if(driver.getBButton()) {
		// DriveTrain.driveStraightHyro(.5);
		// } else {
		// if(newDrive) {
		// DriveTrain.driveForza(driver.getLeftStickXAxis(),
		// driver.getBothTriggerAxis(), driver.getXButton());
		// } else {
//		
		if(driver.getLeftBumper()) {
			if(!gyroReset) {
				DriveTrain.resetHyro();
				gyroReset = true;
			}
//			DriveTrain.turnAngle(30);
			DriveTrain.turnAngle(RRCPSkinnyServer.getHeading());
			System.out.println(RRCPSkinnyServer.getHeading());
		} else if(driver.getRightBumper()) {
			DriveTrain.humanDrive(driver.getLeftStickYAxis() * 0.75, driver.getRightStickYAxis() * 0.75);
			gyroReset = false;
		} else {
			DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
			gyroReset = false;
		}
		
		
		
		
		// }
		// }

		// if(driver.getStartButton()) newDrive = true;
		// if(driver.getBackButton()) newDrive = false;

		// if (driver.getBButton()) {
		// DriveTrain.encoderReset();
		// }
		//
		// if(driver.getAButton()) {
		// DriveTrain.driveStraightHyro(0.5);
		// }


//		if (driver.getLeftBumper()) {
//			Shooter.setSpeed(shootingSpeed);
//		} else {
		if(Math.abs(manip.getLeftTriggerAxis()) < 0.1) {
			Shooter.setShooterSpeedNoPID(manip.getRightTriggerAxis());
		} else {
			Shooter.setShooterSpeedNoPID(manip.getLeftTriggerAxis() * 0.65);
		}
//		}
//
//		if (driver.getBackButton()) {
//			shootingSpeed += 200;
//		} else if (driver.getStartButton()) {
//			shootingSpeed -= 200;
//		}


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
		
		
//    	if(manip.getDPad() == 0) { 	
//    		Scaling.scale(0.3);
//    	} else if(manip.getDPad() == 90) {
//    		Scaling.scale(0.5);
//    	} else if(manip.getDPad() == 180) {
//    		Scaling.scale(0.75);
//    	} else 
    	if(manip.getDPad() == 0) {
    		Scaling.scale(1);
    	} else if(manip.getDPad() == 90) {
    		Scaling.scale(0.5);
    	} else if(manip.getDPad() == 180) {
    		Scaling.descale(1);
    	} else {
    		Scaling.stopScaling();
    	}
		

		if (manip.getRightBumper()) {
			Ingesting.exgest();
			Shooter.shooterExgest();
		} else if (manip.getLeftBumper()) {
			Ingesting.ingest();
			Shooter.shooterIngest();
		} else if (manip.getBackButton()) {
//			Ingesting.inject();
			Ingesting.injectAfterSpeed(Constants.MAX_SHOT_SPEED);
		} else {
			Ingesting.holdBall();
			Ingesting.resetHasReachedSpeed();
//			if(Math.abs(manip.getRightTriggerAxis()) < 0.1) {
//				Shooter.stop();
//			}
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
		} else if(manip.getStartButton()) {
			if(!distanceSnap) {
				//insert method to get desired arm position from camera
				distanceSnap = true;
			}
				//set arm to desired position
				//Arm.setArmPos(pos);	
		} else {
			Arm.setSpeed(-manip.getRightStickYAxis());
			Arm.resetAdjustment();
			distanceSnap = false;
		}

	}

	public static int getShootingSpeed() {
		return shootingSpeed;
	}
}
