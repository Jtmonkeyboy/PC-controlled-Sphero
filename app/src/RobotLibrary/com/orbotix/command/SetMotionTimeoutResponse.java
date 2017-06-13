package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;

public class SetMotionTimeoutResponse extends DeviceResponse
{
  protected SetMotionTimeoutResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
