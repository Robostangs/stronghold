package org.usfirst.frc.team548.robot.AutoModes;

public class FirstAuto extends AutoMode {

	public FirstAuto() {
		super("First Auto");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		setArmToPosInTime(5, 0);
		driveStraightWithHyro(5, 0.5);
		turnToAngleInTime(10, 45);
		setArmToPosInTime(5, 2);
		shootAfterRamp(7, 5, .75);
	}

}
