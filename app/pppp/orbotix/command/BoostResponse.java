package com.orbotix.command;

import com.orbotix.common.internal.DeviceResponse;

public final class BoostResponse extends DeviceResponse
{
  protected BoostResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command)
  {
    super(packet, command);
  }
}
