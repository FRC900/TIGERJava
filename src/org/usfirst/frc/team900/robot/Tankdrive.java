package org.usfirst.frc.team900.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Duncan
 *
 */
public class Tankdrive extends Subsystem {
	WPI_TalonSRX backRight, frontRight, backLeft, frontLeft;
	double kF, kP, kI, kD;
	double feedback_id;
	//public TankdriveSide lSide, rSide;
	//SpeedController left, right;
	
	public Tankdrive() {
		this.backRight = new WPI_TalonSRX(Constants.BR_DRIVE);
		this.frontRight = new WPI_TalonSRX(Constants.FR_DRIVE);
		this.backLeft = new WPI_TalonSRX(Constants.BL_DRIVE);
		this.frontLeft = new WPI_TalonSRX(Constants.FL_DRIVE);
		
	    backRight.follow(frontRight);	
	    backLeft.follow(frontLeft);	
	    
		this.frontRight.setInverted(true);
		this.backRight.setInverted(true);
		this.frontLeft.setInverted(false);
		this.backLeft.setInverted(false);

		this.kF = Constants.kF;
		this.kP = Constants.kP;
		this.kI = Constants.kI;
		this.kD = Constants.kD;
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public double deadzone(double value) {
		if(Math.abs(value) < 0.2)
			value = 0;
		value = Math.pow(value, 3);
		System.out.println(value);
		return value;
	}

	public void drive(double lValue, double rValue) { //lValue and rValue are -1 to 1
		 frontLeft.set(ControlMode.Velocity, deadzone(lValue) * Constants.maxSpeed);
		 frontRight.set(ControlMode.Velocity, deadzone(rValue) * Constants.maxSpeed);
		 
		 //debug and tune PID
		 SmartDashboard.putNumber("In velocity", frontLeft.getMotorOutputPercent());
		 SmartDashboard.putNumber("Out velocity", frontLeft.getSelectedSensorVelocity(Constants.kSlotIdx));
		 SmartDashboard.putNumber("Err", frontLeft.getClosedLoopError(Constants.kSlotIdx));
		 
		 System.out.println(backLeft.getSelectedSensorVelocity(Constants.kSlotIdx));
	}
	
	public void setup() {
		frontRight.selectProfileSlot(Constants.kSlotIdx, 0);
	    frontRight.config_kF(Constants.kSlotIdx, kF, 10);    	
	    frontRight.config_kP(Constants.kSlotIdx, kP, 10);
	    frontRight.config_kI(Constants.kSlotIdx, kI, 10);
	    frontRight.config_kD(Constants.kSlotIdx, kD, 10);
	    frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kSlotIdx, 0);
	    frontRight.setSensorPhase(true);
	    
	    frontLeft.selectProfileSlot(Constants.kSlotIdx, 0);
	    frontLeft.config_kF(Constants.kSlotIdx, kF, 10);    	
	    frontLeft.config_kP(Constants.kSlotIdx, kP, 10);
	    frontLeft.config_kI(Constants.kSlotIdx, kI, 10);
	    frontLeft.config_kD(Constants.kSlotIdx, kD, 10);
	    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kSlotIdx, 0);
	    frontLeft.setSensorPhase(true);
	}


}
