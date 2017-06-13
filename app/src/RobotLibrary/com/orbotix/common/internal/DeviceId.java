package com.orbotix.common.internal;


public enum DeviceId
{
  private byte a;
  
  private DeviceId(int value)
  {
    a = ((byte)value);
  }
  
  public byte getValue() {
    return a;
  }
}
