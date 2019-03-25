/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.electropneumatic;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class C_ChangeGearing extends Command {
<<<<<<< HEAD
  public boolean terminateCommand = false;
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  public C_ChangeGearing() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
<<<<<<< HEAD
    terminateCommand = false;
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot._ElectroPneumatic.engageGearing();
<<<<<<< HEAD
=======
    Robot._Drive.driveDivisible = 1;
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
<<<<<<< HEAD
    return terminateCommand;
=======
    return false;
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot._ElectroPneumatic.disengageGearing();
<<<<<<< HEAD
=======
    Robot._Drive.driveDivisible = 2;
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
