package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.Shooter;
import org.usfirst.frc.team548.robot.AutoCommands.DriveDistanceWithArmPos;

import com.ni.vision.NIVision.ConcentricRakeDirection;

public class HighGoal extends AutoMode {

	public HighGoal() {
		super("High Goal");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		driveDistanceWithArmPos(5, 0.5, 15000, 127000, Constants.ARM_POS.LOW);
		turnToAngleInTime(1.5, 56.5, false);
		setArmToPosInTime(2, Constants.ARM_POS.SHOOT);
		shootAfterRamp(4, 2.5, .75);
	}

}
