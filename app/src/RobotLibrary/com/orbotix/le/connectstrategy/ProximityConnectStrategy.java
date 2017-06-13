package com.orbotix.le.connectstrategy;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import com.orbotix.common.Robot;
import java.util.List;

public class ProximityConnectStrategy implements ConnectStrategy
{
  private b a = new b();
  
  public ProximityConnectStrategy() {}
  
  public Robot getRobotToConnectFromAvailableNodes(@NonNull List<Robot> availableRobots, @NonNull Robot latestRobot) { return a.getRobotToConnectFromAvailableNodes(availableRobots, latestRobot); }
  
  public void setSignalStrengthThreshold(@FloatRange(from=70.0D, to=100.0D) float threshold)
  {
    if (threshold < 70.0F) {
      a.a(70.0F);
    } else if (threshold > 100.0F) {
      a.a(100.0F);
    } else {
      a.a(threshold);
    }
  }
}
