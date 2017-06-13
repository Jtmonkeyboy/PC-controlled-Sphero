package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;


public class GetDeviceModeCommand
  extends DeviceCommand
{
  public GetDeviceModeCommand() {}
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId() { return RobotCommandId.GET_DEVICE_MODE.getValue(); }
}
