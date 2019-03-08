/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.driver_input;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

//Drive Input is a nonterminating command that assigns values from the drive to methods that require driver input to trigger actions.
public class C_DriverInput extends Command {
  public C_DriverInput() {
    requires(Robot._Drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("[ROBOT] Driver Input enabled.");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 
    Robot._Overwatch.overwatchAssigner(OI.xbox1.getTriggerAxis(Hand.kLeft), OI.xbox1.getTriggerAxis(Hand.kRight), OI.xbox1.getBumper(Hand.kRight), OI.xbox1.getBumper(Hand.kLeft));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
