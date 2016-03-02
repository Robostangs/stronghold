package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;

public class SetArmToPosInTime extends AutoCommandBase {
	private Constants.ARM_POS position;
	public SetArmToPosInTime(double timeOut, Constants.ARM_POS pos) {
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
		return "Set Arm To Pos In Time";
	}

}
