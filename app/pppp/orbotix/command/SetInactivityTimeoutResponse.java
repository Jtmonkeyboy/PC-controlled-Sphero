package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;





public class SetInactivityTimeoutResponse
  extends DeviceResponse
{
  protected SetInactivityTimeoutResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
