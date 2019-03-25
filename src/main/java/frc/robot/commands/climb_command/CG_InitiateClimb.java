/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb_command;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.T_WaitTime;
import frc.robot.commands.drive.C_TimedDrive;
import frc.robot.commands.electropneumatic.C_ClimbRelease;
import frc.robot.commands.intake.C_IntakeReachPosition;
import frc.robot.commands.intake.C_IntakeStow;
import frc.robot.commands.intake.C_IntakeSweep;
import frc.robot.commands.intake.C_IntakeVoltageAssignment;
import frc.robot.commands.shooter.C_ShooterReachPosition;
import frc.robot.commands.shooter.C_ShooterStow;
import frc.robot.commands.shooter.C_ShooterVoltageAssignment;

public class CG_InitiateClimb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_InitiateClimb() {

    addParallel(new C_ShooterReachPosition(1200, 1100));
    addSequential(new C_IntakeReachPosition(-900, -890));

    addParallel(new T_WaitTime(1));
    addSequential(new C_ClimbRelease());

    addParallel(new C_ShooterReachPosition(300, 320));
    addSequential(new C_IntakeReachPosition(-1200, -1100));

    addParallel(new C_ShooterVoltageAssignment(-1, 3));
    addSequential(new C_IntakeVoltageAssignment(-1, 3));

    addParallel(new C_ShooterVoltageAssignment(-1, 2));
    addParallel(new C_TimedDrive(2, 1, 0));
    addSequential(new C_IntakeVoltageAssignment(-1, 2));

    addParallel(new C_ShooterStow());
    addParallel(new C_IntakeSweep());
    addSequential(new C_TimedDrive(2, 0.5, 0));

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
