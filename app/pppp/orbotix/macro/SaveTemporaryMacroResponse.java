package com.orbotix.macro;

import com.orbotix.common.internal.DeviceResponse;

public class SaveTemporaryMacroResponse extends DeviceResponse
{
  public SaveTemporaryMacroResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command)
  {
    super(packet, command);
  }
}
