/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.AutoCommands.*;


/**
 *
 * @author Alex
 */
public abstract class AutoMode {
    
    private String autoName;
    
    public AutoMode(String autoName) {
        this.autoName = autoName;
        System.out.println("AUTO: " + this.autoName + " has been initialized!");
    }
    
    public void start() {
        System.out.println("AUTO: " + this.autoName + " has started!");
        this.run();
        System.out.println("AUTO: " + this.autoName + " has ended!");
    }
    
    protected abstract void run();
    
    protected void waitTime(double seconds) {
        runCommand(new Wait(seconds));
    }
    
    protected void turnToAngleInTime(double seconds, double angle, boolean reset) {
        runCommand(new TurnToAngleInTime(seconds, angle, reset));
    }
    
    protected void drivePowerInTime(double seconds, double power) {
    	runCommand(new DrivePowerInTime(seconds, power));
    }
    
    protected void driveStraightWithHyroWithArmPos(double seconds, double power, Constants.ARM_POS pos) {
    	runCommand(new DriveStraightWithGyroInTimeWithArmPos(seconds, power, pos));
    }
    
    protected void setArmToPosInTime(double seconds, Constants.ARM_POS pos) {
    	runCommand(new RaiseArmInTime(seconds, pos));
    }
    
    protected void shootAfterRamp(double seconds, double rampSeconds, double power) {
    	runCommand(new ShootAfterTime(seconds, rampSeconds, power));
    }
    
    protected void driveDistanceWithArmPos(double seconds, double power, int threshold, int distance, Constants.ARM_POS pos) {
    	runCommand(new DriveDistanceWithArmPos(seconds, power, threshold, distance, pos));
    }
    
    private static void runCommand(AutoCommandBase command) {
        command.execute();
    }
}
