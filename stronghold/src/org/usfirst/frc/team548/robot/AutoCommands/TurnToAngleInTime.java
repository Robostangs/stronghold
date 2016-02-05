package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngleInTime extends AutoCommandBase {
	
	double angle = 0;
	public TurnToAngleInTime(double timeOut, double angle) {
		super(timeOut);
		// TODO Auto-generated constructor stub
		this.angle = angle;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		DriveTrain.resetHyro();
		DriveTrain.resetPIDInit();
		
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		DriveTrain.turnAngle(angle);
		SmartDashboard.putNumber("Hyro", DriveTrain.getHyroAngle());
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		DriveTrain.disablePID();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Turn to angle";
	}

}
