package com.orbotix.common.internal;












public enum MainProcessorState
{
  private int a;
  











  private MainProcessorState(int value)
  {
    a = value;
  }
  
  public int getValue() {
    return a;
  }
}
