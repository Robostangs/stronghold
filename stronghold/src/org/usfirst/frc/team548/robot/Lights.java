package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Lights {
	private static SerialPort p;
	private static DriverStation d;
	
	private Lights() {
		p = new SerialPort(9600, Port.kOnboard);
		d = DriverStation.getInstance();
	}
	
	public static void sendData() {
		getAllianceNumber();
		getLeftDT();
		getRightDT();
		getShooterStuff();
		getSalerStuff();
		getCrap();
		getState();
		getFMS();
	}
	
	private static byte getAllianceNumber() {
		if(d.getAlliance() == Alliance.Blue) {
			return -1;
		} else if(d.getAlliance() == Alliance.Red) {
			return 1;
		} else {
			return 0;
		}
	}
	
	private static byte getLeftDT() {
		if(DriveTrain.getLeftSetForLights() > .15) {
			return 1;
		} else if(DriveTrain.getLeftSetForLights() < -.15) {
			return -1;
		} else {
			return 0;
		}
	}
	
	private static byte getRightDT() {
		if(DriveTrain.getRightSetForLights() > .15) {
			return 1;
		} else if(DriveTrain.getRightSetForLights() < -.15) {
			return -1;
		} else {
			return 0;
		}
	}
	
	private static byte getShooterStuff() {
		if(Shooter.getShooterEncoderVelocity() > Constants.MAX_SHOT_SPEED) {
			return 2;
		} else if(Shooter.getShooterEncoderVelocity() < Constants.MAX_SHOT_SPEED && Shooter.getShooterEncoderVelocity() > 0) {
			return 1;
		} else if(Shooter.getShooterEncoderVelocity() == 0) {
			return 0;
		} else {
			return -1;
		}
	}
	
	private static byte getSalerStuff() {
		if(Scaling.getScallingSetForLights() > 0) {
			return 1;
		} else if(Scaling.getScallingSetForLights() < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public static byte getCrap() {
		if(TeleOperated.getLightButtonThing()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static byte getState() {
		if(d.isDisabled()) {
			return 0;
		} else if(d.isAutonomous()) {
			return 1;
		} else if(d.isOperatorControl()) {
			return 2;
		} else {
			return 0;
		}
	}
	
	public static byte getFMS() {
		if(d.isFMSAttached()) {
			return 1;
		} else {
			return 0;
		}
	}
}