package com.orbotix.macro;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;




public class SaveTemporaryMacroChunkResponse
  extends DeviceResponse
{
  public SaveTemporaryMacroChunkResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
