package com.orbotix.common;

public abstract interface RobotChangedStateListener
{
  public abstract void handleRobotChangedState(Robot paramRobot, RobotChangedStateNotificationType paramRobotChangedStateNotificationType);
  
  public static enum RobotChangedStateNotificationType
  {
    private RobotChangedStateNotificationType() {}
  }
}
