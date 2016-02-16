package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Arm;

public class RaiseArmInTime extends AutoCommandBase {
	private int position;
	public RaiseArmInTime(double timeOut, int pos) {
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
		if(position == 0) {
			Arm.setArmDownToLow();
		} else if(position == 1) {
			Arm.setArmShoot();
		} else if (position == 2) {
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
