package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;











public class SetUserHackModeCommand
  extends DeviceCommand
{
  public static final boolean DeviceModeNormal = false;
  public static final boolean DeviceModeUserHack = true;
  private final boolean a;
  
  public SetUserHackModeCommand(boolean mode)
  {
    a = mode;
  }
  
  public boolean getDeviceMode()
  {
    return a;
  }
  
  public byte getDeviceId() {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId() { return RobotCommandId.SET_DEVICE_MODE.getValue(); }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = ((byte)(a ? 1 : 0));
    return arrayOfByte;
  }
}
