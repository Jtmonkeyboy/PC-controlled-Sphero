package com.orbotix.common;

public abstract interface DiscoveryAgentProxy
  extends DiscoveryAgent
{
  public abstract void fireRobotStateChange(Robot paramRobot, RobotChangedStateListener.RobotChangedStateNotificationType paramRobotChangedStateNotificationType);
}
