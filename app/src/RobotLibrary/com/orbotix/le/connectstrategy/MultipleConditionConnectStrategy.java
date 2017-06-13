package com.orbotix.le.connectstrategy;

import com.orbotix.common.Robot;

public class MultipleConditionConnectStrategy implements ConnectStrategy
{
  private ConnectStrategy[] a;
  
  public MultipleConditionConnectStrategy(ConnectStrategy... connectStrategies)
  {
    a = connectStrategies;
  }
  
  public Robot getRobotToConnectFromAvailableNodes(java.util.List<Robot> availableRobots, Robot latestRobot)
  {
    for (ConnectStrategy localConnectStrategy : a) {
      Robot localRobot = localConnectStrategy.getRobotToConnectFromAvailableNodes(availableRobots, latestRobot);
      if (localRobot != null) {
        return localRobot;
      }
    }
    return null;
  }
}
