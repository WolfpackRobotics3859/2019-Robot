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
	public static final int kDriveVoltageRampRate = 0;
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
	public static final int intake_OutPosition = -850;
	public static final int intake_SweepPosition = -1150;
	public static final int intake_StowPosition = 0;
		
	public static final int intakeCruiseVelocity = 600; //In Units/100ms
	public static final int intakeMaxAccel = 600; //In Units/100ms/s
	public final static Gains intakeArmPID = new Gains(0.7, 0, 0, 1023/450, 300, 1);
	//SHOOTER CONSTANTS
	public static final int shooter_RearShot = 2250;
	public static final int shooter_ForwardShot = 1385;
	public static final int shooter_StowPosition = -50;

	public static final int shooterCruiseVelocity = 600;
	public static final int shooterMaxAccel = 600;
	public final static Gains shooterArmPID = new Gains(0.5, 0, 0.1, 1023/300, 300, 1);

<<<<<<< HEAD
	
	//WHEELS CONSTANTS
	public static final double wheels_SHOT_intaking = -0.75;
	public static final double wheels_SHOT_shootingF = 0.45;
	public static final double wheels_SHOT_shootingR = 0.6;
=======
	//WHEELS CONSTANTS
	public static final double wheels_SHOT_intaking = -0.75;
	public static final double wheels_SHOT_shootingF = 0.4;
	public static final double wheels_SHOT_shootingR = 0.4;
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
	public static final double wheels_SHOT_holding = -0.15;

	public static final double wheels_INTA_intaking = -0.8;

	//OVERWATCH CONSTANTS
	public static final double overwatch_intake_IsClear = -600;
	public static final double overwatch_shooter_isStowed = 100;

	//HATCH CONSTANTS
	public static final double hatch_travelSpeed = 0.5;
}
