package com.orbotix.common.sensor;


@Deprecated
public class AttitudeData
{
  private AttitudeSensor a;
  

  public AttitudeData(AttitudeSensor attitudeSensor)
  {
    a = attitudeSensor;
  }
  
  public AttitudeSensor getAttitudeSensor()
  {
    return a;
  }
}
