package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class Cheval extends AutoMode {

	public Cheval() {
		super("cheval");
	}

	@Override
	protected void run() {
		
		// TODO Auto-generated method stub
		driveStraightWithHyroWithArmPos(1, 0.5, Constants.ARM_POS.DEF);
		setArmToPosInTime(1.5, Constants.ARM_POS.LOW);
		driveStraightWithHyroWithArmPos(1.5, 0.8, Constants.ARM_POS.LOW);
	}

}
