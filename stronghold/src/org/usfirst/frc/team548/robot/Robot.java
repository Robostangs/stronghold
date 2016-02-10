
package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team548.robot.AutoModes.*;


public class Robot extends IterativeRobot {;
    TestAuto testAuto = new TestAuto("1");
    AutoMode autoSelected;
    SendableChooser chooser;
	PowerDistributionPanel pdp;

    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Auton 1", testAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        DriveTrain.getInstance();
        Arm.getInstance();
        Ingesting.getInstance();
        Shooter.getInstance();
        TeleOperated.getInstance();
        pdp = new PowerDistributionPanel();
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
		
    	DriveTrain.resetHyro();
    }


    public void autonomousPeriodic() {
    	
    }


    public void teleopPeriodic() {
    	TeleOperated.run();
    	//SmartDashboard.putNumber("Left Encoder", DriveTrain.getLeftEncoder());
    	//SmartDashboard.putNumber("Right Encoder", DriveTrain.getRightEncoder());
    	SmartDashboard.putNumber("Current 0", pdp.getCurrent(0));
    	SmartDashboard.putNumber("Current 1", pdp.getCurrent(1));
    	SmartDashboard.putNumber("Current 2", pdp.getCurrent(2));
    	SmartDashboard.putNumber("Current 3", pdp.getCurrent(3));
    	SmartDashboard.putNumber("Current 4", pdp.getCurrent(4));
    	SmartDashboard.putNumber("Current 5", pdp.getCurrent(5));
    	SmartDashboard.putNumber("Current 6", pdp.getCurrent(6));
    	SmartDashboard.putNumber("Current 7", pdp.getCurrent(7));
    	SmartDashboard.putNumber("Current 8", pdp.getCurrent(8));
    	SmartDashboard.putNumber("Current 9", pdp.getCurrent(9));
    	SmartDashboard.putNumber("Current 10", pdp.getCurrent(10));
    	SmartDashboard.putNumber("Current 11", pdp.getCurrent(11));
    	SmartDashboard.putNumber("Current 12", pdp.getCurrent(12));
    	SmartDashboard.putNumber("Current 13", pdp.getCurrent(13));
    	SmartDashboard.putNumber("Current 14", pdp.getCurrent(14));
    	SmartDashboard.putNumber("Current 15", pdp.getCurrent(15));
    	SmartDashboard.putNumber("ENCODER", Arm.getEncoder());
    	SmartDashboard.putNumber("Left Stick Y", TeleOperated.getStick());
    	
    }
    
    public void testInit() {
    	DriveTrain.resetPIDInit();
    	DriveTrain.setPIDtoGyro();
    }
    public void testPeriodic() {
    	System.out.println(DriveTrain.getHyroAngle());
    }
    
}
