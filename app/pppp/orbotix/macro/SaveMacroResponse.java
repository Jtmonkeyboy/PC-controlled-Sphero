package com.orbotix.macro;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;



public class SaveMacroResponse
  extends DeviceResponse
{
  public SaveMacroResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
