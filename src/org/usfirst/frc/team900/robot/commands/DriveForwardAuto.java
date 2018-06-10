package org.usfirst.frc.team900.robot.commands;

import org.usfirst.frc.team900.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForwardAuto extends Command {
	double startTime, delay, duration, speed;
	double curTime;
	
	public DriveForwardAuto() { }
	
	public void initialize() {
		System.out.println("second init");
		curTime = System.currentTimeMillis() / 1e3;
		startTime = System.currentTimeMillis() / 1e3;
		delay = SmartDashboard.getNumber("Auto Drive Forward Delay", 0.0);
		duration = SmartDashboard.getNumber("Auto Drive Forward Time", 2.0);
		speed = SmartDashboard.getNumber("Auto Drive Forward Speed", 0.0);
	}
	
	public void execute() {
		curTime = System.currentTimeMillis() / 1e3;
		System.out.println("executing");
		if(curTime > (startTime + delay)) {
			Robot.driveBase.drive(speed, speed); //speed input is from -1 to 1
		}
	}
	
	public boolean isFinished() {
		return !((curTime) < startTime + duration + delay);
	}
	public void end() {
		System.out.println("end");
		Robot.driveBase.drive(0.0, 0.0); //speed input is from -1 to 1
	}
	
}
