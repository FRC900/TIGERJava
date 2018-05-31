package org.usfirst.frc.team900.robot;

import org.usfirst.frc.team900.*;

import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;

//import org.usfirst.frc.team900.robot.commands.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static Tankdrive driveBase;
	public static XboxController controller;
	
	public static Autonomous autoAction;
	public static DriverStation ds;
	/*public static Robot robot;
	public static Joystick joy1, joy2;
	public static Swerve swerve;
	public static Gyro gyro;
	public static Shooter shooter;
	//	public static DOF9 fancygyro;
	//	static ADC adc;

	Command autoCommand;
	SendableChooser<Command> autoChooser;
	double test;*/

	public void robotInit() {
		driveBase = new Tankdrive();
		driveBase.setPID();
		
		controller = new XboxController(0);
		autoAction = new Autonomous(driveBase, ds);
		ds = DriverStation.getInstance();
		
		SmartDashboard.putNumber("Drive Forward Speed", Constants.autoSpeed);
		SmartDashboard.putNumber("Drive Forward Time", Constants.autoTime);
	    /*SmartDashboard.putNumber("Speed", .5);
	    SmartDashboard.putNumber("x", -15);
	    SmartDashboard.putNumber("y", 75);
	    SmartDashboard.putNumber("driveSpeed", .5);
        SmartDashboard.putNumber("angle", 60);
        SmartDashboard.putNumber("duration", 3);
		test = 0;
		robot = this;
		System.out.println("robot init");
		joy1 = new Joystick(0);
		joy2 = new Joystick(1);
		gyro = new Gyro();
		//		fancygyro = new DOF9(edu.wpi.first.wpilibj.I2C.Port.kOnboard,0,-1);
		swerve = new Swerve(joy1, gyro);
		winch = new Winch(joy2);
		gear = new GearIntake(joy2);
		shooter = new Shooter(joy2);
		autoChooser = new SendableChooser<Command>();
		autoChooser.addObject("Middle Gear", new MiddleGearAuto());
		autoChooser.addDefault("fortyKPAuto",  new fortyKPAuto());
		autoChooser.addObject("Boiler Gear", new BoilerSideAuto());
		autoChooser.addObject("Gear Reset", new GearReset());
		autoChooser.addObject("SideGearAutoRight", new SideGearAutoRight());
		//autoChooser.addObject("Shooty", new Shoot(15));
		//		adc=new ADC(edu.wpi.first.wpilibj.I2C.Port.kMXP);
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);*/
	}


	public void autonomousInit() {
		autoAction.init();
		autoAction.run();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopInit(){
		autoAction.end();
	}
	public void teleopPeriodic() {
		driveBase.drive(controller.getY(GenericHID.Hand.kLeft), controller.getY(GenericHID.Hand.kRight));
		/*swerve.move();
		shooter.shoot();
		gear.gear();
		winch.winch();
		SmartDashboard.putNumber("Gyro", gyro.getAngle());*/
	}
	public void disabledInit() {
		//if (autoCommand != null) autoCommand.cancel();
	}
	public void disabledPeriodic(){
		//swerve.smartDash();
	}
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
