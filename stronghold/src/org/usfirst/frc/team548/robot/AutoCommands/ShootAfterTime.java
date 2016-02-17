package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Ingesting;
import org.usfirst.frc.team548.robot.Shooter;

public class ShootAfterTime extends AutoCommandBase {
	
	private double rampTime, power;
	public ShootAfterTime(double timeOut, double shootAfter, double power) {
		super(timeOut);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		Shooter.setShooterSpeedNoPID(power);
		if(timer.get() >= rampTime) {
			Ingesting.inject();
		}
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		Shooter.stop();
		Ingesting.stopIngesting();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Shoot After Time";
	}

}
