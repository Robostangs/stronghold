package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Arm;
import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistanceWithArmPos extends AutoCommandBase {
	
	private double power;
	private int distance, thresh;
	private Constants.ARM_POS position;
	public DriveDistanceWithArmPos(double timeOut, double power, int thresh, int distance, Constants.ARM_POS pos) {
		super(timeOut);
		this.power = power;
		this.position = pos;
		this.distance = distance;
		this.thresh = thresh;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		DriveTrain.resetHyro();
		DriveTrain.encoderReset();
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		DriveTrain.driveDistanceNoPID(distance, power, thresh);
    	SmartDashboard.putNumber("Left", DriveTrain.getLeftEncoder());
    	SmartDashboard.putNumber("Right", DriveTrain.getRightEncoder());
		
		Arm.setArmPos(position);
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		DriveTrain.stop();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Drive Distance With Arm Pos";
	}

}