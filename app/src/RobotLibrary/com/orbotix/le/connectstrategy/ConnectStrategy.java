package com.orbotix.le.connectstrategy;

import com.orbotix.common.Robot;
import java.util.List;

public abstract interface ConnectStrategy
{
  public abstract Robot getRobotToConnectFromAvailableNodes(List<Robot> paramList, Robot paramRobot);
}
