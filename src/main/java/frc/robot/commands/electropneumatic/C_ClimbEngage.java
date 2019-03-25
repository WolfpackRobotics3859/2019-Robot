/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.electropneumatic;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class C_ClimbEngage extends Command {
  public C_ClimbEngage() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot._robotTime.timePrintln("[E/AIR] Engaging climb retention.");
    Robot.cClimbRelease.end();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot._ElectroPneumatic.engageClimb();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.cClimbRelease.start();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
