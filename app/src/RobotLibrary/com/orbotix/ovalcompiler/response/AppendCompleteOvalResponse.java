package com.orbotix.ovalcompiler.response;

import com.orbotix.common.internal.DeviceResponse;

public class AppendCompleteOvalResponse extends DeviceResponse
{
  protected AppendCompleteOvalResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command) {
    super(packet, command);
  }
}
