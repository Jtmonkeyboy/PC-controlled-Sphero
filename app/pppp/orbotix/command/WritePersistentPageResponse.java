package com.orbotix.command;

import com.orbotix.common.internal.DeviceResponse;

public class WritePersistentPageResponse extends DeviceResponse
{
  protected WritePersistentPageResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command) {
    super(packet, command);
  }
}
