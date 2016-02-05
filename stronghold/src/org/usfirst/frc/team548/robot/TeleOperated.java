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
          if(Math.abs(manip.getRightStickYAxis()) > 0.1) {
        	  Arm.setSpeed(manip.getRightStickYAxis());
          } else {
        	  Arm.stopArm();
          }
          
          /*
           * A button = run shooter wheels
           * left bumper = inject ball into shooter wheels
           * 
           * right trigger = ingest
           * left trigger = exgest
           * neither = run ingesting inward slowly to hold ball
           */
          if(manip.getAButton()) {
        	  //replace with PID asap (but after arm PID)
        	  Shooter.setShooterSpeedNoPID(Constants.SHOOTER_SPEED_NO_PID);
        	  if(manip.getLeftBumper()) {
        		  Ingesting.inject();
        	  }
          } else {
	          if(manip.getRightTriggerButton()) {
	        	  Ingesting.ingest();
	          } else if(manip.getLeftTriggerButton()) {
	        	  Ingesting.exgest();
	          } else {
	        	  Ingesting.holdBall();
	          }
          }
	}
	
}
