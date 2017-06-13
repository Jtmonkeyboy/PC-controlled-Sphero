package com.orbotix.common.sensor;



public class BackEMFData
  extends a
{
  BackEMFSensor a;
  

  public BackEMFData(BackEMFSensor filteredSensor)
  {
    a = filteredSensor;
  }
  
  public BackEMFSensor getEMFFiltered() {
    return a;
  }
  
  public String toString() {
    StringBuilder localStringBuilder = new StringBuilder("[BackEMF");
    if (a != null) localStringBuilder.append(" f=").append(a).append(" : ");
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
