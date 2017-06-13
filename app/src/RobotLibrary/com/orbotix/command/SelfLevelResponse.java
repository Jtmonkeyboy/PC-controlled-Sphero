package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;




public class SelfLevelResponse
  extends DeviceResponse
{
  protected SelfLevelResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
