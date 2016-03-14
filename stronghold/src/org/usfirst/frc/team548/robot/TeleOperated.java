package org.usfirst.frc.team548.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOperated {
	private static XboxController driver, manip;
	private static TeleOperated instance = null;
	// static boolean newDrive = false;
	private static int shootingSpeed = -3000;

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

		// if(driver.getAButton()) {
		// DriveTrain.resetHyro();
		// } else if(driver.getBButton()) {
		// DriveTrain.driveStraightHyro(.5);
		// } else {
		// if(newDrive) {
		// DriveTrain.driveForza(driver.getLeftStickXAxis(),
		// driver.getBothTriggerAxis(), driver.getXButton());
		// } else {
		
		if(driver.getRightBumper()) {
			DriveTrain.humanDrive(driver.getLeftStickYAxis() * 0.5, driver.getRightStickYAxis() * 0.5);
		} else {
			DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
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
//			Shooter.setShooterSpeedNoPID(manip.getRightTriggerAxis());
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
    		Scaling.descale(0.5);
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
			//Ingesting.inject();
			Ingesting.injectAfterSpeed(Constants.BATTER_SHOT_SPEED);
		} else {
			Ingesting.holdBall();
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
//			if (manip.getDPad() != 90 && manip.getDPad() != 270) {
//				Arm.resetAdjustmentInit();
//			}
//			if (manip.getDPad() == 90) {
//				Arm.changeAdjustment(Constants.POSITIVE_ARM_ADJUSTMENT);
//			} else if (manip.getDPad() == 270) {
//				Arm.changeAdjustment(Constants.NEGATIVE_ARM_ADJUSTMENT);
//			}
		} else {
			Arm.setSpeed(manip.getRightStickYAxis());
			Arm.resetAdjustment();
		}

	}

	public static int getShootingSpeed() {
		return shootingSpeed;
	}
}
