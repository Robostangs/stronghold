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
		driveDistanceWithArmPos(5, 0.5, 10000, 30000, Constants.ARM_POS.DEF);
		setArmToPosInTime(1, Constants.ARM_POS.LOW);
		driveDistanceWithArmPos(4, 0.8, 10000, 80000, Constants.ARM_POS.LOW);
	}

}
