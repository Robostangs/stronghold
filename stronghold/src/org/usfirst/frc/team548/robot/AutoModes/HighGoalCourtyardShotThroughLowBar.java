package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;

public class HighGoalCourtyardShotThroughLowBar extends AutoMode {

	public HighGoalCourtyardShotThroughLowBar() {
		super("High Goal Courtyard Shot Through Low Bar");
	}

	protected void run() {
//		encoder distance
		driveDistanceWithArmPos(5, 0.5, 15000, 127000, Constants.ARM_POS.LOW);
		turnToAngleInTime(3, 54.5, false);
		setArmToPosInTime(1.5, Constants.ARM_POS.SHOOT);
		setArmToPosInTime(1.5, Constants.ARM_POS.AUTO_SHOOT);
		shootAfterRamp(4, 3, 1, 4800, Constants.ARM_POS.AUTO_SHOOT);
	}

}
