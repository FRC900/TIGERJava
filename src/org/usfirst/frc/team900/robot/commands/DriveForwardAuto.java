package org.usfirst.frc.team900.robot.commands;

import org.usfirst.frc.team900.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForwardAuto extends Command {
	double startTime, delay, duration, percentOutput;
	double curTime;
	
									//////////////////////////////////////////////////////////
									//		This autonomous has an empty constructor		//
	public DriveForwardAuto() { }	//	If you wanted to pass values into an autonomous		//
									//	Instead of just pulling important values from the	//
									//  smartDashboard or Constants class you can just		//
									//	modify this constructor to take in values like the	//
									// 	commented out example below. If you do this make	//
									//	sure not to forget to pass these values in when you	//
									//	instantiate the autonomous command					//
									//////////////////////////////////////////////////////////
/*
	public DriveForwardAuto(double some_value) {
		this.some_value = some_value;
	}
*/

	
	public void initialize() {		//This is called once when the command is chosen
		curTime = System.currentTimeMillis() / 1e3;		//get values for these times
		startTime = System.currentTimeMillis() / 1e3;
		
		delay = SmartDashboard.getNumber("Auto Drive Forward Delay", 0.0);		//Get important values from the smartDashboard
		duration = SmartDashboard.getNumber("Auto Drive Forward Time", 2.0);
		percentOutput = SmartDashboard.getNumber("Auto Drive Forward Speed", 0.0);
	}
	
	public void execute() {			//This is called over and over during autonomous
		curTime = System.currentTimeMillis() / 1e3;		//update the time
		if(curTime > (startTime + delay)) {				//only drive forward after the delay
			//Uses drive() instead of scaledDrive() to set the exact
			//percentOutput to the drivebase  without a deadzone or input scaling
			Robot.driveBase.drive(percentOutput, percentOutput); //input is from -1 to 1
		}
	}
	
	public boolean isFinished() {	//This is called over and over during autonomous to determine whether to end or not
		return !((curTime) < startTime + duration + delay);		//stop driving after duration of time
	}
	public void end() {				//This is called when isFinished() returns true
		Robot.driveBase.drive(0.0, 0.0); //Tell the robot to stop
	}
	
}
