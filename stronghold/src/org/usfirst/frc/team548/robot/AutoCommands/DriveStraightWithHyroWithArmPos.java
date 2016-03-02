package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.DriveTrain;

public class DriveStraightWithHyroWithArmPos extends AutoCommandBase {
	
	private double power;
	private Constants.ARM_POS position;
	public DriveStraightWithHyroWithArmPos(double timeOut, double power, Constants.ARM_POS pos) {
		super(timeOut);
		this.power = power;
		this.position = pos;
	}

	public void init() {
		DriveTrain.resetHyro();
	}

	protected void run() {
		DriveTrain.driveStraightHyro(power);
		Arm.setArmPos(position);	
	}

	public void end() {
		DriveTrain.stop();
	}

	protected String getCommandName() {
		return "Drive Straight With Hyro In Time With Arm Pos";
	}

}
