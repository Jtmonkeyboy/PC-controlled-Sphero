package com.orbotix.command;

import com.orbotix.common.internal.DeviceId;

public class Level1DiagnosticsCommand extends com.orbotix.common.internal.DeviceCommand
{
  public Level1DiagnosticsCommand() {}
  
  public byte getDeviceId() {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId()
  {
    return com.orbotix.common.internal.CoreCommandId.LEVEL_1_DIAGNOSTICS.getValue();
  }
}
