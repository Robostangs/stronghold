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
public class Wait extends AutoCommandBase {

    public Wait(double timeOut) {
        super(timeOut);
    }

    @Override
    protected void run() {
        
    }     

    @Override
    public void end() {

    }

    @Override
    protected String getCommandName() {
        return "Wait";
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
