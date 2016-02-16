package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.DriveTrain;

public class DriveStraightWithGyroInTime extends AutoCommandBase {
	
	private double power;
	public DriveStraightWithGyroInTime(double timeOut, double power) {
		super(timeOut);
		this.power = power;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		DriveTrain.resetHyro();
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		DriveTrain.driveStraightHyro(power);
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		DriveTrain.stop();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Drive Straight With Hyro In Time";
	}

}
