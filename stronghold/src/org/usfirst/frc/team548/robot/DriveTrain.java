package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DriveTrain implements PIDSource {
	private static DriveTrain instance;
	private static Encoder encoderLeft, encoderRight;
	
	public static DriveTrain getInstance(){
		if(instance == null){
			instance = new DriveTrain();
		}
		
		return instance;
	}
	
	private DriveTrain(){
		encoderLeft = new Encoder(1, 1);
		encoderRight = new Encoder(1, 1);
	}
	
	public static double getEncoderAverage(){
		return ((encoderLeft.getRaw() + encoderRight.getRaw()) / 2);
	}
	
	public static void encoderReset(){
		encoderLeft.reset();
		encoderRight.reset();
	}
	
	public static void driveStraigt(){
		if(encoderLeft.getRaw() > encoderRight.getRaw()){
			drive
		}
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return 0;
	}
}
