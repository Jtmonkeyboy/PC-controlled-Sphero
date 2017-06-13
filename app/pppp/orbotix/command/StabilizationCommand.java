package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;

public final class StabilizationCommand extends DeviceCommand
{
  private final boolean a;
  
  public StabilizationCommand(boolean on)
  {
    a = on;
  }
  
  public boolean on() {
    return a;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.STABILIZATION.getValue();
  }
  

  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = ((byte)(a ? 1 : 0));
    return arrayOfByte;
  }
}
