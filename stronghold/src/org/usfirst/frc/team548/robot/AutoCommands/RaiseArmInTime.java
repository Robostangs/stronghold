package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;

public class RaiseArmInTime extends AutoCommandBase {
	private Constants.ARM_POS position;
	public RaiseArmInTime(double timeOut, Constants.ARM_POS pos) {
		super(timeOut);
		position = pos;
	}

	public void init() {
		
	}

	protected void run() {
		Arm.setArmPos(position);
	}

	public void end() {
		
	}

	protected String getCommandName() {
		return "SetArmToPos";
	}

}
