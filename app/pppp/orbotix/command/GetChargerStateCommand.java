package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;

public class GetChargerStateCommand extends DeviceCommand {
  public GetChargerStateCommand() {}
  
  public byte getDeviceId() { return 0; }
  

  public byte getCommandId()
  {
    return 38;
  }
}
