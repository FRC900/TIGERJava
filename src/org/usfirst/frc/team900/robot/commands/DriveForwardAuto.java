package org.usfirst.frc.team900.robot.commands;

import org.usfirst.frc.team900.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForwardAuto extends Command {
	
	public void execute() {
		if(timeSinceInitialized() > SmartDashboard.getNumber("Auto Drive Forward Delay", 0.0)) {
			Robot.driveBase.drive(SmartDashboard.getNumber("Auto Drive Forward Speed", 0.0), SmartDashboard.getNumber("Auto Drive Forward Speed", 0.0)); //speed input is from -1 to 1
		}
	}
	
	public boolean isFinished() {
		return ((timeSinceInitialized() + SmartDashboard.getNumber("Auto Drive Forward Delay", 0.0)) < SmartDashboard.getNumber("Auto Drive Forward Time", 2.0));
	}
	public void end() {
		Robot.driveBase.drive(0.0, 0.0); //speed input is from -1 to 1
	}
	
}
