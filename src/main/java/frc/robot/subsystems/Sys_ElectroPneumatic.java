/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.electropneumatic.C_HookClamp;

public class Sys_ElectroPneumatic extends Subsystem {
  DoubleSolenoid hatch_claw, drive_shifter, climb_release; {
    
  hatch_claw = new DoubleSolenoid(RobotMap.hatch_ds_kForward,RobotMap.hatch_ds_kReverse);
  drive_shifter = new DoubleSolenoid(RobotMap.shifter_ds_kForward,RobotMap.shifter_ds_kReverse);
  climb_release = new DoubleSolenoid(RobotMap.climb_ds_kForward, RobotMap.climb_ds_kReverse);
  }
  public Relay spike;
  
  public void turnOnSpike(){
    spike.set(Relay.Value.kForward);
  }

  public void configureSuperStructure() {
    drive_shifter.set(DoubleSolenoid.Value.kForward);
    hatch_claw.set(DoubleSolenoid.Value.kForward);
    spike = new Relay(0);
    spike.set(Relay.Value.kForward);
  }
    
  public synchronized void engageGearing(){
      drive_shifter.set(DoubleSolenoid.Value.kReverse);
    }
  public synchronized void disengageGearing(){
      drive_shifter.set(DoubleSolenoid.Value.kForward);
  }

  public synchronized void releaseClimb(){
    climb_release.set(DoubleSolenoid.Value.kReverse);
  }
  public synchronized void engageClimb(){
    climb_release.set(DoubleSolenoid.Value.kForward);
}
  
  public synchronized void theHook(boolean deployOrRetract) {
    if(deployOrRetract) {
      hatch_claw.set(DoubleSolenoid.Value.kReverse);
    } else {
      hatch_claw.set(DoubleSolenoid.Value.kForward);
    }
    }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
