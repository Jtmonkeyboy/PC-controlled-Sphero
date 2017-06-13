package com.orbotix.orbbasic;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;





public class OrbBasicExecuteProgramResponse
  extends DeviceResponse
{
  public OrbBasicExecuteProgramResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
