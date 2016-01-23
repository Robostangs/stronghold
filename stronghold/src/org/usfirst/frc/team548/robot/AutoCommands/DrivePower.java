package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.DriveTrain;

public class DrivePower extends AutoCommandBase {
	
	double power;
	public DrivePower(double timeOut, double power) {
		super(timeOut);
		this.power = power;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void run() {
		// TODO Auto-generated method stub
		DriveTrain.drive(power, power);
		
	}
	@Override
	public void end() {
		// TODO Auto-generated method stub
		DriveTrain.stop();
	}
	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "DrivePower";
	}

}
