package com.orbotix;

public abstract interface Driveable
{
  public abstract void calibrating(boolean paramBoolean);
  
  public abstract void drive(float paramFloat1, float paramFloat2);
  
  public abstract void rotate(float paramFloat);
  
  public abstract void stop();
}
