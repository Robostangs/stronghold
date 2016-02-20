package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class Portcullis extends AutoMode {

	public Portcullis() {
		super("Portcullis");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		driveStraightWithHyroWithArmPos(3, 0.5, Constants.ARM_POS.LOW);
		driveStraightWithHyroWithArmPos(4, 0.2, Constants.ARM_POS.DEF);
	}

}
