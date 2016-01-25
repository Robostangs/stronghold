/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.DriveTrain;


/**
 *
 * @author Alex
 */
public class DriveDistanceInTime extends AutoCommandBase{

    private int distance;
    public DriveDistanceInTime(double timeOut, int distance) {
        super(timeOut);
        this.distance = distance;
    }

    @Override
    protected void run() {    
    	//DriveTrain.driveDistance(distance, .26);
    	System.out.println("We're driving");
    	//this.setDone(DriveTrain.driveDone);
    }
    @Override
    public void end() {
        //DriveMotors.stopMotors();
    }

    @Override
    protected String getCommandName() {
        return "DriveDistanceInTime";
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//DriveMotors.resetEncoders();
	}
    
}
