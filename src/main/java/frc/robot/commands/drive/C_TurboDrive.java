/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
<<<<<<< HEAD
import frc.robot.OI;
import frc.robot.Robot;
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e

public class C_TurboDrive extends Command {
  public C_TurboDrive() {
    // Use requires() here to declare subsystem dependencies
<<<<<<< HEAD
    requires(Robot._Drive);
=======
    // eg. requires(chassis);
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
<<<<<<< HEAD
    Robot._robotTime.timePrintln("[DRIVE] Turbo Drive Active.");
    Robot.cChangeGearing.start();
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
<<<<<<< HEAD
    Robot._Drive.rapidCurvatureDrive(-OI.xbox1.getRawAxis(1), OI.xbox1.getRawAxis(4));
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
<<<<<<< HEAD
  Robot.cChangeGearing.terminateCommand = true;
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
<<<<<<< HEAD
    end();
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }
}
