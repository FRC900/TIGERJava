/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

   /*
     * --- [1] Update CAN Device IDs and use WPI_VictorSPX where necessary ------
     */
    WPI_TalonSRX _rghtFront = new WPI_TalonSRX(Constants.kRightFront);
    WPI_TalonSRX _rghtFollower = new WPI_TalonSRX(Constants.kRightFollower);
    WPI_TalonSRX _leftFront = new WPI_TalonSRX(Constants.kLeftFront);
    WPI_TalonSRX _leftFollower = new WPI_TalonSRX(Constants.kLeftFollower);

    DifferentialDrive _diffDrive = new DifferentialDrive(_leftFront, _rghtFront);

    Faults _faults_L = new Faults();
    Faults _faults_R = new Faults();

  public Drivetrain()
  {
     /* factory default values */
     _rghtFront.configFactoryDefault();
     _rghtFollower.configFactoryDefault();
     _leftFront.configFactoryDefault();
     _leftFollower.configFactoryDefault();

     /* set up followers */
     _rghtFollower.follow(_rghtFront);
     _leftFollower.follow(_leftFront);

     /* [3] flip values so robot moves forward when stick-forward/LEDs-green */
     _rghtFront.setInverted(true); // !< Update this
     _leftFront.setInverted(false); // !< Update this

     /*
      * set the invert of the followers to match their respective master controllers
      */
     _rghtFollower.setInverted(InvertType.FollowMaster);
     _leftFollower.setInverted(InvertType.FollowMaster);

     /*
      * [4] adjust sensor phase so sensor moves positive when Talon LEDs are green
      */
     _rghtFront.setSensorPhase(true);
     _leftFront.setSensorPhase(true);

     /*
      * WPI drivetrain classes defaultly assume left and right are opposite. call
      * this so we can apply + to both sides when moving forward. DO NOT CHANGE
      */
     _diffDrive.setRightSideInverted(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void drive(double forw, double turn){

    String work;
    
    /* drive robot */
    _diffDrive.arcadeDrive(forw, turn);

    /*
     * [2] Make sure Gamepad Forward is positive for FORWARD, and GZ is positive for
     * RIGHT
     */
    work = " GF:" + forw + " GT:" + turn; 
  }
}
