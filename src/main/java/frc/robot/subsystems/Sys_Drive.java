/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.driver_input.C_DriverInput;


public class Sys_Drive extends Subsystem {

  private static TalonSRX t1,t4;
  private static VictorSPX t2,t3,t5,t6;
  private static ADXRS450_Gyro g1;
  private static PigeonIMU _pigeon;

  public synchronized double praiseTheOverseer(String t1Ort4){
    if(t1Ort4=="t1"){
      return t1.getSelectedSensorPosition();
    } else {
      return t4.getSelectedSensorPosition();
    }
  }

  public void configureDrive(){
    _pigeon = new PigeonIMU(0);
    t1 = new TalonSRX(RobotMap.drive_leftMasterTalon1);
    t4 = new TalonSRX(RobotMap.drive_rightMasterTalon4);
    
    configureMasterTalon(t1, true);
    configureMasterTalon(t4, false);
    
    t2 = new VictorSPX(RobotMap.drive_leftSlaveVictor2);
    t3 = new VictorSPX(RobotMap.drive_leftSlaveVictor3);
    t5 = new VictorSPX(RobotMap.drive_rightSlaveVictor5);
    t6 = new VictorSPX(RobotMap.drive_rightSlaveVictor6);

    t2.setInverted(false);
    t3.setInverted(false);

    t5.setInverted(true);
    t6.setInverted(true);

    t2.follow(t1);
    t3.follow(t1);

    t5.follow(t4);
    t6.follow(t4);

    t1.setSelectedSensorPosition(0);
    t4.setSelectedSensorPosition(0);

    g1 = new ADXRS450_Gyro();

    g1.reset();

    System.out.println("FRC Gyro Connectivity: " + g1.isConnected());
    System.out.println("FRC Gyro Reading " + g1.getAngle());
}

  public void configureMasterTalon(TalonSRX talonID, boolean left){
    talonID.configFactoryDefault();
    talonID.setSensorPhase(true);
    talonID.setInverted(!left);
    talonID.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,Constants.kPIDLoopIdx,Constants.kTimeoutMs);
    talonID.configNominalOutputForward(0, Constants.kTimeoutMs);
    talonID.configNominalOutputReverse(0, Constants.kTimeoutMs);
    talonID.configPeakOutputForward(1, Constants.kTimeoutMs);
    talonID.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    talonID.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
    talonID.enableVoltageCompensation(true);
  }

  public double leftSpeedApproved = 0;
  public double rightSpeedApproved = 0;
  public double rotateVariableL = 0;
  public double rotateVariableR = 0;
  private double leftControlAdditional;
  private double rightControlAdditional;
  public double driveDivisible = 2;

  public void joystickDrive(double leftSpeed, double rotateAxisInput, boolean x_auxInput, Boolean b_auxInput) {
    if(x_auxInput){
      leftControlAdditional = 0.25;
    }
    if(!x_auxInput){
      leftControlAdditional = 0;
    }
    if(b_auxInput){
      rightControlAdditional = 0.25;
    }
    if(!b_auxInput){
      rightControlAdditional = 0;
    }
    if(Math.abs(leftSpeed)<0.1) {
      leftSpeedApproved = 0;
      rightSpeedApproved = 0;
    } if(leftSpeed>0.1) {
      leftSpeedApproved = (-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)/driveDivisible-((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableR);
      rightSpeedApproved = (-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)/driveDivisible-((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableL);
    } if(leftSpeed<-0.1) {
      leftSpeedApproved = -(-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)/driveDivisible+((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableL);
      rightSpeedApproved = -(-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)/driveDivisible+((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableR);
    }

  if(Math.abs(rotateAxisInput)<0.1) {
    rotateVariableL = 0;
    rotateVariableR = 0;
  } if(rotateAxisInput>0.1){
    rotateVariableL = 0;
    rotateVariableR = (1.111111*rotateAxisInput)-0.1111111;
  } if(rotateAxisInput<-0.1){
    rotateVariableR = 0;
    rotateVariableL = (1.111111*Math.abs(rotateAxisInput))-0.1111111;
  }

  if(leftSpeedApproved==0 && rightSpeedApproved == 0 && Math.abs(rotateAxisInput)>0.1) {
    if(rotateAxisInput>0.1){
      leftSpeedApproved = -(-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/3;
      rightSpeedApproved = (-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/3;
    }
    if(rotateAxisInput<0.1){
      rightSpeedApproved = -(-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/3;
      leftSpeedApproved = (-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/3;
    }
  }

  t1.set(ControlMode.PercentOutput, leftSpeedApproved + leftControlAdditional);
  t4.set(ControlMode.PercentOutput, rightSpeedApproved + rightControlAdditional);
}

/**
 * Utilize for program access to drivebase subsystem.
 * @param leftSpeed
 * @param rightSpeed
 */
public void setDrive(double leftSpeed, double rightSpeed){
  t1.set(ControlMode.PercentOutput, leftSpeed);
  t4.set(ControlMode.PercentOutput, rightSpeed);
}

/**
 * Return TRUE for the LEFT encoder value, FALSE for RIGHT encoder value
 * @param trueForLeft
 */
public int getEncoderValue(boolean encoderSide){
  if(encoderSide){
    return t1.getSelectedSensorPosition();
  } else {
    return t4.getSelectedSensorPosition();
  }
}

 
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new C_DriverInput());
  }
}
