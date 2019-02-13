/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.pathcreation;

import java.util.Arrays;

import frc.robot.FalconPathPlanner;

public class PathGenerator {

  double[][] leftVelocityGeneration;
  double[][] rightVelocityGeneration;

  public void generate(double[][]waypoints, double totalTime, double timeStep, double robotTrackWidth, String name) {

      long startTime = System.nanoTime();
      
      FalconPathPlanner path = new FalconPathPlanner(waypoints);
      path.calculate(totalTime, timeStep, robotTrackWidth);
      
      leftVelocityGeneration = Arrays.copyOf(path.smoothLeftVelocity, path.smoothLeftVelocity.length);
      rightVelocityGeneration = Arrays.copyOf(path.smoothRightVelocity, path.smoothRightVelocity.length);
      path.smoothLeftVelocity = leftVelocityGeneration;
      path.smoothRightVelocity = rightVelocityGeneration;
  
      long endTime = System.nanoTime();
      long duration = (endTime - startTime)/1000000;
      System.out.println("PathGenerator: I have generated a path named " + name + " and the array size is " + path.smoothLeftVelocity.length + " in " + duration + "ms");
  }
  

  public double[][] getLeftVelocityArray() {
    return leftVelocityGeneration;
  }

  public double[][] getRightVelocityArray() {
    return rightVelocityGeneration;
  }

  public void destroyTheArrays() {
    Arrays.fill(leftVelocityGeneration, null);
    Arrays.fill(rightVelocityGeneration, null);
    System.out.println("PathGenerator: The Arrays have been wiped.");
  }

}
