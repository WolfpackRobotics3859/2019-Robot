/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.ButtonMonitor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
//driver controller 
  public static XboxController driver1 = new XboxController(0);

//co-driver controller
  public static XboxController driver2 = new XboxController(1);

//driver buttons
  public static Button aButton1 = new JoystickButton(driver1, 1);
  public static Button bButton1 = new JoystickButton(driver1, 2);
  public static Button xButton1 = new JoystickButton(driver1, 3);
  public static Button yButton1 = new JoystickButton(driver1, 4);
  public static Button lBumper1 = new JoystickButton(driver1, 5);
  public static Button rBumper1 = new JoystickButton(driver1, 6);

//co-driver buttons
  public static Button aButton2 = new JoystickButton(driver2, 1);
  public static Button bButton2 = new JoystickButton(driver2, 2);
  public static Button xButton2 = new JoystickButton(driver2, 3);
  public static Button yButton2 = new JoystickButton(driver2, 4);
  public static Button lBumper2 = new JoystickButton(driver2, 5);
  public static Button rBumper2 = new JoystickButton(driver2, 6);

  public OI() {
    aButton1.whileHeld(new IntakeBall());
    aButton1.whenReleased(new StopIntaking());

    aButton2.whileHeld(new FoldOutShoot());
    bButton2.whenPressed(new FoldOutClosed());
    xButton2.whileHeld(new Sweep());
    yButton2.whileHeld(new ShootBall());

    rBumper1.whenPressed(new MoveHatchIn());
    rBumper1.when;
 
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
