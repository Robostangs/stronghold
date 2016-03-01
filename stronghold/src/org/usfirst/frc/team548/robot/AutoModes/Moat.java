package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class Moat extends AutoMode{

	public Moat() {
		super ("moat");
	}

	protected void run() {
		driveStraightWithHyroWithArmPos(5, 0.7, Constants.ARM_POS.DEF);
	}
}
