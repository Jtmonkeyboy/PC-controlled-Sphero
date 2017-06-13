package com.orbotix.command;

import com.orbotix.common.internal.CoreCommandId;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;


public final class PingCommand
  extends DeviceCommand
{
  public PingCommand()
  {
    setKeepAlive(false);
  }
  
  public byte getDeviceId() {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId() { return CoreCommandId.PING.getValue(); }
}
