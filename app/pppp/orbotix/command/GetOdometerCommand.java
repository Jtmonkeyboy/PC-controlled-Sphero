package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;

public class GetOdometerCommand extends DeviceCommand {
  public GetOdometerCommand() {}
  
  public byte getDeviceId() { return 2; }
  
  public byte getCommandId()
  {
    return 117;
  }
}
