package com.orbotix.common.sensor;

import com.orbotix.common.utilities.binary.Maskable;


































public enum SensorFlag
  implements Maskable
{
  private final long a;
  private final float b;
  
  private SensorFlag(long flag)
  {
    a = flag;
    b = 0.9F;
  }
  
  private SensorFlag(long flag, float minFirmwareVersion) {
    a = flag;
    b = minFirmwareVersion;
  }
  
  public long longValue() {
    return a;
  }
}
