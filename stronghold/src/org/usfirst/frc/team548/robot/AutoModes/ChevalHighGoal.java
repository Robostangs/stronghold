package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;

import CameraStuff.RRCPSkinnyServer;

public class ChevalHighGoal extends AutoMode {

	public ChevalHighGoal() {
		super("Cheval High Goal");
	}
	
	protected void run() {
		//time based
//		driveStraightWithHyroWithArmPos(1.6, 0.4, Constants.ARM_POS.DEF);
//		setArmToPosInTime(1.5, Constants.ARM_POS.LOW);
//		driveStraightWithHyroWithArmPos(1.5, 0.6, Constants.ARM_POS.LOW);
		
		//encoder distance
		driveDistanceWithArmPos(2, 0.5, 30000, 90000, Constants.ARM_POS.DEF);
		setArmToPosInTime(1, Constants.ARM_POS.LOW);
		driveDistanceWithArmPos(3, 0.6, 54000, 180000, Constants.ARM_POS.LOW);
		turnToAngleWithVisionInTime(4);
		
		setArmToPosInTime(1, Constants.ARM_POS.SHOOT);
		Arm.setArmAdjustmentFromDistance(RRCPSkinnyServer.getDistance());
		setArmToPosInTime(1.5, Constants.ARM_POS.SHOOT);
		shootAfterRamp(5, 3, 1, 4700, Constants.ARM_POS.SHOOT);
	}

}