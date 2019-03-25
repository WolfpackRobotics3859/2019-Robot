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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.drive.C_RegressedDrive;
=======
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.driver_input.C_DriverInput;
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e


public class Sys_Drive extends Subsystem {

  private static WPI_TalonSRX t1,t4;
  private static WPI_VictorSPX t2,t3,t5,t6;
  private static ADXRS450_Gyro g1;
  private static PigeonIMU _pigeon;
  private static SpeedControllerGroup mGroupLeft, mGroupRight;
  private static DifferentialDrive mDrive;

  public synchronized double praiseTheOverseer(String t1Ort4){
    if(t1Ort4=="t1"){
      return t1.getSelectedSensorPosition();
    } else {
      return t4.getSelectedSensorPosition();
    }
  }

  public void configureDrive(){
    _pigeon = new PigeonIMU(0);
    t1 = new WPI_TalonSRX(RobotMap.drive_leftMasterTalon1);
    t4 = new WPI_TalonSRX(RobotMap.drive_rightMasterTalon4);
    
    configureMasterTalon(t1, true);
    configureMasterTalon(t4, false);
    
    t2 = new WPI_VictorSPX(RobotMap.drive_leftSlaveVictor2);
    t3 = new WPI_VictorSPX(RobotMap.drive_leftSlaveVictor3);
    t5 = new WPI_VictorSPX(RobotMap.drive_rightSlaveVictor5);
    t6 = new WPI_VictorSPX(RobotMap.drive_rightSlaveVictor6);

<<<<<<< HEAD
=======
    t2.setInverted(false);
    t3.setInverted(false);

    t5.setInverted(true);
    t6.setInverted(true);

>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
    mGroupLeft = new SpeedControllerGroup(t1, t2, t3);
    mGroupRight = new SpeedControllerGroup(t4, t5, t6);

    mDrive = new DifferentialDrive(mGroupLeft, mGroupRight);

<<<<<<< HEAD
    t2.configVoltageCompSaturation(9, Constants.kTimeoutMs);
    t3.configVoltageCompSaturation(9, Constants.kTimeoutMs);
    t5.configVoltageCompSaturation(9, Constants.kTimeoutMs);
    t6.configVoltageCompSaturation(9, Constants.kTimeoutMs);

    t2.enableVoltageCompensation(true);
    t3.enableVoltageCompensation(true);
    t5.enableVoltageCompensation(true);
    t6.enableVoltageCompensation(true);

=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
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
<<<<<<< HEAD
    // talonID.setInverted(!left);
=======
    talonID.setInverted(!left);
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
    talonID.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,Constants.kPIDLoopIdx,Constants.kTimeoutMs);
    talonID.configNominalOutputForward(0, Constants.kTimeoutMs);
    talonID.configNominalOutputReverse(0, Constants.kTimeoutMs);
    talonID.configPeakOutputForward(1, Constants.kTimeoutMs);
    talonID.configPeakOutputReverse(-1, Constants.kTimeoutMs);
<<<<<<< HEAD
    talonID.configVoltageCompSaturation(9.0, Constants.kTimeoutMs);
=======
    talonID.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
    talonID.enableVoltageCompensation(true);
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }

  public double leftSpeedApproved = 0;
  public double rightSpeedApproved = 0;
  public double rotateVariableL = 0;
  public double rotateVariableR = 0;
  private double leftControlAdditional;
  private double rightControlAdditional;
<<<<<<< HEAD
  private final double driveDivisible = 0.7;

  public void regressionDrive(double leftSpeed, double rotateAxisInput) {
    t1.enableVoltageCompensation(false);
    t2.enableVoltageCompensation(false);
    t4.enableVoltageCompensation(false);
    t3.enableVoltageCompensation(false);
    t5.enableVoltageCompensation(false);
    t6.enableVoltageCompensation(false);
=======
  public double driveDivisible = 2;

  public void regressionDrive(double leftSpeed, double rotateAxisInput) {
 
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
    if(Math.abs(leftSpeed)<0.1) {
      leftSpeedApproved = 0;
      rightSpeedApproved = 0;
    } if(leftSpeed>0.1) {
<<<<<<< HEAD
      leftSpeedApproved = (-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*driveDivisible-((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableR);
      rightSpeedApproved = (-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*driveDivisible-((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableL);
    } if(leftSpeed<-0.1) {
      leftSpeedApproved = -(-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*driveDivisible+((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableL);
      rightSpeedApproved = -(-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*driveDivisible+((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableR);
=======
      leftSpeedApproved = (-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)/driveDivisible-((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableR);
      rightSpeedApproved = (-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)/driveDivisible-((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableL);
    } if(leftSpeed<-0.1) {
      leftSpeedApproved = -(-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)/driveDivisible+((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableL);
      rightSpeedApproved = -(-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)/driveDivisible+((-0.001754068-0.0584286*leftSpeed+1.050021*leftSpeed*leftSpeed)*rotateVariableR);
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
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
<<<<<<< HEAD
      leftSpeedApproved = -(-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/2;
      rightSpeedApproved = (-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/2;
    }
    if(rotateAxisInput<0.1){
      rightSpeedApproved = -(-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/2;
      leftSpeedApproved = (-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/2;
=======
      leftSpeedApproved = -(-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/3;
      rightSpeedApproved = (-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/3;
    }
    if(rotateAxisInput<0.1){
      rightSpeedApproved = -(-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/3;
      leftSpeedApproved = (-0.001754068-0.0584286*rotateAxisInput+1.050021*rotateAxisInput*rotateAxisInput)/3;
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
    }
  }
  mDrive.tankDrive(leftSpeedApproved + leftControlAdditional, rightSpeedApproved + rightControlAdditional);
 }
<<<<<<< HEAD
 private double steerDir;
 private double driveDir;
public void rapidCurvatureDrive(double drive, double steer){
  t1.enableVoltageCompensation(true);
  t2.enableVoltageCompensation(true);
  t4.enableVoltageCompensation(true);
  t3.enableVoltageCompensation(true);
  t5.enableVoltageCompensation(true);
  t6.enableVoltageCompensation(true);
  lockDrive = drive;
  lockSteer = steer;
  boolean quickTurn = false;
  if(Math.abs(drive)<0.1 && Math.abs(steer)>0.1){
    quickTurn = true;
  } else {
    quickTurn = false;
  }
  mDrive.curvatureDrive(lockDrive, lockSteer, quickTurn);
=======

public void rapidCurvatureDrive(double drive, double steer, boolean quickTurn){
  mDrive.curvatureDrive(drive, steer, quickTurn);
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
}

private boolean m_LimelightHasValidTarget = false;
private double m_LimelightDriveCommand = 0.0;
private double m_LimelightSteerCommand = 0.0;

<<<<<<< HEAD
private double lockDrive, lockSteer;
public void autoLock(double drive, double steer){
    lockDrive = drive;
    lockSteer = steer;
    Update_Limelight_Tracking();
=======
public void autoLock(){
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
    if (m_LimelightHasValidTarget)
    {
          mDrive.arcadeDrive(m_LimelightDriveCommand,m_LimelightSteerCommand);
    }
    else
    {
<<<<<<< HEAD
          mDrive.arcadeDrive(lockDrive, lockSteer);
=======
          mDrive.arcadeDrive(0.0,0.0);
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
    }
}

public void Update_Limelight_Tracking()
  {
        // These numbers must be tuned for your Robot!  Be careful!
        final double STEER_K = 0.03;                    // how hard to turn toward the target
        final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 3;        // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

<<<<<<< HEAD
        if((DESIRED_TARGET_AREA - ta)<= 0.2){
          OI.xbox1.setRumble(RumbleType.kLeftRumble, 1);
          OI.xbox1.setRumble(RumbleType.kRightRumble, 1);
          OI.xbox2.setRumble(RumbleType.kLeftRumble, 1);
          OI.xbox2.setRumble(RumbleType.kRightRumble, 1);
        } else {
          OI.xbox2.setRumble(RumbleType.kLeftRumble, 0);
          OI.xbox2.setRumble(RumbleType.kRightRumble, 0);
          OI.xbox1.setRumble(RumbleType.kLeftRumble, 0);
          OI.xbox1.setRumble(RumbleType.kRightRumble, 0);
        }
        
=======
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
        if (tv < 1.0)
        {
          m_LimelightHasValidTarget = false;
          m_LimelightDriveCommand = 0.0;
          m_LimelightSteerCommand = 0.0;
          return;
        }

        m_LimelightHasValidTarget = true;

        // Start with proportional steering
        double steer_cmd = tx * STEER_K;
        m_LimelightSteerCommand = steer_cmd;

        // try to drive forward until the target area reaches our desired area
        double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

        // don't let the robot drive too fast into the goal
        if (drive_cmd > MAX_DRIVE)
        {
          drive_cmd = MAX_DRIVE;
        }
<<<<<<< HEAD
        m_LimelightDriveCommand = -drive_cmd;
=======
        m_LimelightDriveCommand = drive_cmd;
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
}

/**
 * Utilize for program access to drivebase subsystem.
<<<<<<< HEAD
 * @param drive
 * @param steer
 */

private double setDriveVar, setSteerVar;
public void setDrive(double drive, double steer){
  OI.xbox2.setRumble(RumbleType.kLeftRumble, 0);
  OI.xbox2.setRumble(RumbleType.kRightRumble, 0);
  OI.xbox1.setRumble(RumbleType.kLeftRumble, 0);
  OI.xbox1.setRumble(RumbleType.kRightRumble, 0);
  setDriveVar = drive;
  setSteerVar = steer;
  mDrive.arcadeDrive(setDriveVar, setSteerVar);
=======
 * @param leftSpeed
 * @param rightSpeed
 */
public void setDrive(double leftSpeed, double rightSpeed){
  t1.set(ControlMode.PercentOutput, leftSpeed);
  t4.set(ControlMode.PercentOutput, rightSpeed);
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
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
<<<<<<< HEAD
    setDefaultCommand(new C_RegressedDrive());
=======
    setDefaultCommand(new C_DriverInput());
>>>>>>> 0c0246a78bb3db3c9a40c25f5ce34b280853328e
  }
}
