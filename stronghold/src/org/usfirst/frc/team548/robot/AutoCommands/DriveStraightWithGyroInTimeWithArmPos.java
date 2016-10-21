package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.DriveTrain;

public class DriveStraightWithGyroInTimeWithArmPos extends AutoCommandBase {
	
	private double power;
	private Constants.ARM_POS position;
	public DriveStraightWithGyroInTimeWithArmPos(double timeOut, double power, Constants.ARM_POS pos) {
		super(timeOut);
		this.power = power;
		this.position = pos;
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
		//Arm.setArmPos(position);
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		DriveTrain.stop();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Drive Straight With Hyro In Time Wiht Arm Pos";
	}

}
