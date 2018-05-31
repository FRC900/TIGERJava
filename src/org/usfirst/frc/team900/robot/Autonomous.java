package org.usfirst.frc.team900.robot;

import org.usfirst.frc.team900.*;

import edu.wpi.first.wpilibj.DriverStation;

public class Autonomous {
	private Tankdrive driveBase;
	private DriverStation ds;
	
	public Autonomous(Tankdrive drive, DriverStation station) {
		driveBase = drive;
		ds = station;
	}
	
	public void init() {
	}
	
	public void run() {
		while(ds.getInstance().getMatchTime() > (15 - Constants.autoTime)) {
			driveBase.drive(0.0, Constants.autoSpeed); //from -1 to 1
		}
	}
	
	public void end() {
		driveBase.drive(0.0, 0.0);
	}
}
