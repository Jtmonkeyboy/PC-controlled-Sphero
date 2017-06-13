package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;





public final class JumpToMainResponse
  extends DeviceResponse
{
  protected JumpToMainResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
