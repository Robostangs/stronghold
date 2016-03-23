package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class CrossLowBar extends AutoMode {

	public CrossLowBar() {
		super("Cross Low Bar");
	}

	protected void run() {
		//time based
		driveStraightWithHyroWithArmPos(2.5, 0.5, Constants.ARM_POS.LOW);

		//encoder distance
//		driveDistanceWithArmPos(4, 0.5, 45000, 381000, Constants.ARM_POS.LOW);
	}

}