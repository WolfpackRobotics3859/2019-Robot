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

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotCharacteristics;
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class Sys_AutonomousDrive extends Subsystem {

// VARIABLE INITIATION
public TankModifier modifier;
public TalonSRX t1,t4;
public VictorSPX t2,t3,t5,t6;
EncoderFollower left,right;
ADXRS450_Gyro gyro;
public StringBuilder l_sb,r_sb; {
  l_sb = new StringBuilder();
  r_sb = new StringBuilder();
}
//END OF INITIATION

public void enableAndConfigureGyro() {
 
  gyro = new ADXRS450_Gyro();
  gyro.calibrate();
  gyro.reset();
}

public void configureSpeedControllers() {

t1 = new TalonSRX(RobotMap.leftTalon1);
t4 = new TalonSRX(RobotMap.rightTalon4);
t2 = new VictorSPX(RobotMap.leftVictor2);
t3 = new VictorSPX(RobotMap.leftVictor3);
t5 = new VictorSPX(RobotMap.rightVictor5);
t6 = new VictorSPX(RobotMap.rightVictor6);

t1.configFactoryDefault();
t4.configFactoryDefault();

t4.setInverted(true);
t5.setInverted(true);
t6.setInverted(true);

t2.follow(t1);
t3.follow(t1);
t5.follow(t4);
t6.follow(t4);

t1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
t4.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

}

public void writeTrajectories() {
 
      Waypoint[] points = new Waypoint[] {
          new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
          new Waypoint(2, 1, Pathfinder.d2r(45)),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
          new Waypoint(3, 2, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
      };
    
    Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,Trajectory.Config.SAMPLES_HIGH, 0.02, 4.2, 1, 30);
    Trajectory trajectory = Pathfinder.generate(points, config);

    modifier = new TankModifier(trajectory).modify(RobotCharacteristics.wheel_base_distance);
}

public void configureEncoders() {

  left = new EncoderFollower(modifier.getLeftTrajectory());
  right = new EncoderFollower(modifier.getRightTrajectory());

  left.configureEncoder(t1.getSelectedSensorPosition(1), 1024, RobotCharacteristics.wheel_diameter);
  right.configureEncoder(t4.getSelectedSensorPosition(1), 1024, RobotCharacteristics.wheel_diameter);

  left.configurePIDVA(0.1, 0.0, 0.0, 1 / ((9000*Math.PI*0.1524)/1024), 0);
  right.configurePIDVA(0.1, 0.0, 0.0, 1 / ((9000*Math.PI*0.1524)/1024), 0);
}

public void pathControlLoop() {

  int _loops = 0;

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
  
  double l = left.calculate(t1.getSelectedSensorPosition(1));
  double r = right.calculate(t4.getSelectedSensorPosition(1));

  // double gyro_heading = gyro.getAngle();    // Assuming the gyro is giving a value in degrees
  // double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

  // double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
  // double turn = 0.8 * (-1.0/80.0) * angleDifference;

  // l_sb.append((int) (desired_heading));
  // l_sb.append("deg");	// Degreees

  // l_sb.append((int) (gyro_heading));
  // l_sb.append("deg");	// Degrees
  
  // l_sb.append((int) (angleDifference));
  // l_sb.append("deg");	// Degrees

  // t1.set(ControlMode.PercentOutput, (l + turn));
  // t4.set(ControlMode.PercentOutput, (r - turn));
  t1.set(ControlMode.PercentOutput, l);
  t4.set(ControlMode.PercentOutput, r);

  if (++_loops >= 10) {
    _loops = 0;
    System.out.println(l_sb.toString());
    System.out.println(r_sb.toString());
  }
  l_sb.setLength(0);
  r_sb.setLength(0);
}



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new ExecuteAutonomousDrive());
  }
}
