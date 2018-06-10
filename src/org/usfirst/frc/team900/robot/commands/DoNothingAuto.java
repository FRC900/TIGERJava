package org.usfirst.frc.team900.robot.commands;

import org.usfirst.frc.team900.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DoNothingAuto extends Command{
	
	public DoNothingAuto() { }		//Empty constructor (no important values to pass in)
	public void initialize() { }	//Nothing in initialize because there is nothing to initialize 
	public void execute() {	}		//Nothing in execute because we don't want to do anything
	
	public boolean isFinished() {	//Return true to finish immediately
		return true;
	}
	public void end() {	}			//Nothing in end because no commands were ever set
}
