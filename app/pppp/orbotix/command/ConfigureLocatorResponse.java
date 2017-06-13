package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;





public class ConfigureLocatorResponse
  extends DeviceResponse
{
  protected ConfigureLocatorResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
