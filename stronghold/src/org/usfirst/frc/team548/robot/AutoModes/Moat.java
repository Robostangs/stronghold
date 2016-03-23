package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class Moat extends AutoMode{

	public Moat() {
		super ("Moat");
	}

	protected void run() {		
		//time based
		driveStraightWithHyroWithArmPos(4, 0.8, Constants.ARM_POS.DEF);

		//encoder distance
//		driveDistanceWithArmPos(5, 0.7, 60000, 600000, Constants.ARM_POS.DEF);
	}
}