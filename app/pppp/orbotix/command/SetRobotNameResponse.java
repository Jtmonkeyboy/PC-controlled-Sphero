package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;

public class SetRobotNameResponse extends DeviceResponse
{
  protected SetRobotNameResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
