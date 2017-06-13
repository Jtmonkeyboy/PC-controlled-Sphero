package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;





public final class JumpToBootloaderResponse
  extends DeviceResponse
{
  protected JumpToBootloaderResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
