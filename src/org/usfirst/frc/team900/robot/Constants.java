package org.usfirst.frc.team900.robot;

//this file contains all of the constant values that are used throughout the code.
//Any value in here can be accessed from the rest of the code with <Constants.FL_DRIVE>
public class Constants {
	//for defining Talon IDs
	public final static int FL_DRIVE = 10;
	public final static int BL_DRIVE = 11;

	public final static int FR_DRIVE = 20;
	public final static int BR_DRIVE = 21;
	
	//make values not reversed
	public final static boolean leftReversed = false;
	public final static boolean rightReversed = true;
	
	//for autonomous
	public final static double autoSpeed = 0.75;
	public final static double autoTime = 5;
	public final static double autoDelay = 1;

	
	//PID things
	public final static int kSlotIdx = 0;
	public final static double kF = 0.327;
	public final static double kP = 0.35;
	public final static double kI = 0.0;
	public final static double kD = 3.5;
	
	//for driving with Velocity ControlMode
	public final static int maxSpeed = 3125;
}
