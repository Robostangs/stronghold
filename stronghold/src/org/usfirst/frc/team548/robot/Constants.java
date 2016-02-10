package org.usfirst.frc.team548.robot;

public class Constants {
		
	//Xbox Controllers
		public static final int XBOX_DRIVER_POS = 0;
		public static final int XBOX_MANIP_POS = 1;
		
	//Drivetrain motors
		public static final int DT_TALON_POS_LEFT_FRONT = 1; //left encoder location
		public static final int DT_TALON_POS_LEFT_MID = 2;
		public static final int DT_TALON_POS_LEFT_BACK = 3;
		public static final int DT_TALON_POS_RIGHT_FRONT = 10;
		public static final int DT_TALON_POS_RIGHT_MID = 11;
		public static final int DT_TALON_POS_RIGHT_BACK = 12; //right encoder location		
		public static final double DT_ENCODER_ERROR_THRESHOLD = 0;
		public static final double DT_DRIVE_STRAIGHT_LOWER_POWER = 0;
		public static final double DT_DRIVE_STRAIGHT_HIGHER_POWER = 0;
		
	//Gyro
		public static final int GYRO_POS = 0;
		
	//Arm
		public static final int LEFT_ARM_TALON_POS = 4;
		public static final int RIGHT_ARM_TALON_POS = 9;
		public static final double ARM_POWER_COEFFICIENT = 0.75;
		public static final double ARM_LOW_POWER = 0.05;
		public static final int ARM_MAX_POS = 660;
		public static final int ARM_MAX_THRESHOLD = 700;
		public static final int ARM_MIN_POS = 910;
		public static final int ARM_MIN_THRESHOLD = 900;
		public static final int ARM_LOW_POS = 910;
		public static final int ARM_INGEST_POS = 905;
		public static final int ARM_DEFENSE_POS = 830;
		
		//Lowest position: 917
		//Highest position: 635 (be careful to stop early to prevent falling over)
		
	//Shooter
		public static final int SHOOTING_TALON_POS = 5;
		public static final double SHOOTING_PID_P = 0, SHOOTING_PID_I = 0, SHOOTING_PID_D = 0;
		public static final double SHOOTER_INGEST_SPEED = 0.6;
		public static final double SHOOTER_EXGEST_SPEED = -0.5;
		public static final double SHOOTER_SPEED_NO_PID = -1;
		
	//Ingesting
		public static final int INGESTING_TALON_POS = 8;
		public static final int INGESTING_SWITCH_POS = 0;
		public static final double INGESTING_IN_NORMAL_POWER = 1;
		public static final double INGESTING_OUT_NORMAL_POWER = -0.5;
		public static final double INGESTING_HOLDING_BALL_POWER = 0.05;
		public static final double INJECTING_POWER = -0.6;
		
	//PID constants
		public static final double DT_PID_GYRO_KP = 0.03, DT_PID_GYRO_KI = 0, DT_PID_GYRO_KD = 0;
		public static final double DT_PID_DRIVE_KP = 0, DT_PID_DRIVE_KI = 0, DT_PID_DRIVE_KD = 0;
}
