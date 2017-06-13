package com.orbotix.common.internal;



public enum BootloaderCommandId
{
  private byte a;
  

  private BootloaderCommandId(int value)
  {
    a = ((byte)value);
  }
  
  public byte getValue() { return a; }
}
