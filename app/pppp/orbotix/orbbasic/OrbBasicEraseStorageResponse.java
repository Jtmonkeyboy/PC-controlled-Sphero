package com.orbotix.orbbasic;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;





public class OrbBasicEraseStorageResponse
  extends DeviceResponse
{
  public OrbBasicEraseStorageResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
