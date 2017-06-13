package com.orbotix.common.sensor;


public class Acceleration
{
  private static final double a = 4096.0D;
  
  public double x;
  
  public double y;
  
  public double z;
  
  private static final String b = "%+2.3f";
  

  public static double normalize(short sensorValue)
  {
    return sensorValue / 4096.0D;
  }
  
  public Acceleration()
  {
    x = (this.y = this.z = 0.0D);
  }
  






  public Acceleration(short rawX, short rawY, short rawZ)
  {
    x = normalize(rawX);
    y = normalize(rawY);
    z = normalize(rawZ);
  }
  
  public boolean equals(Object o)
  {
    if (!(o instanceof Acceleration)) return false;
    Acceleration localAcceleration = (Acceleration)o;
    return (x == x) && (y == y) && (z == z);
  }
  


  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("[");
    localStringBuilder.append(String.format("%+2.3f", new Object[] { Double.valueOf(x) }))
      .append(' ').append(String.format("%+2.3f", new Object[] { Double.valueOf(y) }))
      .append(' ').append(String.format("%+2.3f", new Object[] { Double.valueOf(z) }))
      .append(']');
    return localStringBuilder.toString();
  }
}
