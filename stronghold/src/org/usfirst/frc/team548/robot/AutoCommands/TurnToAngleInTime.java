package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngleInTime extends AutoCommandBase {
	
	double angle = 0;
	boolean reset;
	public TurnToAngleInTime(double timeOut, double angle, boolean r) {
		super(timeOut);
		this.angle = angle;
		this.reset = r;
	}

	public void init() {
		if(reset) {
		DriveTrain.resetHyro();
		}
		DriveTrain.resetPIDInit();
	}

	protected void run() {
		DriveTrain.turnAngle(angle);
		SmartDashboard.putNumber("Hyro", DriveTrain.getHyroAngle());
	}

	public void end() {
		DriveTrain.disablePID();
	}

	protected String getCommandName() {
		return "Turn To Angle In Time";
	}

}
