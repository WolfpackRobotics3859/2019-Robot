/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Sys_RobotTime extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Timer systemTimer;

  public void configureTimer(){
      systemTimer = new Timer();
      systemTimer.start();
      System.out.println("[RobotTimer] RobotTimer has begun at system time clock: " + Timer.getFPGATimestamp());
      System.out.println("[RobotTimer] Assuming time responsibility for robot operation.");
  }
  
  private double doubleTimeStorage;
  
  private String formattedTimeStamp(){
      doubleTimeStorage = systemTimer.get();
      String sHour,sMinute,sSecond,returnString;
      int hour,minute,second;
  
      hour = (int) doubleTimeStorage / 3600;
      minute = (int) doubleTimeStorage / 60;
      second = (int) doubleTimeStorage % 60;
  
      sHour = String.format("%02d", hour);
      sMinute = String.format("%02d", minute);
      sSecond = String.format("%02d", second);
  
      returnString = "{"+sHour+":"+sMinute+":"+sSecond+"}";
      return returnString;
  }
  
  public void timePrintln(String message){
    System.out.println(formattedTimeStamp() + " " + message);
  }

  public void resetTimer(){
    System.out.println(formattedTimeStamp() + " [ROBOT TIME] Timer reset at teleop/auto init.");
    systemTimer.reset();
  }
  
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
