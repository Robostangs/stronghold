package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;

import CameraStuff.RRCPSkinnyServer;

public class Moat extends AutoMode{

	public Moat() {
		super ("Moat");
	}

	protected void run() {		
		//time based
		//driveStraightWithHyroWithArmPos(4, 0.5, Constants.ARM_POS.DEF);

		//encoder distance
		
		driveDistanceWithArmPos(6, 0.6, 15000, 290000, Constants.ARM_POS.DEF);
		turnToAngleWithVisionInTime(4);
		
		setArmToPosInTime(1, Constants.ARM_POS.SHOOT);
		Arm.setArmAdjustmentFromDistance(RRCPSkinnyServer.getDistance());
		setArmToPosInTime(1.5, Constants.ARM_POS.SHOOT);
		shootAfterRamp(5, 3, 1, 4700, Constants.ARM_POS.SHOOT);
	}
}