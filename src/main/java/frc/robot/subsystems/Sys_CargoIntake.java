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
import frc.robot.RobotMap;
import frc.robot.commands.CloseIntake;


public class Sys_CargoIntake extends Subsystem {

VictorSPX mArmLeft;
public TalonSRX mArmRight, mRollerRight;
DigitalOutput intakeLS;
public int shooterPosition;

public void configureIntake() {

  intakeLS = new DigitalOutput(RobotMap.intakeArmLS);

  mRollerRight = new TalonSRX(RobotMap.intakeTalonRight);
  mRollerRight.configFactoryDefault();
//  mRollerRight.set(ControlMode.PercentOutput, 0);
  mRollerRight.setInverted(true); //set this later
  mRollerRight.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mRollerRight.enableVoltageCompensation(true);  

  // mRollerRight = new VictorSPX(RobotMap.intakeVictorRight);
  // mRollerRight.configFactoryDefault();
  // mRollerRight.set(ControlMode.PercentOutput, 0);
  // mRollerRight.setInverted(false); //set this later
  // mRollerRight.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  // mRollerRight.enableVoltageCompensation(true);
  // mRollerRight.follow(mRollerLeft);  

  mArmRight= new TalonSRX(RobotMap.intakeArmTalonRight);
  mArmRight.configFactoryDefault();
//  mArmRight.set(ControlMode.PercentOutput, 0);
  mArmRight.setInverted(true); //set this later
  mArmRight.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);   
  mArmRight.enableVoltageCompensation(true);
  shooterPosition = mArmRight.getSelectedSensorPosition();
  
  mArmLeft = new VictorSPX(RobotMap.intakeArmVictorLeft);
  mArmLeft.configFactoryDefault();
//  mArmLeft.set(ControlMode.PercentOutput, 0);
  mArmLeft.setInverted(false); //set this later
  mArmLeft.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mArmLeft.enableVoltageCompensation(true);
  mArmLeft.follow(mArmRight);
  
  mArmRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,Constants.kPIDLoopIdx, Constants.kTimeoutMs);
  mArmRight.setSensorPhase(false); // set this later
  mArmRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
  mArmRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
  mArmRight.configNominalOutputForward(0, Constants.kTimeoutMs);
  mArmRight.configNominalOutputReverse(0, Constants.kTimeoutMs);
  mArmRight.configPeakOutputForward(1, Constants.kTimeoutMs);
  mArmRight.configPeakOutputReverse(-1, Constants.kTimeoutMs);
  mArmRight.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
  mArmRight.config_kF(Constants.kSlotIdx, Constants.intakeArmPID.kF, Constants.kTimeoutMs);
  mArmRight.config_kP(Constants.kSlotIdx, Constants.intakeArmPID.kP, Constants.kTimeoutMs);
  mArmRight.config_kI(Constants.kSlotIdx, Constants.intakeArmPID.kI, Constants.kTimeoutMs);
  mArmRight.config_kD(Constants.kSlotIdx, Constants.intakeArmPID.kD, Constants.kTimeoutMs);
  mArmRight.configMotionCruiseVelocity(Constants.intakeCruiseVelocity, Constants.kTimeoutMs); 
  mArmRight.configMotionAcceleration(Constants.intakeMaxAccel, Constants.kTimeoutMs); 
//  mArmRight.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
  mArmRight.setNeutralMode(NeutralMode.Brake);
}

public void rollerOn(boolean onOrNot, boolean reverse) {
  if (onOrNot && !reverse) {
    mRollerRight.set(ControlMode.PercentOutput, Constants.defaultIntakeRollerSpeed);
  } 
  if (onOrNot && reverse) {
    mRollerRight.set(ControlMode.PercentOutput, -Constants.defaultIntakeRollerSpeed);
  } else {
    mRollerRight.set(ControlMode.PercentOutput, 0);
  }
}

public void rollerSpeed(int value){
  mRollerRight.set(ControlMode.PercentOutput, value);
}

public void intakeArmPosition(String desiredPosition) {
  if (desiredPosition == "intake") {
    mArmRight.set(ControlMode.MotionMagic, Constants.intakeOutPosition);
    System.out.println("R"+mArmRight.getMotorOutputPercent()+" L"+mArmLeft.getMotorOutputPercent());
  } 
  if (desiredPosition == "sweep") {
    mArmRight.set(ControlMode.MotionMagic, Constants.intakeShootPosition);
  }
  if (desiredPosition == "stow") {
    mArmRight.set(ControlMode.MotionMagic, -500);
  }
  if (desiredPosition == "outoftheway") {
    if (mArmRight.getSelectedSensorPosition() < 1000) { //need to determine the good values for this
      mArmRight.set(ControlMode.MotionMagic, 1100);
    } else {
      mArmRight.set(ControlMode.PercentOutput, 0);
    }
  }
  if (desiredPosition == "disabled") {
    mArmRight.set(ControlMode.PercentOutput, 0);
    return;
  }
}

public void rehomeIntake() {
  if (!intakeLS.get()) {
    mArmRight.set(ControlMode.PercentOutput, -0.1);
  } else {
    mArmRight.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    mArmRight.neutralOutput();
    return;
  }
}

@Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new CloseIntake());
  }
}
