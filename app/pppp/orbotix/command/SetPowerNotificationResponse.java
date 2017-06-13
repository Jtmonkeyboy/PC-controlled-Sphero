package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;






public class SetPowerNotificationResponse
  extends DeviceResponse
{
  protected SetPowerNotificationResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
