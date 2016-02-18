package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

import com.ni.vision.NIVision.ConcentricRakeDirection;

public class FirstAuto extends AutoMode {

	public FirstAuto() {
		super("First Auto");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		setArmToPosInTime(2, Constants.ARM_POS.LOW);
		driveStraightWithHyro(5, 0.5);
		turnToAngleInTime(10, 45);
		setArmToPosInTime(5, Constants.ARM_POS.SHOOT);
		//shootAfterRamp(7, 5, .75);
	}

}
