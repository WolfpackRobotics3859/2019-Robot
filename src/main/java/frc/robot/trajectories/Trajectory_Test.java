/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.trajectories;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Trajectory_Test {
  
public Trajectory trajectoryLApproved, trajectoryRApproved;

public void configureTrajectory(){
    Waypoint[] points = new Waypoint[] {
      //  new Waypoint(-9, -4, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
        new Waypoint(2.5, 20, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
        new Waypoint(5, 20, 0),
        new Waypoint(8, 23, Pathfinder.d2r(90)),
        new Waypoint(8, 24, Pathfinder.d2r(90))                           // Waypoint @ x=0, y=0,   exit angle=0 radians
    };
    
    Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1, 0.25, 0.2);
    Trajectory trajectory = Pathfinder.generate(points, config);

    TankModifier modifier = new TankModifier(trajectory).modify(0.6477);

    Trajectory left = modifier.getLeftTrajectory();
    Trajectory right = modifier.getRightTrajectory();
    trajectoryLApproved = left;
    trajectoryRApproved = right;
}
}
