package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;

public class GetChassisIdCommand extends DeviceCommand {
  public GetChassisIdCommand() {}
  
  public byte getDeviceId() { return 2; }
  

  public byte getCommandId()
  {
    return 7;
  }
}
