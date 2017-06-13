package com.orbotix.common.internal;








public enum CoreCommandId
{
  private byte a;
  







  private CoreCommandId(int value)
  {
    a = ((byte)value);
  }
  
  public byte getValue() { return a; }
}
