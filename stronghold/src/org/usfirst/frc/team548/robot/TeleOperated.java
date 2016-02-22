package org.usfirst.frc.team548.robot;

public class TeleOperated {
	private static XboxController driver, manip;
	private static TeleOperated instance = null;
	
	public static TeleOperated getInstance(){
		if(instance == null){
			instance = new TeleOperated();
		}
		return instance;
	}
	
	public TeleOperated() {
		driver = new XboxController(Constants.XBOX_DRIVER_POS);
		manip = new XboxController(Constants.XBOX_MANIP_POS);
	}
	static boolean newDrive = false;
	public static void run() {
        
//        if(driver.getAButton()) {
//        	DriveTrain.encoderReset();
//        	DriveTrain.resetHyro();
//        	DriveTrain.resetPIDInit();
//        }
//        
//        if(driver.getBButton()) {
//
//        		DriveTrain.turnAngle(90);
//        	
//        } else {
//        	DriveTrain.disablePID();
            
//        }
            
            if(driver.getAButton()) {
            	DriveTrain.resetHyro();
            } else if(driver.getBButton()) {
            	DriveTrain.driveStraightHyro(.5);
            } else {
            	if(newDrive) {
            		DriveTrain.driveForza(driver.getLeftStickXAxis(), driver.getBothTriggerAxis(), driver.getXButton());
                } else {
                	DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());	
                }
            }
            
            if(driver.getStartButton()) newDrive = true;
            if(driver.getBackButton()) newDrive = false;
            
            System.out.println("L: "+ DriveTrain.getLeftRate()+" R: "+DriveTrain.getRightRate());
            
      //Manip controls?!         
          
          /*
           * A button = run shooter wheels
           * left bumper = inject ball into shooter wheels
           * 
           * right trigger = ingest
           * left trigger = exgest
           * neither = run ingesting inward slowly to hold ball
           */
//          if(manip.getAButton()) {
//        	  //replace with PID asap (but after arm PID)
//        	  Shooter.setShooterSpeedNoPID(Constants.SHOOTER_SPEED_NO_PID);
////        	  if(manip.getRightBumper()) {
////        		  Ingesting.inject();
//        	  }
//          } else {
        	  
          
           Shooter.setShooterSpeedNoPID(-manip.getRightTriggerAxis());
          
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
	        	  
	        		  Arm.setArmToLow();
	        	  
	          } else if(manip.getXButton()) {
	        	  if(Arm.isArmHigh()) {
	        		  Arm.setArmDownToIng();
	        	  } else {
	        		  Arm.setArmUpToIng();
	        	  }
	          } else if(manip.getBButton()) {
	        	  
	        		  Arm.setArmToDef();
	        	  
	        		  
	        	  
	          } else if(manip.getYButton()) {
	        	  Arm.setArmShoot();
	          } else {
	        	  Arm.resetSnap();
	        	  Arm.setSpeed(manip.getRightStickYAxis());
	          }
	}
	
}
