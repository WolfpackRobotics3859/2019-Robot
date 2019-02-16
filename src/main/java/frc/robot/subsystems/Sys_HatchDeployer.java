/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Sys_HatchDeployer extends Subsystem {

  VictorSPX mHatchMotor;
  DoubleSolenoid hatchPnuematic;
  public DigitalOutput frontLimitSwitch, backLimitSwitch;

public void configureHatch(){
      
  frontLimitSwitch = new DigitalOutput(RobotMap.frontHatchLS);
  backLimitSwitch = new DigitalOutput(RobotMap.backHatchLS);
  
  mHatchMotor = new VictorSPX(RobotMap.shooterWheelsBotVictor);
  mHatchMotor.configFactoryDefault();
  mHatchMotor.set(ControlMode.PercentOutput, 0);
  mHatchMotor.setInverted(false); //set this later
  mHatchMotor.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mHatchMotor.enableVoltageCompensation(true); 
}

public void hatchDeploy(String whereDaHeck){
  if(whereDaHeck == "forward"){
    if(frontLimitSwitch.get() == false){
      mHatchMotor.set(ControlMode.PercentOutput, .2);
    }else{
      mHatchMotor.set(ControlMode.PercentOutput, 0);
    }
  }
  if(whereDaHeck == "back"){
    if(backLimitSwitch.get() == false){
      mHatchMotor.set(ControlMode.PercentOutput, -.2);
    }else{
      mHatchMotor.set(ControlMode.PercentOutput, 0);
    }
  }
  if(whereDaHeck == "disable"){
    mHatchMotor.set(ControlMode.PercentOutput, 0);
  }
}

public void releaseHatch(){
  hatchPnuematic.set(Value.kForward);
}

public void grabHatch(){
  hatchPnuematic.set(Value.kReverse);
}

  @Override
  public void initDefaultCommand() {

  }
}
