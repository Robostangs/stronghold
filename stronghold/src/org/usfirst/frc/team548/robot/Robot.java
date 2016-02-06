
package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.IOException;

import org.usfirst.frc.team548.robot.AutoModes.*;


public class Robot extends IterativeRobot {;
    TestAuto testAuto = new TestAuto("1");
    AutoMode autoSelected;
    SendableChooser chooser;
	
    @Override
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Auton 1", testAuto);
        SmartDashboard.putData("Auto choices", chooser);
        Logger.getInstance();
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
    @Override
    public void autonomousInit() {
    	autoSelected = (AutoMode) chooser.getSelected();
//		autoSelected = SmartDashboard.getStrixng("Auto Selector", defaultAuto);
    	System.out.println("Start Auton");
		autoSelected.start();
    	DriveTrain.resetHyro();
    }

    @Override
    public void autonomousPeriodic() {
    	
    }

    @Override
    public void teleopPeriodic() {
    	TeleOperated.run();
    	SmartDashboard.putNumber("Left Encoder", DriveTrain.getLeftEncoder());
    	SmartDashboard.putNumber("Right Encoder", DriveTrain.getRightEncoder());
    	
    }
    
    @Override
    public void testInit() {
    	DriveTrain.resetPIDInit();
    	DriveTrain.setPIDtoGyro();
    }
    
    @Override
    public void testPeriodic() {
    	System.out.println(DriveTrain.getHyroAngle());
    }
    
    @Override
    public void disabledInit(){
    	try {
			Logger.getInstance().closeWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
