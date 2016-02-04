package org.usfirst.frc.team548.robot;

public class TeleOperated {
	private static XboxController driver; //, manip;
	private static TeleOperated instance = null;
	
	public static TeleOperated getInstance(){
		if(instance == null){
			instance = new TeleOperated();
		}
		return instance;
	}
	
	public TeleOperated() {
		driver = new XboxController(Constants.XBOX_DRIVER_POS);
		//manip = new XboxController(Constants.XBOX_MANIP_POS);
	}

	public static void run() {
        
        if(driver.getAButton()) {
        	DriveTrain.encoderReset();
        }
        
        if(driver.getBButton()) {
        	boolean done = false;
        	if(DriveTrain.testEncodersDriving(30000)) {
        		done = true;
        	}
        	if(done) {
        		DriveTrain.turnAngle(90, 0.03, 0.01, 0);
        	}
        } else {
        	DriveTrain.disablePID();
            DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
        }
	}
	
}
