package com.orbotix.orbbasic;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;





public class OrbBasicAppendFragmentResponse
  extends DeviceResponse
{
  public OrbBasicAppendFragmentResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
