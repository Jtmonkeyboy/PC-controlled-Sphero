package com.orbotix.common.internal;




















public enum RobotCommandId
{
  private byte a;
  



















  private RobotCommandId(int value)
  {
    a = ((byte)value);
  }
  
  public byte getValue() {
    return a;
  }
}
