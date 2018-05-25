package org.usfirst.frc.team900;

import org.usfirst.frc.team900.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.*;

/**
 * @author Duncan
 *
 */
public class Tankdrive {
	public TankdriveSide lSide, rSide;
	SpeedController left, right;
	XboxController controller = new XboxController(0);
	
	public Tankdrive() {
		lSide = new TankdriveSide(new WPI_TalonSRX(Constants.LB_DRIVE), new WPI_TalonSRX(Constants.LF_DRIVE), Constants.kF, Constants.kP, Constants.kI, Constants.kD, Constants.feedback_id);
		rSide = new TankdriveSide(new WPI_TalonSRX(Constants.RB_DRIVE), new WPI_TalonSRX(Constants.RF_DRIVE), Constants.kF, Constants.kP, Constants.kI, Constants.kD, Constants.feedback_id);
	}
	
	public void drive() {
		 lSide.set(Constants.joyMultiplier * controller.getY(GenericHID.Hand.kLeft)); 
		 rSide.set(Constants.joyMultiplier * controller.getY(GenericHID.Hand.kRight)); 
	}
	
	public void setPID() {
		lSide.setPID();
		rSide.setPID();
	}
}
