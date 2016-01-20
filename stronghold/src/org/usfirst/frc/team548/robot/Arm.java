package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Arm {

	private static Arm instance = null;
	private static Encoder armEncoder;
	
	public static Arm getInstance() {
		if(instance == null){
			instance = new Arm();
		}
		return instance;
	}
	
	private Arm() {
		armEncoder = new Encoder(Constants.ARM_ENCODER_POS_1, Constants.ARM_ENCODER_POS_2);
	}
	
	
}
