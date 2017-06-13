package com.orbotix.ovalcompiler.response;

import com.orbotix.common.internal.DeviceResponse;

public class ResetOvmResponse extends DeviceResponse
{
  protected ResetOvmResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command) {
    super(packet, command);
  }
}
