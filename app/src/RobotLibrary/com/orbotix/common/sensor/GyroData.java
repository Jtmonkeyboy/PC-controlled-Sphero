package com.orbotix.common.sensor;




public class GyroData
  extends a
{
  private ThreeAxisSensor a;
  


  public GyroData(ThreeAxisSensor filteredSensor)
  {
    a = filteredSensor;
  }
  
  public ThreeAxisSensor getRotationRateFiltered() {
    return a;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("[Gyro");
    if (a != null) localStringBuilder.append(" f=").append(a);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
