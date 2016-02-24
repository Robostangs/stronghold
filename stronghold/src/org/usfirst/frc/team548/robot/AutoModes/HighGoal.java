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
//		Arm.changeAdjustment(0.05);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.1);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.15);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.2);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(.25);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.3);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.35);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.40);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.45);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.5);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.55);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.6);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.65);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.7);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
//		Arm.changeAdjustment(0.75);
//		setArmToPosInTime(0.1, Constants.ARM_POS.SHOOT);
		shootAfterRamp(4, 2.5, .75);
	}

}
