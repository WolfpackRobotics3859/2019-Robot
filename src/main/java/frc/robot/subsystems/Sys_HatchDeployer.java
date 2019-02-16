package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Sys_HatchDeployer extends Subsystem {

  VictorSPX mHatchMotor;
  DoubleSolenoid HatchPnuematic;
  DigitalOutput frontLimitSwitch, backLimitSwitch;

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
    }
    if(frontLimitSwitch.get() == true){
      mHatchMotor.set(ControlMode.PercentOutput, 0);
    }
  }
  if(whereDaHeck == "back"){
    if(backLimitSwitch.get() == false){
      mHatchMotor.set(ControlMode.PercentOutput, -.2);
    }
    if(backLimitSwitch.get() == true){
      mHatchMotor.set(ControlMode.PercentOutput, 0);
    }
  }
  if(whereDaHeck == "disable"){
    mHatchMotor.set(ControlMode.PercentOutput, 0);
  }
}

  @Override
  public void initDefaultCommand() {

  }
}
