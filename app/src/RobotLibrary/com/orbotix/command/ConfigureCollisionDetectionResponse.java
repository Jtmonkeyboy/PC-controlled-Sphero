package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;




public class ConfigureCollisionDetectionResponse
  extends DeviceResponse
{
  protected ConfigureCollisionDetectionResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
