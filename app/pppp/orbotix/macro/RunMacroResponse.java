package com.orbotix.macro;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;









public class RunMacroResponse
  extends DeviceResponse
{
  public RunMacroResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
    if (getResponseCode() == ResponseCode.OK) {}
  }
}
