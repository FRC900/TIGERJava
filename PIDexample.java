/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team900.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends IterativeRobot {
    private XboxController controller = new XboxController(0); //multiply by -1400
    int kSlotIdx = 0;
    float joy_multiplier = -1400;
    
    StringBuilder _sb = new StringBuilder();
    
    WPI_TalonSRX leftFront = new WPI_TalonSRX(10);
    WPI_TalonSRX leftBack = new WPI_TalonSRX(11);
    WPI_TalonSRX rightBack = new WPI_TalonSRX(21);
    WPI_TalonSRX rightFront = new WPI_TalonSRX(20);
    
    //make better
    double kF = 0.312; //100% output = 1023 throttle = 3280 nu/100ms; F = 1.00 / (3280/1023)
    double kP = 100; //10% to respond to max errors of around 1100 nu/100ms; P = 0.10 / (1100/1023) = .093
        
    public void robotInit() {
		leftFront.selectProfileSlot(kSlotIdx, 0);
    	leftFront.config_kF(kSlotIdx, kF, 10);    	
    	leftFront.config_kP(kSlotIdx, kP, 10);
    	leftFront.config_kI(kSlotIdx, 0, 10);
    	leftFront.config_kD(kSlotIdx, 0, 10);
    	leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	
    	leftBack.selectProfileSlot(kSlotIdx, 0);
    	leftBack.config_kF(kSlotIdx, kF, 10); //or is it just leftBack.setF(kF) ?
    	leftBack.config_kP(kSlotIdx, kP, 10);
    	leftBack.config_kI(kSlotIdx, 0, 10);
    	leftBack.config_kD(kSlotIdx, 0, 10);
    	leftBack.follow(leftFront);
    	
    	rightFront.selectProfileSlot(kSlotIdx, 0);
    	rightFront.config_kF(kSlotIdx, kF, 0);    	
    	rightFront.config_kP(kSlotIdx, kP, 0);
    	rightFront.config_kI(kSlotIdx, 0, 0);
    	rightFront.config_kD(kSlotIdx, 0, 0); //slot, value, timeoutMs
    	rightFront.setInverted(true);
    	
    	rightBack.selectProfileSlot(kSlotIdx, 0);
    	rightBack.config_kF(kSlotIdx, kF, 0);    	
    	rightBack.config_kP(kSlotIdx, kP, 0);
    	rightBack.config_kI(kSlotIdx, 0, 0);
    	rightBack.config_kD(kSlotIdx, 0, 0);
    	rightBack.setInverted(true);
    	rightBack.follow(rightFront);
    }

    public void teleopInit() {
        
    }
    
    @Override
    public void teleopPeriodic() {
         if (isOperatorControl() && isEnabled()) {
        	 //debug
        	 _sb.append("out:");
        	 _sb.append(leftFront.getMotorOutputPercent());
        	 _sb.append("\tspeed:");
        	 _sb.append(leftFront.getSelectedSensorVelocity(kSlotIdx));
        	 
        	 if(controller.getAButton()) {
        		 //drive normally
        		 leftFront.set(ControlMode.Velocity, joy_multiplier * controller.getY(GenericHID.Hand.kLeft)); 
        		 leftBack.set(ControlMode.Velocity, joy_multiplier * controller.getY(GenericHID.Hand.kLeft)); 
        		 rightFront.set(ControlMode.Velocity, joy_multiplier * controller.getY(GenericHID.Hand.kRight)); 
        		 rightBack.set(ControlMode.Velocity, joy_multiplier * controller.getY(GenericHID.Hand.kRight));
        		 
        		 _sb.append("\terr:");
        		 _sb.append(leftFront.getClosedLoopError(kSlotIdx));
        	 }
        	 else {
        		 //get pidf values
        		 leftFront.set(ControlMode.PercentOutput, controller.getY(GenericHID.Hand.kLeft)); 
        		 leftBack.set(ControlMode.PercentOutput, controller.getY(GenericHID.Hand.kLeft)); 
        		 rightFront.set(ControlMode.PercentOutput, controller.getY(GenericHID.Hand.kRight)); 
        		 rightBack.set(ControlMode.PercentOutput, controller.getY(GenericHID.Hand.kRight)); 
        	 }
        	 

        	 _sb.append("\n");
        	 System.out.println(_sb.toString());
         }
    }
    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
}
