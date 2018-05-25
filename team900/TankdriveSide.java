package org.usfirst.frc.team900;

import org.usfirst.frc.team900.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class TankdriveSide {
	WPI_TalonSRX back, front;
	double kF, kP, kI, kD;
	double feedback_id;
	
	public TankdriveSide(WPI_TalonSRX front, WPI_TalonSRX back, double kF, double kP, double kI, double kD, int feedback_id) {
		this.back = back;
		this.front = front;
		this.kF = kF;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.feedback_id = feedback_id;
	}
	
	public void setPID() {
		front.selectProfileSlot(Constants.kSlotIdx, 0);
	    front.config_kF(Constants.kSlotIdx, kF, 10);    	
	    front.config_kP(Constants.kSlotIdx, kP, 10);
	    front.config_kI(Constants.kSlotIdx, kI, 10);
	    front.config_kD(Constants.kSlotIdx, kD, 10);
	    front.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	    back.follow(front);
	}
	
	void set(double joyValue) {
		front.set(ControlMode.Velocity, joyValue);
	}
}
