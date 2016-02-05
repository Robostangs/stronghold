/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.DriveTrain;

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
        turnToAngleInTime(5, 90);

        waitTime(2.5);
        turnToAngleInTime(5, 90);
        turnToAngleInTime(5, 180);
    }
    
}
