
package org.usfirst.frc.team900.robot;

import java.util.List;

//import org.usfirst.frc.team900.robot.commands.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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
	public static Robot robot;
	public static Joystick joy1, joy2;
	public static Swerve swerve;
	public static Gyro gyro;
	public static Shooter shooter;
	//	public static DOF9 fancygyro;
	//	static ADC adc;

	Command autoCommand;
	SendableChooser<Command> autoChooser;
	double test;

	public void robotInit() {
	    SmartDashboard.putNumber("Speed", .5);
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
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
	}


	public void autonomousInit() {
		gyro.reset();
		gear.encoder.reset();
		Robot.gear.liftPID.setSetpoint(0);
		if (autoCommand != null) autoCommand.cancel();
		autoCommand = (Command) autoChooser.getSelected();
		autoCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("gear encoder", gear.encoder.get());
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopInit(){
		if (autoCommand != null) autoCommand.cancel();
		gyro.reset();   
		swerve.setPivot(0, 0);
		shooter.init();
	}
	public void teleopPeriodic() {
		swerve.move();
		shooter.shoot();
		gear.gear();
		winch.winch();
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
	}
	public void disabledInit() {
		if (autoCommand != null) autoCommand.cancel();
	}
	public void disabledPeriodic(){
		swerve.smartDash();
	}
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
