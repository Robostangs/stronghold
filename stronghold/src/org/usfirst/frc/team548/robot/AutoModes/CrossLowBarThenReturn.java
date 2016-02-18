package org.usfirst.frc.team548.robot.AutoModes;

public class CrossLowBarThenReturn extends AutoMode {
	public CrossLowBarThenReturn() {
		super("Cross Low Bar Then Return");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		driveStraightWithHyro(4, .5);
		driveStraightWithHyro(3, -.5);
	}
}
