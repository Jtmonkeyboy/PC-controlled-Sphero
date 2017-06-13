package com.orbotix.common;

import android.content.Context;
import java.util.List;

public abstract interface DiscoveryAgent
{
  public abstract boolean startDiscovery(Context paramContext)
    throws DiscoveryException;
  
  public abstract void stopDiscovery();
  
  public abstract List<Robot> getRobots();
  
  public abstract List<Robot> getConnectingRobots();
  
  public abstract List<Robot> getConnectedRobots();
  
  public abstract List<Robot> getOnlineRobots();
  
  public abstract void connect(Robot paramRobot);
  
  public abstract void disconnectAll();
  
  public abstract void addDiscoveryListener(DiscoveryAgentEventListener paramDiscoveryAgentEventListener);
  
  public abstract void removeDiscoveryListener(DiscoveryAgentEventListener paramDiscoveryAgentEventListener);
  
  public abstract void addRobotStateListener(RobotChangedStateListener paramRobotChangedStateListener);
  
  public abstract void removeRobotStateListener(RobotChangedStateListener paramRobotChangedStateListener);
  
  public abstract void addDiscoveryChangedStateListener(DiscoveryStateChangedListener paramDiscoveryStateChangedListener);
  
  public abstract void removeDiscoveryChangedStateListener(DiscoveryStateChangedListener paramDiscoveryStateChangedListener);
  
  public abstract Context getContext();
  
  public abstract int getMaxConnectedRobots();
  
  public abstract void setMaxConnectedRobots(int paramInt);
  
  public abstract boolean isDiscovering();
}
