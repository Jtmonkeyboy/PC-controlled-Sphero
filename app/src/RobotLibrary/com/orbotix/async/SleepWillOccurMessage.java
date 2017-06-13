package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;

public final class SleepWillOccurMessage extends AsyncMessage
{
  protected SleepWillOccurMessage(byte[] packet) {
    super(packet);
  }
}
