package com.orbotix.common.sensor;

public class ThreeAxisSensor {
  public int x;
  public int y;
  public int z;
  private static final String a = "%+4d";
  
  public ThreeAxisSensor() {}
  
  public String toString() { StringBuilder localStringBuilder = new StringBuilder("[");
    localStringBuilder.append(String.format("%+4d", new Object[] { Integer.valueOf(x) }))
      .append(' ').append(String.format("%+4d", new Object[] { Integer.valueOf(y) }))
      .append(' ').append(String.format("%+4d", new Object[] { Integer.valueOf(z) })).append(']');
    return localStringBuilder.toString();
  }
}
