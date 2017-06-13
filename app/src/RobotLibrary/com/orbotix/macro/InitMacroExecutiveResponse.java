package com.orbotix.macro;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;


public class InitMacroExecutiveResponse
  extends DeviceResponse
{
  public InitMacroExecutiveResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
