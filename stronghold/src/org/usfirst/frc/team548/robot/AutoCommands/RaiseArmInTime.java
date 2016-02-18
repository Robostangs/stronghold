package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;

public class RaiseArmInTime extends AutoCommandBase {
	private Constants.ARM_POS position;
	public RaiseArmInTime(double timeOut, Constants.ARM_POS pos) {
		super(timeOut);
		position = pos;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		if(position == Constants.ARM_POS.LOW) {
			Arm.setArmDownToLow();
		} else if(position == Constants.ARM_POS.SHOOT) {
			Arm.setArmShoot();
		} else if (position == Constants.ARM_POS.DEF) {
			Arm.setArmUpToDef();
		}
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "SetArmToPos";
	}

}
