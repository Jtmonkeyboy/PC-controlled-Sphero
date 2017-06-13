package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;




public class SetDataStreamingResponse
  extends DeviceResponse
{
  protected SetDataStreamingResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
}
