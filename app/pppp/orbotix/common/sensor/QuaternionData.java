package com.orbotix.common.sensor;
















@Deprecated
public class QuaternionData
{
  QuaternionSensor a;
  















  public QuaternionData(QuaternionSensor quaternions)
  {
    a = quaternions;
  }
  




  public QuaternionSensor getQuaternions()
  {
    return a;
  }
  




  public float getQ0()
  {
    return a.q0;
  }
  




  public float getQ1()
  {
    return a.q1;
  }
  




  public float getQ2()
  {
    return a.q2;
  }
  




  public float getQ3()
  {
    return a.q3;
  }
}
