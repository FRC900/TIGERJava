package org.usfirst.frc.team900.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class Autonomous {
	private Tankdrive driveBase;
	
	public Autonomous(Tankdrive drive) {
		driveBase = drive;
	}
	
	public void init() {
	}
	
	public void run() {
		if(DriverStation.getInstance().getMatchTime() > (15 - Constants.autoTime)) {
			driveBase.drive(Constants.autoSpeed, Constants.autoSpeed); //from -1 to 1
		}
	}
	
	public void end() {
		driveBase.drive(0.0, 0.0);
	}
}
