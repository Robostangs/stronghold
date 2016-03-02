package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class HighGoalBatterHighShotThroughLowBar extends AutoMode{

	public HighGoalBatterHighShotThroughLowBar() {
		super("High Goal Batter High Shot Through Low Bar");
	}

	protected void run() {
		driveDistanceWithArmPos(5, 0.5, 15000, 127000, Constants.ARM_POS.LOW);
		turnToAngleInTime(1.5, 56.5, false);
		setArmToPosInTime(1.5, Constants.ARM_POS.SHOOT);
		driveDistanceWithArmPos(5, 0.5, 15000, 120000, Constants.ARM_POS.SHOOT);
		shootAfterRamp(2, 1.5, 0.65, Constants.ARM_POS.SHOOT);
	}
}
