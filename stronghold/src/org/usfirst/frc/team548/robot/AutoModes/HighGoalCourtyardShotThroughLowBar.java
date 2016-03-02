package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class HighGoalCourtyardShotThroughLowBar extends AutoMode {

	public HighGoalCourtyardShotThroughLowBar() {
		super("High Goal Courtyard Shot Through Low Bar");
	}

	protected void run() {
		//encoder distance
		driveDistanceWithArmPos(5, 0.5, 15000, 127000, Constants.ARM_POS.LOW);
		turnToAngleInTime(1.5, 56.5, false);
		setArmToPosInTime(2, Constants.ARM_POS.SHOOT);
		shootAfterRamp(4, 2.5, 0.75, Constants.ARM_POS.SHOOT);
	}

}
