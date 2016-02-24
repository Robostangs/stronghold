package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.Ingesting;
import org.usfirst.frc.team548.robot.Shooter;

public class ShootAfterTime extends AutoCommandBase {
	
	private double rampTime, power;
	public ShootAfterTime(double timeOut, double shootAfter, double power) {
		super(timeOut);
		this.power = power;
		this.rampTime = shootAfter;
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
		Arm.resetAdjustment();
		Arm.setArmPos(Constants.ARM_POS.AUTO_SHOOT);
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
