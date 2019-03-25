/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class C_ShooterVoltageAssignment extends Command {
  private double requiredVoltage, duration;
  private Timer timer1 = new Timer();
  public C_ShooterVoltageAssignment(double voltage, double time) {
    // Use requires() here to declare subsystem dependencies

    requires(Robot._Shooter);
    requiredVoltage = voltage;
    duration = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot._robotTime.timePrintln("[SHOOTER] Assigning voltage " + requiredVoltage + " for " + duration + " seconds.");
    timer1.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot._Shooter.manualShooter(requiredVoltage);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(timer1.get()>=duration){
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot._robotTime.timePrintln("[SHOOTER] Terminating voltage assignment.");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
