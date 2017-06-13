package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;

public class Level1DiagnosticsResponse extends DeviceResponse
{
  protected Level1DiagnosticsResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
