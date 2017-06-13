package com.orbotix.common.sensor;


public class BackEMFSensor
{
  public int leftMotorValue;
  
  public int rightMotorValue;
  
  public BackEMFSensor() {}
  
  public String toString()
  {
    return "BackEMFSensor{l=" + leftMotorValue + ", r=" + rightMotorValue + '}';
  }
}
