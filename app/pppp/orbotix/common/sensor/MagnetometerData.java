package com.orbotix.common.sensor;


@Deprecated
public class MagnetometerData
{
  private ThreeAxisSensor a;
  
  private ThreeAxisSensor b;
  
  public MagnetometerData(ThreeAxisSensor filteredSensor, ThreeAxisSensor rawSensor)
  {
    a = filteredSensor;
    b = rawSensor;
  }
  
  public ThreeAxisSensor getMagnetometerDataFiltered() {
    return a;
  }
  
  public ThreeAxisSensor getMagnetometerDataRaw() {
    return b;
  }
}
