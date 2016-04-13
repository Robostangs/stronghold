
package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;

import CameraStuff.RRCPSkinnyServer;

public class Cheval extends AutoMode {

	public Cheval() {
		super("Cheval");
	}
	
	protected void run() {
		//time based
//		driveStraightWithHyroWithArmPos(1.6, 0.4, Constants.ARM_POS.DEF);
//		setArmToPosInTime(1.5, Constants.ARM_POS.LOW);
//		driveStraightWithHyroWithArmPos(1.5, 0.6, Constants.ARM_POS.LOW);
		
		//encoder distance
		driveDistanceWithArmPos(2, 0.5, 10000, 30000, Constants.ARM_POS.DEF);
		setArmToPosInTime(1, Constants.ARM_POS.LOW);
		driveDistanceWithArmPos(3, 0.6, 18000, 60000, Constants.ARM_POS.DEF);
		
		
		setArmToPosInTime(1, Constants.ARM_POS.SHOOT);
		turnToAngleWithVisionInTime(4);
		Arm.setArmAdjustmentFromDistance(RRCPSkinnyServer.getDistance());
		setArmToPosInTime(1.5, Constants.ARM_POS.SHOOT);
		shootAfterRamp(4, 3, 1, 33000, Constants.ARM_POS.SHOOT);
	}

}
