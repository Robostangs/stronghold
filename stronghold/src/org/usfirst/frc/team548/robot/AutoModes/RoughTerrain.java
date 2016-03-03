package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class RoughTerrain extends AutoMode{

	public RoughTerrain() {
		super ("Rough Terrain");
	}

	protected void run() {
		//time based
		driveStraightWithHyroWithArmPos(3, 0.6, Constants.ARM_POS.DEF);
		
		//encoder distance
//		driveDistanceWithArmPos(4, 0.6, 15000, 127000, Constants.ARM_POS.DEF);
	}
}