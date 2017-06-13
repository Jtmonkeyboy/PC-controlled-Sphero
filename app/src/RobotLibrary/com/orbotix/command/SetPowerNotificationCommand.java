package com.orbotix.command;

import com.orbotix.common.internal.CoreCommandId;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
















public class SetPowerNotificationCommand
  extends DeviceCommand
{
  private final boolean a;
  
  public SetPowerNotificationCommand(boolean notificationState)
  {
    a = notificationState;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.CORE.getValue();
  }
  

  public byte getCommandId()
  {
    return CoreCommandId.SET_POWER_NOTIFICATION.getValue();
  }
}
