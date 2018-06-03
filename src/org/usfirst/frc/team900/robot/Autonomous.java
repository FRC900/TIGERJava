package org.usfirst.frc.team900.robot;

import edu.wpi.first.wpilibj.DriverStation;

//the autonomous action is just a way to package all autonomous commands into single lines
//Testing git
public class Autonomous {
	public Tankdrive driveBase;
	
	//constructor -- add all subsystems that you want to move during auto
	public Autonomous(Tankdrive driveBase_) {
		driveBase = driveBase_;
	}
	
	public void init() {
	}
	
	//runs periodically throughout autonomous
	public void run() {
		if(DriverStation.getInstance().getMatchTime() > (15 - Constants.autoTime)) { //limit the amount of time that the robot drives
			driveBase.drive(Constants.autoSpeed, Constants.autoSpeed); //speed input is from -1 to 1
		}
	}
	
	//make sure that the robot stops driving at the end of autonomous
	public void end() {
		driveBase.drive(0.0, 0.0);
	}
}
