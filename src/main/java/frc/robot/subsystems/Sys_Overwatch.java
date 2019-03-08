/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.loops.L_RobotLoops;
import frc.robot.OI;

public class Sys_Overwatch extends Subsystem {

  //Retrieve Values for all On-Board Sensors
  private double encoder_drive_left;
  private double encoder_drive_right;
  private double encoder_intake_arm;
  private double encoder_shooter_arm;

  //Retrive Values for all Limit Switches
  private boolean limit_intake_upper;
  private boolean limit_intake_lower;
  private boolean limit_hatch_inner;
  private boolean limit_hatch_outer;

  public void getGlobalSensorValues(){
    encoder_drive_left = Robot._Drive.praiseTheOverseer("t1");
    encoder_drive_right = Robot._Drive.praiseTheOverseer("t4");

    encoder_intake_arm = Robot._Intake.praiseTheOverseerWithEncoderValues();

    encoder_shooter_arm = Robot._Shooter.praiseTheOverseer();

    limit_intake_upper = Robot._Intake.praiseTheOverseerWithLSValues("upper");
    limit_intake_lower = Robot._Intake.praiseTheOverseerWithLSValues("lower");

    limit_hatch_outer = Robot._Hatch.praiseTheOverseer("forward");
    limit_hatch_inner = Robot._Hatch.praiseTheOverseer("rear");
  }

  public synchronized boolean isIntakeExtended(){
    if(encoder_intake_arm<(Constants.overwatch_intake_IsClear)){
      return true;
    } else {
      return false;
    }
  }

  public synchronized boolean isShooterStowed(){
    if(encoder_shooter_arm<(Constants.overwatch_shooter_isStowed)){
      return true;
    } else {
      return false;
    }
  }

  public int shotSelector = 1;
  public synchronized int selectedShooterPosition() {
    if(shotSelector==1){
      return Constants.shooter_ForwardShot;
    } else {
      return Constants.shooter_RearShot;
    }
  }
  public synchronized double selectedShooterSpeed() {
    if(shotSelector==1){
      return Constants.wheels_SHOT_shootingF;
    } else {
      return Constants.wheels_SHOT_shootingR;
    }
  }
  
  public synchronized void shotManager(boolean forwardOrRear) {
    if(forwardOrRear){
      System.out.println("[OVERWATCH] Forward shot selected.");
      shotSelector = 1;
    } else {
      System.out.println("[OVERWATCH] Rear shot selected.");
      shotSelector = 2;
    }
  }

  private boolean intakeOut = false;
  private boolean intakeSweep = false;
  private boolean intakeStow = true;

  private boolean shooterOut = false;
  private boolean shooterStow = true;

  private boolean wheelsIntake = false;
  private boolean wheelsShoot = false;
  private boolean wheelsHold = true;

  private boolean ePneumaticHook = false;

  private boolean ignoreLeftInput = false;

  public void overwatchAssigner(double leftTriggerInput, double rightTriggerInput, boolean isRBpressed, boolean isLBpressed){
    double leftInput = OI.xbox1.getTriggerAxis(Hand.kLeft);
    double rightInput = OI.xbox1.getTriggerAxis(Hand.kRight);
    
    if(rightTriggerInput>0.1){
      ignoreLeftInput = true;
    } else {
      ignoreLeftInput = false;
    }

//Places intake priority over shooter, this prevents both axis overlapping assignments to systems.
    if(!ignoreLeftInput){
      if(leftInput>=0.1 && leftInput<0.5){
        intakeOut = true;
        ePneumaticHook = true;
        //intakeSweep
        intakeStow = false;
        shooterOut = false;
        shooterStow = true;
        wheelsIntake = false;
      //  wheelsShoot = false;
        wheelsHold = true;
      }
      if(leftInput>=0.5 && leftInput<0.95){
        intakeOut = true;
        ePneumaticHook = true;
        //intakeSweep
        intakeStow = false;
        shooterOut = true;
        shooterStow = false;
        wheelsIntake = false;
      //  wheelsShoot = false;
        wheelsHold = true;
      }
      if(leftInput>=0.95){
        intakeOut = true;
        ePneumaticHook = true;
        //intakeSweep
        intakeStow = false;
        shooterOut = true;
        shooterStow = false;
        wheelsIntake = false;
      //  wheelsShoot = true;
        wheelsHold = false;
      } else if(leftInput<0.1) {
        intakeOut = false;
        ePneumaticHook = false;
        intakeStow = true;
        shooterOut = false;
        shooterStow = true;
        wheelsIntake = false;
      //  wheelsShoot = false;
        wheelsHold = true;
        shotSelector = 1;
      }
    } 
    if(ignoreLeftInput) {
      if(rightInput>0.1){
        intakeOut = true;
        ePneumaticHook = false;
        intakeStow = false;
        shooterOut = false;
        shooterStow = true;
        wheelsIntake = true;
      //  wheelsShoot = false;
        wheelsHold = false;
      } else {
        intakeOut = false;
        ePneumaticHook = false;
        intakeStow = true;
        shooterOut = false;
        shooterStow = true;
        wheelsIntake = false;
      //  wheelsShoot = false;
        wheelsHold = true;
      }
    }
    intakeSweep = isRBpressed;
    wheelsShoot = isLBpressed;
  }
  
  public boolean manualOverride = false;
  public void overwatchManager(){
    
  if(!manualOverride){
    if(intakeOut && !intakeSweep){
      Robot.cIntakeOut.start();
    }
    if(intakeStow && !intakeSweep){
      if(encoder_intake_arm <= -100){
      Robot.cIntakeStow.start();
      } else {
        Robot.cIntakeStaticVoltageHome.start();
      }
    }
    if(shooterOut){
      Robot.cShooterShootPosition.start();
    }
    if(shooterStow){
      Robot.cShooterStow.start();
    }
    if(wheelsIntake){
      Robot.cWheelsIntake.start();
    }
    if(wheelsShoot){
      Robot.cWheelsShoot.start();
    }
    if(wheelsHold){
      Robot.cWheelsHold.start();
    }
    if(intakeSweep){
      Robot.cIntakeSweep.start();
    }
    if(!ePneumaticHook){
      if(!OI.xbox2.getAButton()){
      Robot.cHookClamp.start();
      }
    }
    if(ePneumaticHook){
      Robot.cHookRelease.start();
    }
  } else {
    Robot.cIntakeManualControl.start();

  }
}
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new L_RobotLoops());
  }
}
