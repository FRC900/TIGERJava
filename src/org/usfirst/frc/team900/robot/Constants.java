package org.usfirst.frc.team900.robot;

//this file contains all of the constant values that are used throughout the code.
//Any value in here can be accessed from the rest of the code with <Constants."variable name">
//															Example: Constants.FL_DRIVE;
public class Constants {
	//Defines the IDs for creating talon objects
	public final static int FL_DRIVE = 10;	///////////////////////////////////////////////////////////
	public final static int BL_DRIVE = 11;	//These IDs may not be correct for your robot			 //
											//To find the correct ID open internet explorer and go to//
	public final static int FR_DRIVE = 20;	//roboRIO-####-FRC.local where #### is your team number	 //
	public final static int BR_DRIVE = 21;	//once there you can find the IDs for all of your talons //
											//and you can make individual talons blink to figure out //
											//which talon is which									 //
											///////////////////////////////////////////////////////////
										
	
	//Reverse the output of the left and/or right side
	//Make both sides drive in the correct direction
	public final static boolean leftReversed = false;
	public final static boolean rightReversed = true;
	
	//Default values for autonomous
	public final static double autoSpeed = 0.75;	//Percentage of max speed
	public final static double autoTime = 5;		//Length of time to drive forward
	public final static double autoDelay = 1;		//Delay before driving forward

	
	//PID things
	public final static int kSlotIdx = 0;		///////////////////////////////////////////////////////////
	public final static double kF = 0.327;		//				THESE ARE JUST FOR OUR ROBOT			 //
	public final static double kP = 0.35;		//							REMOVE						 //
	public final static double kI = 0.0;		//														 //
	public final static double kD = 3.5;		//														 //
												//														 //
	public final static int maxSpeed = 3125;//for driving with Velocity ControlMode						 //
												///////////////////////////////////////////////////////////
}
