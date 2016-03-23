package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;

public class LowBarHighGoal extends AutoMode {

	public LowBarHighGoal() {
		super("Low Bar High Goal");
	}

	protected void run() {
//		encoder distance
		driveDistanceWithArmPos(6, 0.5, 15000, 400000, Constants.ARM_POS.LOW);
		turnToAngleInTime(1.5, 54, false);
		turnToAngleWithVisionInTime(2.5);
		setArmToPosInTime(1, Constants.ARM_POS.SHOOT);
		setArmToPosInTime(1.5, Constants.ARM_POS.AUTO_SHOOT);
		shootAfterRamp(3, 3, 1, 4700, Constants.ARM_POS.AUTO_SHOOT);
	}

}