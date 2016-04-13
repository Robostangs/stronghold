package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.DriveTrain;

import CameraStuff.RRCPSkinnyServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngleWithVision extends AutoCommandBase{
	double angle = 0;
	
	
	public TurnToAngleWithVision(double timeOut) {
		// TODO Auto-generated method stub
		super(timeOut);
		// TODO Auto-generated constructor stub
		this.angle = angle;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		DriveTrain.resetHyro();
		
		DriveTrain.resetPIDInit();
		
		angle = RRCPSkinnyServer.getHeading()-4;
		System.out.println(angle);
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		DriveTrain.turnSmallAngle(angle);
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
		return "Turn to angle with vision";
	}

}
