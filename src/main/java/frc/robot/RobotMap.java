/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
  
  //Left Drive Talon and Victors
  public static int leftTalon1 = 1;
  public static int leftVictor2 = 11;
  public static int leftVictor3 = 12;
  
  //Right Drive Talon and Victors
  public static int rightTalon4 = 2;
  public static int rightVictor5 = 9;
  public static int rightVictor6 = 10;

  //Cargo Intake Motor Controllers
  // public static int intakeVictorLeft = 7;
  public static int intakeTalonRight = 6;
  public static int intakeArmTalonRight= 3;
  public static int intakeArmVictorLeft = 8;

  //Cargo Shooter Motor Controllers
  public static int shooterArmTalon = 4;
  public static int shooterWheelsTopTalon = 5;
  public static int shooterWheelsBotVictor = 7;

  //Hatch Panel Deployment Motor Controllers
  public static int hatchDeploymentVictor = 13;

  //Intake Arm Homing Limit Swtich Value
  public static int intakeArmLS = 1;

  //Shooter Arm Homing Limit Switch
  public static int shooterArmLS = 2;




  

  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
