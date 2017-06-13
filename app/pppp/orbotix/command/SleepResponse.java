package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;

public class SleepResponse extends DeviceResponse
{
  protected SleepResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
