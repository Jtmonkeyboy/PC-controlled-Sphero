package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;




public final class BackLEDOutputResponse
  extends DeviceResponse
{
  public BackLEDOutputResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
