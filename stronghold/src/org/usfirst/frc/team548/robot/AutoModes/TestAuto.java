/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team548.robot.AutoModes;

/**
 *
 * @author Alex
 */
public class TestAuto extends AutoMode {

    public TestAuto(String name) {
        super(name);
    }

    @Override
    protected void run() {
        waitTime(2.5);
        drivePowerInTime(2, 1);
        waitTime(2.5);
        drivePowerInTime(3, .5);
    }
    
}
