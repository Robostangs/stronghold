package org.usfirst.frc.team548.robot;

import org.usfirst.frc.team548.robot.Constants.ARM_POS;

import CameraStuff.RRCPSkinnyServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOperated {
	private static XboxController driver, manip;
	private static TeleOperated instance = null;
	// static boolean newDrive = false;
	private static double shootingSpeed = 3000, headingSnapValue = 0;
	public static boolean oneDriver = false;

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
		
		if (driver.getStartButton())
			oneDriver = true;
		if (driver.getBackButton())
			oneDriver = false;
		if (!oneDriver) {
			if (driver.getAButton()) {
				Scaling.engageServo();
			} else if (driver.getBButton()) {
				Scaling.disengageServo();
			}

			/*
			 * Driver Controls
			 * 
			 * Tank drive on sticks
			 */

			// } else if(driver.getBButton()) {
			// DriveTrain.driveStraightHyro(.5);
			// } else {
			// if(newDrive) {
			// DriveTrain.driveForza(driver.getLeftStickXAxis(),
			// driver.getBothTriggerAxis(), driver.getXButton());
			// } else {
			//
			// System.out.println("driving");

			if (Math.abs(driver.getTriggers()) < .1) {
				DriveTrain.humanDrive(driver.getLeftStickYAxis(),
						driver.getRightStickYAxis());
			} else {
				// DriveTrain.driveForza(xbox.getTriggers(),
				// xbox.getLeftStickYAxis(), xbox.getXButton());
				DriveTrain.driveForza(driver.getLeftStickXAxis(),
						driver.getTriggers(), driver.getXButton());
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

			// if (driver.getLeftBumper()) {
			// Shooter.setSpeed(shootingSpeed);
			// } else {

			if (Math.abs(manip.getLeftTriggerAxis()) < 0.1) {
				Shooter.setShooterSpeedNoPID(manip.getRightTriggerAxis());
			}
			// }
			//
			// if (driver.getBackButton()) {
			// shootingSpeed += 200;
			// } else if (driver.getStartButton()) {
			// shootingSpeed -= 200;
			// }

			/*
			 * Manip Controls
			 * 
			 * Right trigger: shooter speed (max of 1) Left trigger: shooter
			 * speed (max of 0.65) Right bumper: exgest Left bumper: ingest Back
			 * button: inject
			 * 
			 * A button: set arm LOW X button: set arm INGEST B button: set arm
			 * DEFENSE Y button: set arm HIGH
			 * 
			 * Right stick: manual arm control
			 */

			if (manip.getDPad() == 315 || manip.getDPad() == 0
					|| manip.getDPad() == 45) {
				Scaling.descale(1);
			} else if (manip.getDPad() == 90) {
				if (!manip.getYButton()) {
					Scaling.scale(0.3);
				}
			} else if (manip.getDPad() == 135 || manip.getDPad() == 180
					|| manip.getDPad() == 225) {
				Scaling.scale(1);
			} else {
				Scaling.stopScaling();
			}

			if (manip.getRightBumper()) {
				Ingesting.exgest();
				Shooter.shooterExgest();
			} else if (manip.getLeftBumper()) {
				Ingesting.ingest();
				Shooter.shooterIngest();
				if (Shooter.getShooterEncoderVelocity() > 30000) {
					driver.setRightRumble(1);
				} else {
					driver.setRightRumble(0);
				}
			} else if (manip.getLeftJoystickButton()) {
				Ingesting.inject();
			} else if (manip.getBackButton()) {
				if (manip.getRightTriggerAxis() > 0.5) {
					// if (Shooter.getShooterEncoderVelocity() != 0) {
					Ingesting.injectAfterSpeed(Constants.MAX_SHOT_SPEED);
					// } else {
					// Ingesting.inject();
					// }
				} else if (manip.getLeftTriggerAxis() > 0.5) {
					// if (Shooter.getShooterEncoderVelocity() != 0) {
					Ingesting.injectAfterSpeed(Constants.BATTER_SHOT_SPEED);
					// } else {
					// Ingesting.inject();
					// }
				}
			} else {
				if (Math.abs(manip.getLeftTriggerAxis()) > 0.1) {
					Shooter.setShooterSpeedNoPID(manip.getLeftTriggerAxis());
					Ingesting.injectAfterSpeed(Constants.MAX_SHOT_SPEED);
					driver.setRightRumble(1);
				} else {
					Ingesting.holdBall();
					Ingesting.resetHasReachedSpeed();
					Ingesting.resetTimer();
					driver.setRightRumble(0);
				}
			}

			if (manip.getAButton()) {
				Arm.setArmPos(Constants.ARM_POS.LOW);
				Arm.resetAdjustment();
			} else if (manip.getXButton()) {
				Arm.setArmPos(ARM_POS.SHOOT);
				Arm.setAdjustmentToOuterWorks();
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
			} else {
				Arm.setSpeed(-manip.getRightStickYAxis());
				Arm.resetAdjustment();

			}

		} else {
			//driving
			
				DriveTrain.driveForza(driver.getLeftStickXAxis(),
				driver.getTriggers(), driver.getXButton());
			
			
			//Shooter
			if (driver.getBButton()) {
				Ingesting.exgest();
				Shooter.shooterExgest();
			} else if (driver.getLeftBumper()) {
				Ingesting.ingest();
				Shooter.shooterIngest();
				
			}  else {
				if (driver.getRightBumper()) {
					Shooter.setShooterSpeedNoPID(1);
					Ingesting.injectAfterSpeed(Constants.MAX_SHOT_SPEED);
					//driver.setRightRumble(1);
				} else {
					Ingesting.holdBall();
					Ingesting.resetHasReachedSpeed();
					Ingesting.resetTimer();
					Shooter.setShooterSpeedNoPID(0);
				}
			}
			
			//Arm
			if (manip.getAButton()) {
				Arm.setArmPos(Constants.ARM_POS.LOW);
				Arm.resetAdjustment();
			} else if (manip.getXButton()) {
				Arm.setArmPos(ARM_POS.DEF);
				Arm.setAdjustmentToOuterWorks();
			} else if (manip.getYButton()) {
				Arm.setArmPos(Constants.ARM_POS.SHOOT);
				
			} else {
				Arm.setSpeed(-driver.getRightStickYAxis());
				Arm.resetAdjustment();

			}
		}

	}

	public static boolean lightThing = false, toggle = false;

	public static boolean getLightButtonThing() {
		return lightThing;
	}
}
