/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.drive.C_RegressedDrive;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.driver_input.C_DriverInput;
import frc.robot.commands.electropneumatic.C_ChangeGearing;
import frc.robot.commands.electropneumatic.C_ClimbEngage;
import frc.robot.commands.electropneumatic.C_ClimbRelease;
import frc.robot.commands.electropneumatic.C_HookClamp;
import frc.robot.commands.electropneumatic.C_HookRelease;
import frc.robot.commands.intake.C_IntakeManualControl;
import frc.robot.commands.intake.C_IntakeOut;
import frc.robot.commands.intake.C_IntakeStaticVoltageHome;
import frc.robot.commands.intake.C_IntakeStop;
import frc.robot.commands.intake.C_IntakeStow;
import frc.robot.commands.intake.C_IntakeSweep;
import frc.robot.commands.loops.L_RobotLoops;
import frc.robot.commands.shooter.C_ShooterManualControl;
import frc.robot.commands.shooter.C_ShooterShootPosition;
import frc.robot.commands.shooter.C_ShooterStop;
import frc.robot.commands.shooter.C_ShooterStow;
import frc.robot.commands.wheels.C_WheelsHold;
import frc.robot.commands.wheels.C_WheelsIntake;
import frc.robot.commands.wheels.C_WheelsShoot;
import frc.robot.subsystems.Sys_Drive;
import frc.robot.subsystems.Sys_ElectroPneumatic;
import frc.robot.subsystems.Sys_Hatch;
import frc.robot.subsystems.Sys_Intake;
import frc.robot.subsystems.Sys_Overwatch;
import frc.robot.subsystems.Sys_RobotTime;
import frc.robot.subsystems.Sys_Shooter;
import frc.robot.subsystems.Sys_Wheels;
import frc.robot.trajectories.Trajectory_Test;

public class Robot extends TimedRobot {
  
  public static Sys_Drive _Drive;
  public static Sys_Shooter _Shooter;
  public static Sys_Intake _Intake;
  public static Sys_Hatch _Hatch;
  public static Sys_Wheels _Wheels;
  public static Sys_ElectroPneumatic _ElectroPneumatic;
  public static Sys_Overwatch _Overwatch;
  
  public static OI m_oi;
  public static C_DriverInput cDriverInput;
  public static Constants constant;
  public static Trajectory_Test trajectory;
  public static L_RobotLoops looper;
  
  //Command visibility for OVERWATCH control
  public static C_IntakeManualControl cIntakeManualControl;
  public static C_IntakeOut cIntakeOut;
  public static C_IntakeStop cIntakeStop;
  public static C_IntakeStow cIntakeStow;
  public static C_IntakeSweep cIntakeSweep;
  public static C_IntakeStaticVoltageHome cIntakeStaticVoltageHome;

  public static C_ShooterManualControl cShooterManualControl;
  public static C_ShooterShootPosition cShooterShootPosition;
  public static C_ShooterStop cShooterStop;
  public static C_ShooterStow cShooterStow;

  public static C_WheelsHold cWheelsHold;
  public static C_WheelsIntake cWheelsIntake;
  public static C_WheelsShoot cWheelsShoot;

  public static C_ChangeGearing cChangeGearing;
  public static C_HookClamp cHookClamp;
  public static C_HookRelease cHookRelease;
  public static C_ClimbEngage cClimbEngage;
  public static C_ClimbRelease cClimbRelease;
  public static Sys_RobotTime _robotTime;

  public static C_RegressedDrive cRegressedDrive;
  
  @Override
  public void robotInit() {

    _robotTime = new Sys_RobotTime();
  
    //shouldn't need initialization, but here to prevent errors
    constant = new Constants();

    //Initializes subsystems.
    _Drive = new Sys_Drive();
    _Shooter = new Sys_Shooter();
    _Intake = new Sys_Intake();
    _Hatch = new Sys_Hatch();
    _Wheels = new Sys_Wheels();
    _ElectroPneumatic = new Sys_ElectroPneumatic();
    _Overwatch = new Sys_Overwatch();
    
    //configures subsystem actuators
    Robot._Drive.configureDrive();
    Robot._Intake.configureIntake();
    Robot._Shooter.configureShooter();
    Robot._Hatch.configureHatch();
    Robot._Wheels.configureWheels();
    Robot._robotTime.configureTimer();

    //enables operator interface
    m_oi = new OI();
    looper = new L_RobotLoops();

    cIntakeManualControl = new C_IntakeManualControl();
    cIntakeOut = new C_IntakeOut();
    cIntakeStop = new C_IntakeStop();
    cIntakeStow = new C_IntakeStow();
    cIntakeSweep = new C_IntakeSweep();
    cIntakeStaticVoltageHome = new C_IntakeStaticVoltageHome();

    cShooterManualControl = new C_ShooterManualControl();
    cShooterShootPosition = new C_ShooterShootPosition();
    cShooterStop = new C_ShooterStop();
    cShooterStow = new C_ShooterStow();

    cWheelsHold = new C_WheelsHold();
    cWheelsIntake = new C_WheelsIntake();
    cWheelsShoot = new C_WheelsShoot();

    cHookClamp = new C_HookClamp();
    cHookRelease= new C_HookRelease();

    cChangeGearing = new C_ChangeGearing();

    cDriverInput = new C_DriverInput();

    cRegressedDrive = new C_RegressedDrive();

    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);

    CameraServer.getInstance().startAutomaticCapture("CAMERA 1", "/dev/video0").setVideoMode(PixelFormat.kMJPEG, 160, 120, 20);
    CameraServer.getInstance().startAutomaticCapture("CAMERA 2", "/dev/video1").setVideoMode(PixelFormat.kMJPEG, 160, 120, 20);

    Robot._robotTime.timePrintln("[SYSTEM] Robot Init Completed.");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    _robotTime.resetTimer();
    cDriverInput.start();
    _robotTime.timePrintln("[SYSTEM] Autonomous Initiated.");
  }
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    //Initiate Teleoperative Driver Input
    cDriverInput.start();
    _robotTime.timePrintln("[SYSTEM] Teleoperative control initiated.");
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
  }
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
