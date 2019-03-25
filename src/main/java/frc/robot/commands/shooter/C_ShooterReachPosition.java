/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class C_ShooterReachPosition extends Command {
  private int shooterGoal, acceptableGoal;
  
  public C_ShooterReachPosition(int desiredGoal, int acceptableEndPosition) {
    requires(Robot._Shooter);
    shooterGoal = desiredGoal;
    acceptableGoal = acceptableEndPosition;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot._robotTime.timePrintln("[SHOOTER] Attempting to reach goal position: " + shooterGoal);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot._Shooter.shooterGoTo(shooterGoal);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Math.abs(Robot._Shooter.praiseTheOverseer()-acceptableGoal)<50){
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot._robotTime.timePrintln("[SHOOTER] Goal Reached, handing off.");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
