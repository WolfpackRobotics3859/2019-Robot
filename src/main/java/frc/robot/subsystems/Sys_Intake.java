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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.intake.C_IntakeStow;

public class Sys_Intake extends Subsystem {
  VictorSPX m_ArmLeft;
  TalonSRX m_ArmRight;
  DigitalInput ls_highBound, ls_lowerBound;

  private static Sys_Intake mInstance = null;
  public synchronized static Sys_Intake getInstance(){
      if(mInstance==null){
        mInstance = new Sys_Intake();
      }
      return mInstance;
  }

  public synchronized int praiseTheOverseerWithEncoderValues(){
      return m_ArmRight.getSelectedSensorPosition();
  }
  public synchronized boolean praiseTheOverseerWithLSValues(String upperOrLower){
      if(upperOrLower == "upper"){
        return ls_highBound.get();
      } else {
        return ls_lowerBound.get();
      }
  }

  public void configureIntake(){
      System.out.println("[INTAKE] Initialized.");
      ls_highBound = new DigitalInput(RobotMap.intake_ls_upper);
      ls_lowerBound = new DigitalInput(RobotMap.intake_ls_lower);
      
      m_ArmRight= new TalonSRX(RobotMap.intake_ArmTalonRight);
      m_ArmRight.configFactoryDefault();
      m_ArmRight.set(ControlMode.PercentOutput, 0);
      m_ArmRight.setInverted(true); //set this later
      m_ArmRight.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);   
      m_ArmRight.enableVoltageCompensation(true);
      
      m_ArmLeft = new VictorSPX(RobotMap.intake_ArmVictorLeft);
      m_ArmLeft.configFactoryDefault();
      m_ArmLeft.set(ControlMode.PercentOutput, 0);
      m_ArmLeft.setInverted(false); //set this later
      m_ArmLeft.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
      m_ArmLeft.enableVoltageCompensation(true);
      m_ArmLeft.follow(m_ArmRight);

      m_ArmRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,Constants.kPIDLoopIdx, Constants.kTimeoutMs);
      m_ArmRight.setSensorPhase(false); // set this later
      m_ArmRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
      m_ArmRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
      m_ArmRight.configNominalOutputForward(0, Constants.kTimeoutMs);
      m_ArmRight.configNominalOutputReverse(0, Constants.kTimeoutMs);
      m_ArmRight.configPeakOutputForward(1, Constants.kTimeoutMs);
      m_ArmRight.configPeakOutputReverse(-1, Constants.kTimeoutMs);
      m_ArmRight.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
      m_ArmRight.config_kF(Constants.kSlotIdx, Constants.intakeArmPID.kF, Constants.kTimeoutMs);
      m_ArmRight.config_kP(Constants.kSlotIdx, Constants.intakeArmPID.kP, Constants.kTimeoutMs);
      m_ArmRight.config_kI(Constants.kSlotIdx, Constants.intakeArmPID.kI, Constants.kTimeoutMs);
      m_ArmRight.config_kD(Constants.kSlotIdx, Constants.intakeArmPID.kD, Constants.kTimeoutMs);
      m_ArmRight.configMotionCruiseVelocity(Constants.intakeCruiseVelocity, Constants.kTimeoutMs); 
      m_ArmRight.configMotionAcceleration(Constants.intakeMaxAccel, Constants.kTimeoutMs); 
      m_ArmRight.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
      m_ArmRight.setNeutralMode(NeutralMode.Brake);
  }
    
  public synchronized void intakeGoTo(double value) {
    if(Robot._Overwatch.isShooterStowed()){  
        m_ArmRight.set(ControlMode.MotionMagic, value);
    } else {
        m_ArmRight.set(ControlMode.MotionMagic, Constants.intake_OutPosition);
    }
  }

  public synchronized void stop() {
      m_ArmRight.neutralOutput();
  }

  public synchronized void autoSensorZero(){
    if(!ls_highBound.get()){
        if(m_ArmRight.getSelectedSensorPosition()<-50){
            System.out.println("False Zero Positive");
        } else {
      manualZeroSensor();
      }
      }
    }
  public synchronized void manualIntake(double value) {
      
    m_ArmRight.set(ControlMode.PercentOutput, value);
  }

  public synchronized void manualZeroSensor() {
    m_ArmRight.setSelectedSensorPosition(0);
  }

  public synchronized void goHome() {
      if(ls_highBound.get()){
          m_ArmRight.set(ControlMode.PercentOutput, 0.3);
          } else {
          m_ArmRight.set(ControlMode.PercentOutput, 0);
          }
      }

  @Override
  public void initDefaultCommand() {
      setDefaultCommand(new C_IntakeStow());
  }
}
