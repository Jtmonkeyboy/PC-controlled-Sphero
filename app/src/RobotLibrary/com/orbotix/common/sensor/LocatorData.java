package com.orbotix.common.sensor;






public class LocatorData
  extends a
{
  LocatorSensor a;
  




  LocatorSensor b;
  





  public LocatorData(LocatorSensor position, LocatorSensor velocity)
  {
    a = position;
    if (velocity != null) {
      b = new LocatorSensor();
      b.x = (x / 10.0F);
      b.y = (y / 10.0F);
    } else {
      b = new LocatorSensor();
      b.x = 0.0F;
      b.y = 0.0F;
    }
  }
  





  public LocatorSensor getPosition()
  {
    return a;
  }
  




  public LocatorSensor getVelocity()
  {
    return b;
  }
  




  public float getPositionX()
  {
    return a == null ? 0.0F : a.x;
  }
  




  public float getPositionY()
  {
    return a == null ? 0.0F : a.y;
  }
  




  public float getVelocityX()
  {
    return b == null ? 0.0F : b.x;
  }
  




  public float getVelocityY()
  {
    return b == null ? 0.0F : b.y;
  }
  











  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("[Locator");
    if (a != null) localStringBuilder.append(" pos=").append(a);
    if (b != null) localStringBuilder.append(" : ").append(" vel=").append(b);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
