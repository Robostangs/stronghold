package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class HighGoalBatterLowShotThroughLowBar extends AutoMode{

	public HighGoalBatterLowShotThroughLowBar() {
		super ("High Goal Batter Shot Through Low Bar");
	}

	protected void run() {
		//encoder based
		driveDistanceWithArmPos(5, 0.5, 15000, 127000, Constants.ARM_POS.LOW);
		turnToAngleInTime(1.5, 56.5, false);
		driveDistanceWithArmPos(5, 0.5, 15000, 120000, Constants.ARM_POS.DEF);
		//shootAfterRamp(3.5, 2.5, 1, Constants.ARM_POS.DEF);
	}
}
