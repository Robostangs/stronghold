/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team548.robot.AutoCommands;

/**
 *
 * @author Alex
 */
public class WaitTime extends AutoCommandBase {

    public WaitTime(double timeOut) {
        super(timeOut);
    }

	public void init() {
		
	}
	
    protected void run() {
        
    }     

    public void end() {

    }

    protected String getCommandName() {
        return "Wait Time";
    }

}
