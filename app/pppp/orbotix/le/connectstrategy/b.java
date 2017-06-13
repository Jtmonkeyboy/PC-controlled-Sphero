package com.orbotix.le.connectstrategy;

import com.orbotix.common.Robot;
import java.util.List;








class b
  implements ConnectStrategy
{
  private Float a = Float.valueOf(90.0F);
  
  b() {}
  
  public Robot getRobotToConnectFromAvailableNodes(List<Robot> availableRobots, Robot latestRobot) { if ((latestRobot.getSignalQuality() >= a.floatValue()) && (!latestRobot.isConnecting())) {
      return latestRobot;
    }
    return null;
  }
  
  public void a(float paramFloat) {
    a = Float.valueOf(paramFloat);
  }
}
