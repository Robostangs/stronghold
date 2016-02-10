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
            DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
//        }
            
            
      //Manip controls?!
            
            
          //*****NEED TO IMPLEMENT ARM PID POSITIONS ASAP*******
            
          //Use right joystick to manually move arm
          if(Math.abs(manip.getLeftStickYAxis()) > 0.15) {
        	  
          } else {
        	 // Arm.stopArm();
          }
          
          
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
	          if(manip.getLeftBumper()) {
	        	  Ingesting.exgest();
	        	  Shooter.shooterExgest();
	          } else if(manip.getRightBumper()) {
	        	  Ingesting.ingest();
	        	  Shooter.shooterIngest();
	          } else if (manip.getAButton()){
	        	  Ingesting.inject();
	          } else {
	        	  Ingesting.holdBall();
	        	  //Shooter.stop();
	          }
	          
	          if(manip.getYButton()) {
	        	  Arm.setArmPos(850);
	          } else {
	        	  Arm.setSpeed(manip.getLeftStickYAxis());
	          }
	          
	          if(manip.getBButton()) {
	        	  Arm.setSpeed(-0.5);
	          }
          //}
	}
	
	public static double getStick() {
		return manip.getLeftStickYAxis();
	}
	
}
