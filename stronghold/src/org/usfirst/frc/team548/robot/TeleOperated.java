package org.usfirst.frc.team548.robot;

public class TeleOperated {
	private static XboxController driver, manip;
	private static TeleOperated instance = null;
//	static boolean newDrive = false;
	
	public static TeleOperated getInstance() {
		if(instance == null){
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
		
//            if(driver.getAButton()) {
//            	DriveTrain.resetHyro();
//            } else if(driver.getBButton()) {
//            	DriveTrain.driveStraightHyro(.5);
//            } else {
//            	if(newDrive) {
//            		DriveTrain.driveForza(driver.getLeftStickXAxis(), driver.getBothTriggerAxis(), driver.getXButton());
//                } else {
                	DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());	
//                }
//            }
            
//            if(driver.getStartButton()) newDrive = true;
//            if(driver.getBackButton()) newDrive = false;
            
      /*
       * Manip Controls
       * 
       * Right trigger: shooter speed
       * Right bumper: exgest
       * Left bumper: ingest
       * Back button: inject
       * 
       * A button: set arm LOW
       * X button: set arm INGEST
       * B button: set arm DEFENSE
       * Y button: set arm HIGH
       * 
       * Right stick: manual arm control
       */
            if(manip.getPOV() == 0) {
            	Shooter.setSpeed(90000);
            } else {
            Shooter.setShooterSpeedNoPID(manip.getRightTriggerAxis());
            }
            if(manip.getRightBumper()) {
            	Ingesting.exgest();
            	Shooter.shooterExgest();
            } else if(manip.getLeftBumper()) {
            	Ingesting.ingest();
	        	Shooter.shooterIngest();
	        } else if (manip.getBackButton()){
	        	Ingesting.inject();
	        } else {
	        	Ingesting.holdBall();
	        }
	          
            if(manip.getAButton()) {
            	Arm.setArmPos(Constants.ARM_POS.LOW);
            	Arm.resetAdjustment();
	        } else if(manip.getXButton()) {
	        	Arm.setArmPos(Constants.ARM_POS.ING);
	        	Arm.resetAdjustment();
	        } else if(manip.getBButton()) {
	        	Arm.setArmPos(Constants.ARM_POS.DEF); 
	        	Arm.resetAdjustment();
	        } else if(manip.getYButton()) {
	        	Arm.setArmPos(Constants.ARM_POS.SHOOT);
	        	if(manip.getDPad() != 90 && manip.getDPad() != 270) {
	        		Arm.resetAdjustmentInit();
	        	}
	        	if(manip.getDPad() == 90) {
	        		Arm.changeAdjustment(Constants.POSITIVE_ARM_ADJUSTMENT);
	        	} else if (manip.getDPad() == 270) {
	        		Arm.changeAdjustment(Constants.NEGATIVE_ARM_ADJUSTMENT);
	        	}
	        } else {
	        	Arm.setSpeed(manip.getRightStickYAxis());
	        	Arm.resetAdjustment();
	        }
            
    }
}
