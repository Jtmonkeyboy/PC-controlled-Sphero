package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;




public final class PingResponse
  extends DeviceResponse
{
  protected PingResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
