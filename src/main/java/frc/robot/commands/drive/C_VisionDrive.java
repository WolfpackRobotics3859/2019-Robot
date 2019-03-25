/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

<<<<<<< HEAD
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
=======
import edu.wpi.first.wpilibj.command.Command;
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
import frc.robot.Robot;

public class C_VisionDrive extends Command {
  public C_VisionDrive() {
    requires(Robot._Drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
<<<<<<< HEAD
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
    System.out.println("[DRIVE] Enabling vision drive.");
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
<<<<<<< HEAD
    Robot._Drive.autoLock(OI.xbox1.getRawAxis(1), OI.xbox1.getRawAxis(4));
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
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
