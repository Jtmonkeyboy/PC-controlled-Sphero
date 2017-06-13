package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;





public class SetUserHackModeResponse
  extends DeviceResponse
{
  public SetUserHackModeResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
