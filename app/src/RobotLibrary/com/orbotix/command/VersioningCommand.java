package com.orbotix.command;

import com.orbotix.common.internal.DeviceId;

public final class VersioningCommand extends com.orbotix.common.internal.DeviceCommand
{
  public VersioningCommand() {}
  
  public byte getDeviceId() {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId()
  {
    return com.orbotix.common.internal.CoreCommandId.VERSIONING.getValue();
  }
}
