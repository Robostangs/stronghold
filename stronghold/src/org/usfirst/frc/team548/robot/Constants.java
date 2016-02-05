package org.usfirst.frc.team548.robot;

public class Constants {
		
		public static final int XBOX_DRIVER_POS = 0;
		public static final int XBOX_MANIP_POS = 1;
		
		public static final int DT_TALON_POS_LEFT_FRONT = 1; //left encoder location
		public static final int DT_TALON_POS_LEFT_MID = 2;
		public static final int DT_TALON_POS_LEFT_BACK = 3;
		public static final int DT_TALON_POS_RIGHT_FRONT = 10;
		public static final int DT_TALON_POS_RIGHT_MID = 11;
		public static final int DT_TALON_POS_RIGHT_BACK = 12; //right encoder location
		
		public static final int GYRO_POS = 0;
		
		public static final int ARM_TALON_POS = 0;
		
		public static final int SHOOTING_TALON_POS = 0;
		public static final double SHOOTING_PID_P = 0, SHOOTING_PID_I = 0, SHOOTING_PID_D = 0;
		
		public static final int ING_TALON_POS = 0;
		public static final int ING_SWITCH_POS = 0;
		
		public static final double DT_ENCODER_ERROR_THRESHOLD = 0;
		public static final double DT_DRIVE_STRAIGHT_LOWER_POWER = 0;
		public static final double DT_DRIVE_STRAIGHT_HIGHER_POWER = 0;
		
		public static final int LEFT_ENCODER_POS_1 = 0;
		public static final int LEFT_ENCODER_POS_2 = 0;
		public static final int RIGHT_ENCODER_POS_1 = 0;
		public static final int RIGHT_ENCODER_POS_2 = 0;
		
		public static final int ARM_ENCODER_POS_1 = 0;
		public static final int ARM_ENCODER_POS_2 = 0;
		
		public static final int SHOOTER_ENCODER_POS_1 = 0;
		public static final int SHOOTER_ENCODER_POS_2 = 0;
		
		public static final double ROLLER_IN_NORMAL_POWER = 1;
		public static final double ROLLER_OUT_NORMAL_POWER = -1;
		
		public static final double BELTS_IN_NORMAL_POWER = 0.2;
		public static final double BELTS_OUT_NORMAL_POWER = -0.2;
		
		public static final double SHOOTER_INGEST_SPEED = 0.2;
		
		//PID constants
		public static final double DT_PID_GYRO_KP = 0.03, DT_PID_GYRO_KI = 0, DT_PID_GYRO_KD = 0;
		public static final double DT_PID_DRIVE_KP = 0, DT_PID_DRIVE_KI = 0, DT_PID_DRIVE_KD = 0;
}
