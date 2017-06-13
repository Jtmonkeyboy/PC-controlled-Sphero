package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;

public class GetSkuCommand extends DeviceCommand {
  public GetSkuCommand() {}
  
  public byte getDeviceId() {
    return 2;
  }
  
  public byte getCommandId()
  {
    return 58;
  }
}
