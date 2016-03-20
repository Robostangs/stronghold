package org.usfirst.frc.team548.robot;

public class Constants {
		
	//Xbox Controllers
		public static final int XBOX_DRIVER_POS = 0;
		public static final int XBOX_MANIP_POS = 1;
		
	//Drivetrain motors
		public static final int DT_TALON_POS_LEFT_FRONT = 1;//left encoder location
		public static final int DT_TALON_POS_LEFT_MID = 2;
		public static final int DT_TALON_POS_LEFT_BACK = 3;
		public static final int DT_TALON_POS_RIGHT_FRONT = 10;
		public static final int DT_TALON_POS_RIGHT_MID = 11;
		public static final int DT_TALON_POS_RIGHT_BACK = 12;//right encoder location		
		public static final double DT_ENCODER_ERROR_THRESHOLD = 0;
		public static final double DT_HYRO_ERROR_THRESHOLD = 1;
		public static final double DT_DRIVE_STRAIGHT_LOWER_POWER_RIGHT = .85;
		public static final double DT_DRIVE_STRAIGHT_HIGHER_POWER_RIGHT = 1.25;
		public static final double DT_DRIVE_STRAIGHT_LOWER_POWER_LEFT = .80;
		public static final double DT_DRIVE_STRAIGHT_HIGHER_POWER_LEFT = 1.10;
		public static final double DRIVE_DISTANCE_LOW_POWER = 0.2;
		
	//Gyro
		public static final int GYRO_POS = 0;
		
	//Arm
		public static final double ARM_ENOCDER_OFFSET = -.08;
		public static final int LEFT_ARM_TALON_POS = 4;
		public static final int RIGHT_ARM_TALON_POS = 7;
		public static final double ARM_POWER_COEFFICIENT = -0.75;
		public static final double ARM_LOW_POS = 0.555; //change this one
		public static final double ARM_ING_POS = ARM_LOW_POS - 0.014; //don't change
		public static final double ARM_DEF_POS = ARM_LOW_POS - .116; //don't change
		public static final double ARM_SHOOT_POS = ARM_LOW_POS - .441;//don't change
		public static final double ARM_AUTO_SHOOT_POS = ARM_LOW_POS - 0.344;//don't change
		public static final double ARM_LOW_P = 4;
		public static final double ARM_LOW_I = 0.006;
		public static final double ARM_LOW_D = 5;
 		public static final double ARM_ING_P = 2.5;
		public static final double ARM_ING_I = 0.002;
		public static final double ARM_ING_D = 5;
		public static final double ARM_DEF_P = 2.5;
		public static final double ARM_DEF_I = 0.002;
		public static final double ARM_DEF_D = 5;
		public static final double ARM_SHOOT_P = 2.5;
		public static final double ARM_SHOOT_I = 0.001;
		public static final double ARM_SHOOT_D = 0;
		
		public static final double ARM_AUTO_SHOOT_P = 7;
		public static final double ARM_AUTO_SHOOT_I = 0;
		public static final double ARM_AUTO_SHOOT_D = 1;
		public static final double ARM_SHOOT_ADJUST_P = 10;
		public static final double ARM_SHOOT_ADJUST_I = 0;
		public static final double ARM_SHOOT_ADJUST_D = 1;
		public static final double POSITIVE_ARM_ADJUSTMENT = 0.005;
		public static final double NEGATIVE_ARM_ADJUSTMENT = -0.005;
		
		public static enum ARM_POS {
			LOW (ARM_LOW_POS),
			ING (ARM_ING_POS),
			DEF (ARM_DEF_POS),
			SHOOT (ARM_SHOOT_POS),
			AUTO_SHOOT (ARM_AUTO_SHOOT_POS);
			private final double setpoint;
			ARM_POS(double value) {
				this.setpoint = value;
			}
			
			public double getSetpoint() { return setpoint; }
		}
	
		
		//Lowest position: 917
		//Highest position: 635 (be careful to stop early to prevent falling over)
		
	//Shooter
		public static final int SHOOTING_TALON_POS_1 = 5;
		public static final int SHOOTING_TALON_POS_2 = 9;
		public static final double SHOOTING_PID_P = 0, SHOOTING_PID_I = 0, SHOOTING_PID_D = 0;
		public static final double SHOOTER_INGEST_SPEED = -1;
		public static final double SHOOTER_EXGEST_SPEED = 1;
		public static final double SHOOTER_SPEED_NO_PID = 1;
		public static final double SHOOTER_P = 0.0000004;
		public static final double SHOOTER_I = 0.000000000006;
		public static final double SHOOTER_D = 0;
		public static final int SHOOTER_ENC_POS_1 = 0;
		public static final int SHOOTER_ENC_POS_2 = 1;
		public static final int MAX_SHOT_SPEED = 4800;
		
	//Ingesting
		public static final int INGESTING_TALON_POS = 8;
		public static final int INGESTING_SWITCH_POS = 0;
		public static final double INGESTING_IN_NORMAL_POWER = 1;
		public static final double INGESTING_OUT_NORMAL_POWER = -0.5;
		public static final double INGESTING_HOLDING_BALL_POWER = 0.05;
		public static final double INJECTING_POWER = -1;
		
		//Scaling
		public static final int SCALING_TALON_POS = 6;
		public static final int SCALING_SWITCH_POS = 0;
		public static final int DESCALING_SWITCH_POS = 1;
		public static final int SCALING_SERVO_POS = 0;
		public static final double SCALING_SPEED = 0.3;
		public static final double DESCALING_SPEED = -0.3;
		
	//PID constants
		public static final double DT_PID_GYRO_KP = 0.04, DT_PID_GYRO_KI = 0.000, DT_PID_GYRO_KD = 0; //p 0.03, i 0.002
		public static final double DT_PID_SMALL_GYRO_KP = 0.1, DT_PID_SMALL_GYRO_KI = 0.005, DT_PID_SMALL_GYRO_KD = 0;
		public static final double DT_PID_DRIVE_KP = 0, DT_PID_DRIVE_KI = 0, DT_PID_DRIVE_KD = 0;
		
}
