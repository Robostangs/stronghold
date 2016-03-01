package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class RockWall extends AutoMode{

	public RockWall() {
		super ("rock wall");
	}

	protected void run() {
		driveStraightWithHyroWithArmPos(6, 0.8, Constants.ARM_POS.DEF);
	}

	
}
