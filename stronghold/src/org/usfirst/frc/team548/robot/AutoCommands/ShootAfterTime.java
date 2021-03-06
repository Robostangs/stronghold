package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.Ingesting;
import org.usfirst.frc.team548.robot.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootAfterTime extends AutoCommandBase {
	
	private double rampTime, power, shootAfterSpeed;
	private Constants.ARM_POS position;
	private static boolean hasReachedSpeed = false;
	public ShootAfterTime(double timeOut, double shootAfter, double power, double shootAfterSpeed, Constants.ARM_POS pos) {
		super(timeOut);
		this.power = power;
		this.rampTime = shootAfter;
		this.shootAfterSpeed = shootAfterSpeed;
		position = pos;
	}

	public void init() {
		Ingesting.resetHasReachedSpeed();
		Ingesting.resetTimer();
	}

	protected void run() {
//		Shooter.setShooterSpeedNoPID(power);
//		if(timer.get() >= rampTime) {
//			Ingesting.inject();
//		}
//		Arm.resetAdjustment();
		Arm.setArmPos(position);
		SmartDashboard.putNumber("Shooter Actual Velocity", Shooter.getShooterEncoderVelocity());
		Shooter.setShooterSpeedNoPID(power);
		
		if((this.timeOut - timer.get()) < .5) {
			Ingesting.inject();
		} else {
			Ingesting.injectAfterSpeed(shootAfterSpeed);	
		}
		SmartDashboard.putNumber("ENCODER", Arm.getEncoder());
	}

	public void end() {
		Shooter.stop();
		Ingesting.stopIngesting();
		
	}

	protected String getCommandName() {
		return "Shoot After Time";
	}

}
