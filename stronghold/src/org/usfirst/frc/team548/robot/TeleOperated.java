package org.usfirst.frc.team548.robot;

public class TeleOperated {
	XboxController driver, manip;
	
	public TeleOperated() {
		driver = new XboxController(Constants.XBOX_DRIVER_POS);
		manip = new XboxController(Constants.XBOX_MANIP_POS);
	}

	public void run() {
		
        DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
        
	}
	
}
