package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TeleoperativeDrive;

public class Sys_DriveTrain extends Subsystem {

public static int i=0;
public static int a=0;
WPI_TalonSRX t1,t4;
WPI_VictorSPX t2,t3,t5,t6; {

t1 = new WPI_TalonSRX(RobotMap.leftTalon1);
t4 = new WPI_TalonSRX(RobotMap.rightTalon4);
t2 = new WPI_VictorSPX(RobotMap.leftVictor2);
t3 = new WPI_VictorSPX(RobotMap.leftVictor3);
t5 = new WPI_VictorSPX(RobotMap.rightVictor5);
t6 = new WPI_VictorSPX(RobotMap.rightVictor6);

t1.setInverted(false);
t2.setInverted(true);
t3.setInverted(true);
t4.setInverted(true);
t5.setInverted(false);
t6.setInverted(false);

t2.follow(t1);
t3.follow(t1);
t5.follow(t4);
t6.follow(t4);


t1.configFactoryDefault();
t4.configFactoryDefault();

/* Config sensor used for Primary PID [Velocity] */
    t1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
                                        Constants.kPIDLoopIdx, 
                                        Constants.kTimeoutMs);
    t4.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
                                        Constants.kPIDLoopIdx, 
                                        Constants.kTimeoutMs);
    /**
 * Phase sensor accordingly. 
     * Positive Sensor Reading should match Green (blinking) Leds on Talon
     */
t1.setSensorPhase(true);
t4.setSensorPhase(true);

/* Config the peak and nominal outputs */
t1.configNominalOutputForward(0, Constants.kTimeoutMs);
t1.configNominalOutputReverse(0, Constants.kTimeoutMs);
t1.configPeakOutputForward(1, Constants.kTimeoutMs);
t1.configPeakOutputReverse(-1, Constants.kTimeoutMs);

t4.configNominalOutputForward(0, Constants.kTimeoutMs);
t4.configNominalOutputReverse(0, Constants.kTimeoutMs);
t4.configPeakOutputForward(1, Constants.kTimeoutMs);
t4.configPeakOutputReverse(-1, Constants.kTimeoutMs);

/* Config the Velocity closed loop gains in slot0 */
// t1.config_kF(Constants.kPIDLoopIdx, Constants.l_kGains_Velocit.kF, Constants.kTimeoutMs);
// t1.config_kP(Constants.kPIDLoopIdx, Constants.l_kGains_Velocit.kP, Constants.kTimeoutMs);
// t1.config_kI(Constants.kPIDLoopIdx, Constants.l_kGains_Velocit.kI, Constants.kTimeoutMs);
// t1.config_kD(Constants.kPIDLoopIdx, Constants.l_kGains_Velocit.kD, Constants.kTimeoutMs);

// t4.config_kF(Constants.kPIDLoopIdx, Constants.r_kGains_Velocit.kF, Constants.kTimeoutMs);
// t4.config_kP(Constants.kPIDLoopIdx, Constants.r_kGains_Velocit.kP, Constants.kTimeoutMs);
// t4.config_kI(Constants.kPIDLoopIdx, Constants.r_kGains_Velocit.kI, Constants.kTimeoutMs);
// t4.config_kD(Constants.kPIDLoopIdx, Constants.r_kGains_Velocit.kD, Constants.kTimeoutMs);

}

StringBuilder l_sb = new StringBuilder();
StringBuilder r_sb = new StringBuilder();

int _loops = 0;

public void intitializeVelocityDrive() {
/* Get Talon/Victor's current output percentage */
double lmotorOutput = t1.getMotorOutputPercent();
double rmotorOutput = t4.getMotorOutputPercent();
		
/* Prepare line to print */
l_sb.append("\tLout:");
r_sb.append("\tRout:");

/* Cast to int to remove decimal places */
l_sb.append((int) (lmotorOutput * 100));
l_sb.append("%");	// Percent
r_sb.append((int) (rmotorOutput * 100));
r_sb.append("%");	// Percent

l_sb.append("\tspd:");
l_sb.append(t1.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
l_sb.append("u"); 	// Native units
r_sb.append("\tspd:");
r_sb.append(t4.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
r_sb.append("u"); 	// Native units
  /**
   * Convert 500 RPM to units / 100ms.
   * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
   * velocity setpoint is in units/100ms
   */
  double l_targetVelocity_UnitsPer100ms = Robot.pathgen.path1LeftVelocityArray[i][1];
  double r_targetVelocity_UnitsPer100ms = Robot.pathgen.path1RightVelocityArray[i][1];

  /* 500 RPM in either direction */
  t1.set(ControlMode.Velocity, l_targetVelocity_UnitsPer100ms);
  t4.set(ControlMode.Velocity, r_targetVelocity_UnitsPer100ms);

  /* Append more signals to print when in speed mode. */
  l_sb.append("\terr:");
  l_sb.append(t1.getClosedLoopError(Constants.kPIDLoopIdx));
  l_sb.append("\ttrg:");
  l_sb.append(l_targetVelocity_UnitsPer100ms);
  r_sb.append("\terr:");
  r_sb.append(t4.getClosedLoopError(Constants.kPIDLoopIdx));
  r_sb.append("\ttrg:");
  r_sb.append(r_targetVelocity_UnitsPer100ms);
  
  if (i < Robot.pathgen.path1LeftVelocityArray.length-1) {
  i++;
  }
  /* Percent Output */

    /* Print built string every 10 loops */
if (++_loops >= 10) {
  _loops = 0;
  System.out.println(l_sb.toString());
  System.out.println(r_sb.toString());
    }
    /* Reset built string */
l_sb.setLength(0);
r_sb.setLength(0);
}

public double toProperUnits(double magicNumber) {
  //     ft/s        in / u/rot / ms
  // return (magicNumber*1222.8)/(Math.PI*6);
  return magicNumber*65.23;
}


// DifferentialDrive diffDrive;
// SpeedControllerGroup leftSpeedGroup;
// SpeedControllerGroup rightSpeedGroup; {
// leftSpeedGroup = new SpeedControllerGroup(t1,t2,t3);
// rightSpeedGroup = new SpeedControllerGroup (t4,t5,t6);
// diffDrive = new DifferentialDrive(leftSpeedGroup, rightSpeedGroup);
// }

public void teleopDrive(double leftSpeed, double rightSpeed) {
  t1.set(-leftSpeed);
  t4.set(-rightSpeed);
  // System.out.println(t1.getSelectedSensorVelocity());
  // System.out.println(t4.getSelectedSensorVelocity());

}


  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TeleoperativeDrive());
  }
}
