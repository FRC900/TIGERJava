//this defines where the file is
package org.usfirst.frc.team900.robot; 

import org.usfirst.frc.team900.robot.commands.*;	//Gain access to classes that are in the separate package


//all of these import libraries that are used in this file 
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//now we create a class called Robot, which is a subclass of IterativeRobot. IterativeRobot is an FRC-defined construct.
public class Robot extends IterativeRobot {
	
////////////////////////	THIS CODE DOESN'T NEED TO BE CHANGED	//////////////////////////////////////
	//initialize the driver station																		//
	public static DriverStation ds; 																	//
																										//
	//Variable to receive the chosen autonomous command													//
	Command autoCommand;																				//
																										//
	//Autonomous chooser for the smart dashboard														//
	SendableChooser<Command> autoChooser;																//
																										//
	//initialize the joystick																			//
	public static XboxController controller;															//
																										//
	//initialize the drive base																			//
	public static Tankdrive driveBase;																	//	
//////////////////////////////////////////////////////////////////////////////////////////////////////////


		
	//////////////////////////////////////////////////////////////
	//			Define other subsystem variables here so		// 
	//			they can be accessed throughout the code		//	
	//															//
	//					DON'T INSTANTIATE THEM HERE!			//
	//															//
	//	just define a variable of the subsystem class type here	//
	//////////////////////////////////////////////////////////////

	

	
	
	//this function is run when the robot is initialized
	//put all code that needs to be run just once when the robot is just turned on here
	//Initialize subsystems and put values on the smartDashboard here
	public void robotInit() {
		driveBase = new Tankdrive(); 		//create the drive base (defines all of the motor controllers and related terms)
		driveBase.setup(); 					//setup followers, reverse the outputs of certain Talons, etc.
		
		//////////////////////////////////////////////
		//	Instantiate any other subsystems here 	//
		//  	such as a shooter or any other		//
		//			additional mechanisms			//
		//////////////////////////////////////////////
		
		controller = new XboxController(0); //create a controller object for receiving joystick inputs
		
		ds = DriverStation.getInstance(); /* this is how you access information from the Driver Station. See more at 
										   * https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599722-driver-station-input-overview
										   */
		
		//this puts values that determine autonomous onto the smartDashboard where they can be edited
		SmartDashboard.putNumber("Auto Drive Forward Speed", Constants.autoSpeed);	//percent of max speed to drive at during auto
		SmartDashboard.putNumber("Auto Drive Forward Time", Constants.autoTime);	//length of time to drive forward
		SmartDashboard.putNumber("Auto Drive Forward Delay", Constants.autoDelay);	//delay before driving forward
		
		//These lines create a menu on the smartDashboard for choosing an autonomous mode
		autoChooser = new SendableChooser<Command>();						//instantiate autoChooser
		autoChooser.addObject("Drive Forward", new DriveForwardAuto());		//Add drive forward auto
		autoChooser.addDefault("Do Nothing",  new DoNothingAuto());			//Add do nothing auto
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);		//put the chooser on the smartDashboard
	}

	//function is run when autonomous begins
	public void autonomousInit() {
		if (autoCommand != null) autoCommand.cancel();		//clear autoCommand
		autoCommand = (Command) autoChooser.getSelected();	//get the chosen command from the chooser
		autoCommand.start();								//run the chosen autonomous
	}
	
	//function is called over and over again during autonomous
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); 	//Leave this here it is important(it makes sure the auto command is running in the background)
	}
	
	//this function is called when autonomous ends
	public void teleopInit(){
		autoCommand.cancel(); 	//make the robot stop performing auto actions!
	}
	
	//this function is called over and over again during autonomous
	public void teleopPeriodic() {
		//tell the robot to drive with inputs from the controllers
		//Uses scaledDrive instead of drive to apply a deadzone and
		//to scale the joystick values for smoother driving
		driveBase.scaledDrive(-1*controller.getY(GenericHID.Hand.kLeft), -1*controller.getY(GenericHID.Hand.kRight)); 
	}
	
	//these three are probably not useful. ignore unless you are sure you want to put something in here
	public void disabledInit() {
	}
	
	public void disabledPeriodic(){
	}
	
	public void testPeriodic() {
	}

}
