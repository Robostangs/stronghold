package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class CrossLowBar extends AutoMode {

	public CrossLowBar() {
		super("Cross Low Bar");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		driveStraightWithHyroWithArmPos(4, .5, Constants.ARM_POS.LOW);
	}

}