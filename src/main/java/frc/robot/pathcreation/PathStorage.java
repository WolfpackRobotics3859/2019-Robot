/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.pathcreation;

import frc.robot.Robot;

public class PathStorage {
    

public double[][] path1LeftVelocityArray;
public double[][] path1RightVelocityArray;

public double[][] path2LeftVelocityArray;
public double[][] path2RightVelocityArray;

//PARAMETERS PATH 1: test auto drive in loop
double[][] coordinates1 = new double[][]{
 {1000, 2},
 {5000, 2},
 {9000, 2}

}; 
double totalTime1 = 8;
double timeStep1 = 0.1;
double robotTrackWidth1 = 2.3;
String name1 = "First Path";

//PARAMETERS PATH 2: test auto drive straight
// double[][] coordinates2 = new double[][]{
//  {1, 0},
//  {2, 0}
// }; 
// double totalTime2 = 8;
// double timeStep2 = 0.1;
// double robotTrackWidth2 = 2.3;
// String name2 = "Second Path";
    
    
    public void initiatePathStorage() {
        Robot.pathgenprime.generate(coordinates1, totalTime1, timeStep1, robotTrackWidth1, name1);
        path1LeftVelocityArray = Robot.pathgenprime.leftVelocityGeneration.clone();
        path1RightVelocityArray = Robot.pathgenprime.rightVelocityGeneration.clone();
        System.out.println("Path Storage: Path 1: Received an array length of: " + path1LeftVelocityArray.length);

        // Robot.pathgenprime.generate(coordinates2, totalTime2, timeStep2, robotTrackWidth2, name2);
        // path2LeftVelocityArray = Robot.pathgenprime.leftVelocityGeneration.clone();
        // path2RightVelocityArray = Robot.pathgenprime.rightVelocityGeneration.clone();
        // System.out.println("Path Storage: Path 2: Received an array length of: " + path2LeftVelocityArray.length);
        //System.out.println("Path 1: Received an array length of: " + path1LeftVelocityArray.length);
        // mcPath.generate(coordinates2, totalTime2, timeStep2, robotTrackWidth2, name2);
        // synchronized(mcPath) {
        // try{
        //     mcPath.wait();
        // } catch(InterruptedException e) {
        //      e.printStackTrace();
        //   }
        // path2LeftVelocityArray = mcPath.getLeftVelocityArray();
        // path2LeftVelocityArray = mcPath.getRightVelocityArray();
        // System.out.println("Path 2: Received an array length of: " + path2LeftVelocityArray.length);
        // }
        return;
    }

}