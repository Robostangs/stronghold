package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class Portcullis extends AutoMode {

	public Portcullis() {
		super("Portcullis");
	}

	protected void run() {
		//time based
		driveStraightWithHyroWithArmPos(3, 0.5, Constants.ARM_POS.LOW);
		driveStraightWithHyroWithArmPos(4, 0.2, Constants.ARM_POS.DEF);
		
		//encoder distance
//		driveDistanceWithArmPos(2, 0.5, 10000, 75000, Constants.ARM_POS.LOW);
//		driveDistanceWithArmPos(4, 0.2, 5000, 100000, Constants.ARM_POS.DEF);
	}

}
