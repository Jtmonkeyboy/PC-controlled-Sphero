package com.orbotix.common;

import java.util.List;

public abstract interface DiscoveryAgentEventListener
{
  public abstract void handleRobotsAvailable(List<Robot> paramList);
}
