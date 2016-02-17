package org.usfirst.frc.team548.robot.AutoModes;

public class CrossLowBar extends AutoMode {

	public CrossLowBar() {
		super("Cross Low Bar");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		driveStraightWithHyro(4, .5);
	}

}
