package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;




public class RawMotorResponse
  extends DeviceResponse
{
  protected RawMotorResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
