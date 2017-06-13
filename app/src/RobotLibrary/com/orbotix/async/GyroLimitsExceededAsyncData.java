package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;

public class GyroLimitsExceededAsyncData extends AsyncMessage
{
  protected GyroLimitsExceededAsyncData(byte[] packet)
  {
    super(packet);
    com.orbotix.common.DLog.v("Hooray you exceeded the gyro limits!");
  }
}
