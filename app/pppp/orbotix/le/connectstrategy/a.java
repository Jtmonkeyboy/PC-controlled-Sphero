package com.orbotix.le.connectstrategy;

import com.orbotix.common.Robot;
import java.util.Date;
import java.util.List;

class a
  implements ConnectStrategy
{
  private Date a;
  private Robot b;
  
  a() {}
  
  public Robot getRobotToConnectFromAvailableNodes(List<Robot> availableRobots, Robot latestRobot)
  {
    if (a == null) {
      a = new Date();
      b = latestRobot;
      return null;
    }
    if ((a.getTime() - new Date().getTime() <= -1000L) && 
      (b != null) && (availableRobots.size() == 1)) {
      a = null;
      return b;
    }
    
    return null;
  }
}
