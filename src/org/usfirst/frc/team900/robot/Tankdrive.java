package org.usfirst.frc.team900.robot;

import com.ctre.phoenix.motorcontrol.ControlMode; //to configure driving in PercentOutput vs Velocity PID
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//creates a subsystem that controls robot driving
public class Tankdrive extends Subsystem {
	WPI_TalonSRX backRight, frontRight, backLeft, frontLeft; //define all of the motor controllers within a drive base
	double kF, kP, kI, kD; //PID values
	
	public Tankdrive() {
		//create all of the Talons
		this.backRight = new WPI_TalonSRX(Constants.BR_DRIVE);
		this.frontRight = new WPI_TalonSRX(Constants.FR_DRIVE);
		this.backLeft = new WPI_TalonSRX(Constants.BL_DRIVE);
		this.frontLeft = new WPI_TalonSRX(Constants.FL_DRIVE);
		
		//assign masters and followers
	    backRight.follow(frontRight);	//Make the back right talon follow the front right
	    backLeft.follow(frontLeft);		//Make the back left talon follow the front left
	    
	    //make the left and right drive in the same direction
		this.frontRight.setInverted(Constants.rightReversed);
		this.backRight.setInverted(Constants.rightReversed);
		this.frontLeft.setInverted(Constants.leftReversed);
		this.backLeft.setInverted(Constants.leftReversed);

		//set the PID values			//REMOVE THIS CODE
		this.kF = Constants.kF;			//THIS IS JUST FOR US
		this.kP = Constants.kP;
		this.kI = Constants.kI;
		this.kD = Constants.kD;
	}
	
	//default command -- it's fine to leave blank, or set to 0.0
	@Override
	protected void initDefaultCommand() {
	}
	
	//for scaling and deadzoning joysticks, to improve driving quality 
	public double deadzone(double value) { 		//value input is from -1 to 1
		if(Math.abs(value) < 0.2) 				//very small joystick inputs should be ignored
			value = 0;
		value = Math.pow(value, 3); 			//cube the input for more sensitivity and robot control
		return value;
	}

	//Used to deadzone and scale joystick inputs before setting the input to the talons
	public void scaledDrive(double lValue, double rValue) {
		this.drive(deadzone(lValue), deadzone(rValue));
	}
	
	//Set the lValue and rValue directly to the talons
	//called by scaledDrive and during autonomous to ignore the scaling and deadzone
	public void drive(double lValue, double rValue) { //lValue and rValue are -1 to 1
		//if using PercentOutput
		 frontLeft.set(ControlMode.PercentOutput, lValue);
		 frontRight.set(ControlMode.PercentOutput, rValue);
		
		//if using Velocity PID, uncomment
		/*frontLeft.set(ControlMode.Velocity, deadzone(lValue) * Constants.maxSpeed);
		  frontRight.set(ControlMode.Velocity, deadzone(rValue) * Constants.maxSpeed);*/
		 
		 //debug and tune PID
		 SmartDashboard.putNumber("In velocity", frontLeft.getMotorOutputPercent());
		 SmartDashboard.putNumber("Out velocity", frontLeft.getSelectedSensorVelocity(Constants.kSlotIdx));
		 SmartDashboard.putNumber("Err", frontLeft.getClosedLoopError(Constants.kSlotIdx));
	}
	
	//config PID fix reversed encoders
	public void setup() {
		frontRight.selectProfileSlot(Constants.kSlotIdx, 0);
	    frontRight.config_kF(Constants.kSlotIdx, kF, 10);    	
	    frontRight.config_kP(Constants.kSlotIdx, kP, 10);
	    frontRight.config_kI(Constants.kSlotIdx, kI, 10);
	    frontRight.config_kD(Constants.kSlotIdx, kD, 10);
	    frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kSlotIdx, 0); //which encoder does it assign to this talon?
	    frontRight.setSensorPhase(true); //the encoder is reversed -- this unreverses it
	    
	    frontLeft.selectProfileSlot(Constants.kSlotIdx, 0);
	    frontLeft.config_kF(Constants.kSlotIdx, kF, 10);    	
	    frontLeft.config_kP(Constants.kSlotIdx, kP, 10);
	    frontLeft.config_kI(Constants.kSlotIdx, kI, 10);
	    frontLeft.config_kD(Constants.kSlotIdx, kD, 10);
	    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kSlotIdx, 0); //which encoder does it assign to this talon?
	    frontLeft.setSensorPhase(true); //the encoder is reversed -- this unreverses it
	}
}
