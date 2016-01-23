
package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team548.robot.AutoModes.*;


public class Robot extends IterativeRobot {;
    TestAuto testAuto = new TestAuto("1");
    TestAuto2 testAuto2 = new TestAuto2();
    AutoMode autoSelected;
    SendableChooser chooser;
	

    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Auton 1", testAuto);
        chooser.addObject("Auton 2", testAuto2);
        SmartDashboard.putData("Auto choices", chooser);
        
        DriveTrain.getInstance();
        //Arm.getInstance();
        Autonomous.getInstance();
        //Ingesting.getInstance();
        //Shooter.getInstance();
        TeleOperated.getInstance();
    }
    
	/**
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (AutoMode) chooser.getSelected();
//		autoSelected = SmartDashboard.getStrixng("Auto Selector", defaultAuto);
    	System.out.println("Start Auton");
		autoSelected.start();
		
    }


    public void autonomousPeriodic() {
    	
    }


    public void teleopPeriodic() {
    	TeleOperated.run();
    }
    

    public void testPeriodic() {
    
    }
    
}
