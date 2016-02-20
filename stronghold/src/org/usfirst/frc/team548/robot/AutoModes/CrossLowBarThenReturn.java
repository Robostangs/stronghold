package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class CrossLowBarThenReturn extends AutoMode {
	public CrossLowBarThenReturn() {
		super("Cross Low Bar Then Return");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		driveStraightWithHyroWithArmPos(4, .5, Constants.ARM_POS.LOW);
		driveStraightWithHyroWithArmPos(3, -.5, Constants.ARM_POS.LOW);
	}
}
