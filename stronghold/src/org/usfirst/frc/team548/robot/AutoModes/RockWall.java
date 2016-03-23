package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class RockWall extends AutoMode{

	public RockWall() {
		super ("Rock Wall");
	}

	protected void run() {
		//time based
		driveStraightWithHyroWithArmPos(4, 0.6, Constants.ARM_POS.DEF);
		
		//encoder distance
//		driveDistanceWithArmPos(6, 0.8, 45000, 600000, Constants.ARM_POS.DEF);
	}

	
}