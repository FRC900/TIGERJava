package org.usfirst.frc.team900;

import org.usfirst.frc.team900.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Duncan
 *
 */
public class Tankdrive {
	public TankdriveSide lSide, rSide;
	SpeedController left, right;
	XboxController controller = new XboxController(0);
	
	public Tankdrive() {
		lSide = new TankdriveSide(new WPI_TalonSRX(Constants.LF_DRIVE), new WPI_TalonSRX(Constants.LB_DRIVE), Constants.kF, Constants.kP, Constants.kI, Constants.kD, Constants.feedback_id);
		rSide = new TankdriveSide(new WPI_TalonSRX(Constants.RF_DRIVE), new WPI_TalonSRX(Constants.RB_DRIVE), Constants.kF, Constants.kP, Constants.kI, Constants.kD, Constants.feedback_id);
	}

	public void drive() {
		 lSide.set(controller.getY(GenericHID.Hand.kLeft)); 
		 rSide.set(controller.getY(GenericHID.Hand.kRight)); 
		 
		 //debug and tune PID
		 SmartDashboard.putNumber("In velocity", lSide.front.getMotorOutputPercent());
		 SmartDashboard.putNumber("Out velocity", lSide.front.getSelectedSensorVelocity(Constants.kSlotIdx));
		 SmartDashboard.putNumber("Err", lSide.front.getClosedLoopError(Constants.kSlotIdx));
		 
		 System.out.println(lSide.back.getSelectedSensorVelocity(Constants.kSlotIdx));
	}
	
	public void setPID() {
		lSide.setPID();
		rSide.setPID();
	}
}
