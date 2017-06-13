package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;




























public class SetMotionTimeoutCommand
  extends DeviceCommand
{
  private final int a;
  
  public SetMotionTimeoutCommand(int timeBeforeTimeout)
  {
    a = timeBeforeTimeout;
  }
  




  public float getTimeBeforeTimeout()
  {
    return a;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.SET_MOTION_TIMEOUT.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[0] = ((byte)(a >> 8));
    arrayOfByte[1] = ((byte)a);
    return arrayOfByte;
  }
}
