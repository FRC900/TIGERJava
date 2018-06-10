//this defines where the file is
package org.usfirst.frc.team900.robot; 

//all of these import libraries that are used in this file 

import org.usfirst.frc.team900.robot.commands.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//now we create a class called Robot, which is a subclass of IterativeRobot. IterativeRobot is a FRC-defined thing.
public class Robot extends IterativeRobot {
	public static Tankdrive driveBase; 			//initialize the drive base
	public static XboxController controller; 	//initialize the joystick
	
	public static DriverStation ds; 			//initialize the driver station

	Command autoCommand;						//Variable to receive the chosen autonomous command
	SendableChooser<Command> autoChooser;		//Autonomous chooser for the smart dashboard

	
	//this function is run when the robot is initialized
	public void robotInit() {
		driveBase = new Tankdrive(); 		//create the drive base (defines all of the motor controllers and related terms)
		driveBase.setup(); 					//setup followers, reverse the outputs of certain Talons, etc.
		
		controller = new XboxController(0);
		ds = DriverStation.getInstance(); /* this is how you access information from the Driver Station. See more at 
										   * https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599722-driver-station-input-overview
										   */
		
		//these two lines display information about the autonomous action on the SmartDashboard
		SmartDashboard.putNumber("Auto Drive Forward Speed", Constants.autoSpeed);
		SmartDashboard.putNumber("Auto Drive Forward Time", Constants.autoTime);
		SmartDashboard.putNumber("Auto Drive Forward Delay", Constants.autoDelay);
		
		//These lines create a menu on the smart dashboard for choosing an autonomous mode
		autoChooser = new SendableChooser<Command>();						//instantiate autoChooser
		autoChooser.addObject("Drive Forward", new DriveForwardAuto());		//Add drive forward auto
		autoChooser.addDefault("Do Nothing",  new DoNothingAuto());			//Add do nothing auto
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);		//put the chooser on the smart dashboard
	}

	//function is run when autonomous begins
	public void autonomousInit() {
		if (autoCommand != null) autoCommand.cancel();		//clear autoCommand
		//autoCommand = (Command) autoChooser.getSelected();	//get the chosen command from the chooser
		autoCommand = new DriveForwardAuto();
		autoCommand.start();								//run the chosen autonomous
		System.out.println("auto inint");
	}
	
	//function is called over and over again during autonomous
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); 	//what does this actually do? no one knows...
	}
	
	//this function is called when autonomous ends
	public void teleopInit(){
		autoCommand.cancel(); 	//make the robot stop performing auto actions!
	}
	
	//this function is called over and over again during autonomous
	public void teleopPeriodic() {
		driveBase.drive(-1*controller.getY(GenericHID.Hand.kLeft), -1*controller.getY(GenericHID.Hand.kRight)); //tell the robot to drive with inputs from the controllers
	}
	
	//these three are probably not useful. ignore for now.
	public void disabledInit() {
	}
	
	public void disabledPeriodic(){
	}
	
	public void testPeriodic() {
	}

}
