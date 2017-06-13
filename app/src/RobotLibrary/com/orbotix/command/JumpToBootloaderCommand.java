package com.orbotix.command;

import com.orbotix.common.internal.CoreCommandId;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;



public final class JumpToBootloaderCommand
  extends DeviceCommand
{
  public JumpToBootloaderCommand() {}
  
  public byte getDeviceId()
  {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId()
  {
    return CoreCommandId.JUMP_TO_BOOTLOADER.getValue();
  }
}
