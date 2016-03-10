package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class LowShotBatterThroughLowBar extends AutoMode{

	public LowShotBatterThroughLowBar() {
		super ("Low Shot Batter Through Low Bar");
	}

	protected void run() {
		//encoder distance
		driveDistanceWithArmPos(4, 0.5, 15000, 355000, Constants.ARM_POS.LOW);
		turnToAngleInTime(1.5, 54, false);
		driveDistanceWithArmPos(5, 0.5, 15000, 160000, Constants.ARM_POS.DEF);
		shootAfterRamp(3.5, 2.5, 1, Constants.ARM_POS.DEF);
	}
}