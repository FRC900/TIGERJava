package org.usfirst.frc.team900.robot;


import org.usfirst.frc.team900.robot.Constants;
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
		lSide = new TankdriveSide(new WPI_TalonSRX(constants.LB_DRIVE), new WPI_TalonSRX(constants.LF_DRIVE), constants.L_Kf, constants
				
									)
	}
}
