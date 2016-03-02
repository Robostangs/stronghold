package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.DriveTrain;

public class DrivePowerInTime extends AutoCommandBase {
	
	double power;
	public DrivePowerInTime(double timeOut, double power) {
		super(timeOut);
		this.power = power;
	}
	
	public void init() {
		
	}

	protected void run() {
		DriveTrain.drive(power, power);
	}
	
	public void end() {
		DriveTrain.stop();
	}
	
	protected String getCommandName() {
		return "Drive Power In Time";
	}

}
