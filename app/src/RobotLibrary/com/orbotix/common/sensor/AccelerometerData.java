package com.orbotix.common.sensor;



public class AccelerometerData
  extends a
{
  private static final double a = 4096.0D;
  

  private Acceleration b = null;
  private ThreeAxisSensor c = null;
  
  public AccelerometerData(ThreeAxisSensor filteredSensor)
  {
    if (filteredSensor != null) {
      b = new Acceleration();
      b.x = (x / 4096.0D);
      b.y = (y / 4096.0D);
      b.z = (z / 4096.0D);
    }
  }
  
  public Acceleration getFilteredAcceleration() {
    return b;
  }
  
  public ThreeAxisSensor getRawAcceleration() {
    return c;
  }
  















  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("[Accel");
    if (b != null) localStringBuilder.append(" f=").append(b);
    if (c != null) localStringBuilder.append(" : ").append(" r=").append(c);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
