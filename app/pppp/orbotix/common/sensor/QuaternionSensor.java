package com.orbotix.common.sensor;


public class QuaternionSensor
  extends a
{
  private static final float a = 10000.0F;
  
  public float q0;
  
  public float q1;
  public float q2;
  public float q3;
  
  public QuaternionSensor() {}
  
  public static float normalize(int quaternion)
  {
    return quaternion / 10000.0F;
  }
  
  public float getQ0() {
    return q0;
  }
  
  public float getQ1() {
    return q1;
  }
  
  public float getQ2() {
    return q2;
  }
  
  public float getQ3() {
    return q3;
  }
  
  public String toString()
  {
    return "Quaternion{q0=" + q0 + ", q1=" + q1 + ", q2=" + q2 + ", q3=" + q3 + '}';
  }
}
