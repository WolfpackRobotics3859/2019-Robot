/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constants { 
    	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;
	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;
	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
    public static final int kTimeoutMs = 100;
	/**s
	 * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 7200 represents Velocity units at 100% output
     * 
	 * 	           
	 kP   kI   kD   kF          Iz    PeakOut */
    // public final static Gains l_kGains_Velocit = new Gains( 0, 0, 0, 1023.0/630,  300,  1.00);
	// public final static Gains r_kGains_Velocit = new Gains( 0, 0, 0, 1023.0/630,  300,  1.00);
	//Maximum Roller Speed for Cargo Intake
	
	
	
	//INTAKE CONSTANTS
		//This Position is in units
	public static final double intakeOutPosition = -1100;
	public static final double intakeShootPosition = -1500;
		//In Units/100ms
	public static final int intakeCruiseVelocity = 600;
		//In Units/100ms/s
	public static final int intakeMaxAccel = 600;
	public static final double defaultIntakeRollerSpeed = 0.5;
	public final static Gains intakeArmPID = new Gains(0.7, 0, 0, 1023/400, 300, 1);
	public final static int intakeSafeClear = -700;
	//SHOOTER CONSTANTS
	public static final double shooterMaxExtend = 1000;
	public static final double shooterShoot = 1700;
	public static final double defaultShooterSpeed = 1;
	public static final double defaultShooterIntakeSpeed = 0.5;
	public static final double shooterSafeZoneForIntakeToMove = 100;
	public final static Gains shooterArmPID = new Gains(0.1, 0, 0, 1023/500, 300, 1);
}
