package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;


public final class JumpToMainCommand
  extends DeviceCommand
{
  public JumpToMainCommand() {}
  
  public byte getDeviceId()
  {
    return DeviceId.BOOTLOADER.getValue();
  }
  
  public byte getCommandId()
  {
    return 4;
  }
}
