package com.orbotix.common.utilities.math;



public class Value
{
  public Value() {}
  


  public static float clamp(float value, float min, float max)
  {
    if (value > max)
      return max;
    if (value < min) {
      return min;
    }
    return value;
  }
  






  public static double clamp(double value, double min, double max)
  {
    if (value > max)
      return max;
    if (value < min) {
      return min;
    }
    return value;
  }
  






  public static int clamp(int value, int min, int max)
  {
    if (value > max)
      return max;
    if (value < min) {
      return min;
    }
    return value;
  }
  







  public static double window(double value, double windowValue, double delta)
  {
    if ((Math.abs(value) > Math.abs(windowValue) - delta) && (Math.abs(value) < Math.abs(windowValue) + delta)) {
      return windowValue;
    }
    return value;
  }
}
