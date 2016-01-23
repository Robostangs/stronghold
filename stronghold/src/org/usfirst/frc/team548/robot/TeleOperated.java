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
		//manip = new XboxController(Constants.XBOX_MANIP_POS);
	}

	public static void run() {
		
        DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
        //DriveTrain.humanDrive(0, 0);
	}
	
}
