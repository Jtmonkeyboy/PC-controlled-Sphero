package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;



public class NonPersistentSetOptionFlagsResponse
  extends DeviceResponse
{
  protected NonPersistentSetOptionFlagsResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
