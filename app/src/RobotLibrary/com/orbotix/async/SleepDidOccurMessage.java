package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;


public class SleepDidOccurMessage
  extends AsyncMessage
{
  protected SleepDidOccurMessage(byte[] packet)
  {
    super(packet);
  }
}
