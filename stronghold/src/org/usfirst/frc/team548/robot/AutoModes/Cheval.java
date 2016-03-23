package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;


public class Cheval extends AutoMode {

	public Cheval() {
		super("Cheval");
	}
	
	protected void run() {
		//time based
//		driveStraightWithHyroWithArmPos(1.6, 0.4, Constants.ARM_POS.DEF);
//		setArmToPosInTime(1.5, Constants.ARM_POS.LOW);
//		driveStraightWithHyroWithArmPos(1.5, 0.6, Constants.ARM_POS.LOW);
		
		//encoder distance
		driveDistanceWithArmPos(2, 0.5, 30000, 90000, Constants.ARM_POS.DEF);
		setArmToPosInTime(1, Constants.ARM_POS.LOW);
		driveDistanceWithArmPos(3, 0.6, 54000, 180000, Constants.ARM_POS.LOW);
	}

}