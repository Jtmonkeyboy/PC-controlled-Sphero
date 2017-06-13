package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;






public class SetOptionFlagsResponse
  extends DeviceResponse
{
  protected SetOptionFlagsResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
