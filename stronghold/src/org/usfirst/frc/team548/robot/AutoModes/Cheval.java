package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class Cheval extends AutoMode {

	public Cheval() {
		super("Cheval");
	}
	
	protected void run() {
		//time based
		driveStraightWithHyroWithArmPos(1, 0.5, Constants.ARM_POS.DEF);
		setArmToPosInTime(1.5, Constants.ARM_POS.LOW);
		driveStraightWithHyroWithArmPos(1.5, 0.8, Constants.ARM_POS.LOW);
		
		//encoder distance
//		driveDistanceWithArmPos(1, 0.5, 10000, 75000, Constants.ARM_POS.DEF);
//		setArmToPosInTime(1.5, Constants.ARM_POS.LOW);
//		driveDistanceWithArmPos(1.5, 0.8, 10000, 75000, Constants.ARM_POS.LOW);
	}

}