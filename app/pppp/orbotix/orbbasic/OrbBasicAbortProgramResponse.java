package com.orbotix.orbbasic;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;













public class OrbBasicAbortProgramResponse
  extends DeviceResponse
{
  public OrbBasicAbortProgramResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
