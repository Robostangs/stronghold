package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;

public class HighGoalCourtyardShotThroughLowBar extends AutoMode {

	public HighGoalCourtyardShotThroughLowBar() {
		super("High Goal Courtyard Shot Through Low Bar");
	}

	protected void run() {
//		encoder distance
		driveDistanceWithArmPos(4.5, 0.5, 15000, 380000, Constants.ARM_POS.LOW);
		turnToAngleInTime(1, 65, false);
		driveDistanceWithArmPos(2, 0.4, 15000, 100000, Constants.ARM_POS.LOW);
		//turnToAngleWithVisionInTime(1);
		setArmToPosInTime(1, Constants.ARM_POS.SHOOT);
		setArmToPosInTime(1.5, Constants.ARM_POS.AUTO_SHOOT);
		shootAfterRamp(5, 4, 1, 34500, Constants.ARM_POS.AUTO_SHOOT);
	}

}
