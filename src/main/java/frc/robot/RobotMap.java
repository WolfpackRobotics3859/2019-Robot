/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
  //Left Drive Talon and Victors
  public static int drive_leftMasterTalon1 = 1; //1
  public static int drive_leftSlaveVictor2 = 11; //11
  public static int drive_leftSlaveVictor3 = 12; //12
  
  //Right Drive Talon and Victors
  public static int drive_rightMasterTalon4 = 2; //2
  public static int drive_rightSlaveVictor5 = 9; //9
  public static int drive_rightSlaveVictor6 = 10; //10

  //Cargo Intake Motor Controllers
  public static int intake_TalonRight = 6;
  public static int intake_ArmTalonRight= 3;
  public static int intake_ArmVictorLeft = 8;

  //Cargo Shooter Motor Controllers
  public static int shooter_Talon_Arm = 4;
  public static int shooter_Talon_TopWheels = 5;
  public static int shooter_Victor_BotWheels = 7;

  //Hatch Panel Deployment Motor Controllers
  public static int hatch_Victor = 13;

  //SuperStructure Assignments
  public static int hatch_ds_kForward = 2;
  public static int hatch_ds_kReverse = 3;
  public static int shifter_ds_kForward = 0;
  public static int shifter_ds_kReverse = 1;
  public static int climb_ds_kForward = 4;
  public static int climb_ds_kReverse = 5;

  //Intake Arm Homing Limit Swtich Value
  public static int intake_ls_upper = 9;
  public static int intake_ls_lower = 8;

  //Shooter Arm Homing Limit Switch
  public static int shooter_ls_upper = 100;

  //Hatch Limit Switches
  public static int hatch_ls_front = 0;
  public static int hatch_ls_rear = 2;
}
