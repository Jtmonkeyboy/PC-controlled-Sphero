package com.orbotix.le.connectstrategy;

import com.orbotix.common.Robot;

public class NoConnectStrategy implements ConnectStrategy
{
  public NoConnectStrategy() {}
  
  public Robot getRobotToConnectFromAvailableNodes(java.util.List<Robot> availableRobots, Robot latestRobot) {
    return null;
  }
}
