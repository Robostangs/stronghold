package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class ChevalCross extends AutoMode {

	public ChevalCross() {
		super("Cheal cross only");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistanceWithArmPos(2, 0.5, 10000*3, 30000*3, Constants.ARM_POS.DEF);
		setArmToPosInTime(1, Constants.ARM_POS.LOW);
		driveDistanceWithArmPos(3, 0.6, 18000*3, 60000*3, Constants.ARM_POS.DEF);
	}

}
