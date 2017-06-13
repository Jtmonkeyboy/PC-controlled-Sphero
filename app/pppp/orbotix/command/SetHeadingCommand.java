package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;













public final class SetHeadingCommand
  extends DeviceCommand
{
  private final float a;
  
  public SetHeadingCommand(float heading)
  {
    a = heading;
  }
  




  public float getHeading()
  {
    return a;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  




  public byte getCommandId()
  {
    return RobotCommandId.SET_HEADING.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[2];
    
    arrayOfByte[0] = ((byte)((int)a >> 8));
    arrayOfByte[1] = ((byte)(int)a);
    
    return arrayOfByte;
  }
}
