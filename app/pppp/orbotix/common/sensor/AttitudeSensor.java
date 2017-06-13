package com.orbotix.common.sensor;


public class AttitudeSensor
  extends a
{
  public int pitch;
  
  public int roll;
  
  public int yaw;
  

  public AttitudeSensor() {}
  

  /**
   * @deprecated
   */
  public void getAttitudeSensor() {}
  

  public String toString()
  {
    return "Attitude{ r=" + roll + " p=" + pitch + " y=" + yaw + '}';
  }
}
