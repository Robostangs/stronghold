package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class RoughTerrain extends AutoMode{

	public RoughTerrain() {
		super ("rough terrain");
	}

	protected void run() {
		driveStraightWithHyroWithArmPos(4, 0.6, Constants.ARM_POS.DEF);
	}
}
