/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ShootOff;

/**
 * Add your docs here.
 */
public class Sys_CargoShooter extends Subsystem {

TalonSRX mArm, mRollerTop;
VictorSPX mRollerBot;
DigitalOutput limitS;
public int intakeposition;


public void configureShooter() {
  // limitS = new DigitalOutput(RobotMap.intakeArmLS);

  mRollerTop = new TalonSRX(RobotMap.shooterWheelsTopTalon);
  mRollerTop.configFactoryDefault();
  mRollerTop.set(ControlMode.PercentOutput, 0);
  mRollerTop.setInverted(false); //set this later
  mRollerTop.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mRollerTop.enableVoltageCompensation(true);  

  mRollerBot = new VictorSPX(RobotMap.shooterWheelsBotVictor);
  mRollerBot.configFactoryDefault();
  mRollerBot.set(ControlMode.PercentOutput, 0);
  mRollerBot.setInverted(false); //set this later
  mRollerBot.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mRollerBot.enableVoltageCompensation(true);
  mRollerBot.follow(mRollerTop);  

  mArm= new TalonSRX(RobotMap.shooterArmTalon);
  mArm.configFactoryDefault();
//  mArm.set(ControlMode.PercentOutput, 0);
  mArm.setInverted(true); //set this later
  mArm.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mArm.enableVoltageCompensation(true);
  
  mArm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,Constants.kPIDLoopIdx, Constants.kTimeoutMs);
  mArm.setSensorPhase(true); // set this later
  mArm.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
  mArm.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
  mArm.configNominalOutputForward(0, Constants.kTimeoutMs);
  mArm.configNominalOutputReverse(0, Constants.kTimeoutMs);
  mArm.configPeakOutputForward(1, Constants.kTimeoutMs);
  mArm.configPeakOutputReverse(-1, Constants.kTimeoutMs);
  mArm.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
  mArm.config_kF(Constants.kSlotIdx, Constants.shooterArmPID.kF, Constants.kTimeoutMs);
  mArm.config_kP(Constants.kSlotIdx, Constants.shooterArmPID.kP, Constants.kTimeoutMs);
  mArm.config_kI(Constants.kSlotIdx, Constants.shooterArmPID.kI, Constants.kTimeoutMs);
  mArm.config_kD(Constants.kSlotIdx, Constants.shooterArmPID.kD, Constants.kTimeoutMs);
  mArm.configMotionCruiseVelocity(Constants.intakeCruiseVelocity, Constants.kTimeoutMs); 
  mArm.configMotionAcceleration(Constants.intakeMaxAccel, Constants.kTimeoutMs); 
//  mArm.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
  mArm.setNeutralMode(NeutralMode.Brake);
}

public void shooterArmPosition(String desiredPosition) {
  intakeposition = Robot._CargoIntake.mArmRight.getSelectedSensorPosition();
  if (desiredPosition == "maxExtend") {
    mArm.set(ControlMode.MotionMagic, Constants.shooterMaxExtend);
  } 
  if (desiredPosition == "shoot") {
    mArm.set(ControlMode.MotionMagic, Constants.shooterShoot);
  }
  if (desiredPosition == "stow") {
    // if (limitS.get()) {
    //   mArm.neutralOutput();
    //   mArm.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    // } else { 
    //   mArm.set(ControlMode.MotionMagic, 0);
    // }
    mArm.set(ControlMode.MotionMagic, -100);
  }
  if (desiredPosition == "disabled") {
    mArm.set(ControlMode.PercentOutput, 0);
    return;
  }

}

public void shooterSpeed(double value){
  mRollerTop.set(ControlMode.PercentOutput, value);
  mRollerBot.set(ControlMode.PercentOutput, value);
}
public void shooterShooting(String mode) {
  if (mode == "shoot") {
    mRollerTop.set(ControlMode.PercentOutput, Constants.defaultShooterSpeed);
  }
  if (mode == "intake") {
    mRollerTop.set(ControlMode.PercentOutput, Constants.defaultShooterIntakeSpeed);
  }
  if (mode == "disabled") {
    mRollerTop.neutralOutput();  
    return;
  } else {
    return;
  }
}
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ShootOff());
  }
}
