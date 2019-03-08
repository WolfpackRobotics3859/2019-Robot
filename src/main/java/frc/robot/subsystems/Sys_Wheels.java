/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.wheels.C_WheelsHold;

public class Sys_Wheels extends Subsystem {

private TalonSRX mIntake_Roller, mShooter_Top;
private VictorSPX mShooter_Bot;
private static Sys_Wheels mInstance = null;

public synchronized static Sys_Wheels getInstance() {
  if (mInstance == null){
    mInstance = new Sys_Wheels();
  }
    return mInstance;
  }

public synchronized void configureWheels(){
  mIntake_Roller = new TalonSRX(RobotMap.intake_TalonRight);
  mIntake_Roller.configFactoryDefault();
  mIntake_Roller.set(ControlMode.PercentOutput, 0);
  mIntake_Roller.setInverted(true); //set this later
  mIntake_Roller.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mIntake_Roller.enableVoltageCompensation(true); 
  
  mShooter_Top = new TalonSRX(RobotMap.shooter_Talon_TopWheels);
  mShooter_Top.configFactoryDefault();
  mShooter_Top.set(ControlMode.PercentOutput, 0);
  mShooter_Top.setInverted(false); //set this later
  mShooter_Top.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mShooter_Top.enableVoltageCompensation(true);  

  mShooter_Bot = new VictorSPX(RobotMap.shooter_Victor_BotWheels);
  mShooter_Bot.configFactoryDefault();
  mShooter_Bot.set(ControlMode.PercentOutput, 0);
  mShooter_Bot.setInverted(false); //set this later
  mShooter_Bot.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mShooter_Bot.enableVoltageCompensation(true);
//  mShooter_Bot.follow(mShooter_Top);
  return; 
}

public synchronized void setShooterWheels(double speed) {
  mShooter_Top.set(ControlMode.PercentOutput, speed);
  mShooter_Bot.set(ControlMode.PercentOutput, speed);
}

public synchronized void setShooterShoot(double speed) {
  if(Robot._Overwatch.shotSelector == 1){
    mShooter_Bot.set(ControlMode.PercentOutput, speed);
    mShooter_Top.set(ControlMode.PercentOutput, speed);
  } else {
    mShooter_Bot.set(ControlMode.PercentOutput, speed);
    mShooter_Top.set(ControlMode.PercentOutput, -0.1);
  }
}
public synchronized void setIntakeRoller(double speed) {
  mIntake_Roller.set(ControlMode.PercentOutput, speed);
}

public synchronized void stopShooterWheels() {
  mShooter_Top.neutralOutput();
}

public synchronized void stopIntakeRoller() {
  mIntake_Roller.neutralOutput();
}

public synchronized void stopAllWheels() {
  mShooter_Top.neutralOutput();
  mIntake_Roller.neutralOutput();
}

public void intakeWheelsMove(double controller){
  if(controller>0.4){
  mIntake_Roller.set(ControlMode.PercentOutput, Constants.wheels_INTA_intaking);
  mShooter_Top.set(ControlMode.PercentOutput, Constants.wheels_SHOT_intaking);
  } else {
    mIntake_Roller.set(ControlMode.PercentOutput, 0);
    if(OI.xbox1.getTriggerAxis(Hand.kLeft)<=0.97){
    mShooter_Top.set(ControlMode.PercentOutput, Constants.wheels_SHOT_holding);
    }
  }
}
public void shooterWheelsMove(){
  mShooter_Top.set(ControlMode.PercentOutput, Robot._Overwatch.selectedShooterSpeed());
}

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new C_WheelsHold());
  }
}
