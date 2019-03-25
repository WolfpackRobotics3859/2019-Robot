/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.hatch.C_HatchStop;

public class Sys_Hatch extends Subsystem {

private VictorSPX mHatchMotor;
private DigitalInput frontLimitSwitch, rearLimitSwitch;
private static Sys_Hatch mInstance = null;

public synchronized static Sys_Hatch getInstance() {
  if (mInstance == null){
    mInstance = new Sys_Hatch();
  }
  return mInstance;
}

public boolean praiseTheOverseer(String forwardOrRear){
  if(forwardOrRear == "forward"){
    return frontLimitSwitch.get();
  } else {
    return rearLimitSwitch.get();
  }
}

public void configureHatch(){
  frontLimitSwitch = new DigitalInput(RobotMap.hatch_ls_front);
  rearLimitSwitch = new DigitalInput(RobotMap.hatch_ls_rear);
  mHatchMotor = new VictorSPX(RobotMap.hatch_Victor);
  mHatchMotor.configFactoryDefault();
  mHatchMotor.set(ControlMode.PercentOutput, 0);
  mHatchMotor.setInverted(true);
  mHatchMotor.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
  mHatchMotor.enableVoltageCompensation(true); 
}

public synchronized void forward(double speed){
  if(!frontLimitSwitch.get()){
    mHatchMotor.set(ControlMode.PercentOutput, speed);
  } else {
    mHatchMotor.neutralOutput();
  }
}

public synchronized void reverse(double speed){
  if(!rearLimitSwitch.get()){
    mHatchMotor.set(ControlMode.PercentOutput, -speed);
  } else {
    mHatchMotor.neutralOutput();
  }
}

public synchronized void stop(){
  mHatchMotor.neutralOutput();
}

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new C_HatchStop());
  }
}
