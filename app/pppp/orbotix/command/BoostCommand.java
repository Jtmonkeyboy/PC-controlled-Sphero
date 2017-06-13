package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.common.utilities.math.Value;

public final class BoostCommand extends DeviceCommand
{
  private final float a;
  private final float b;
  
  public BoostCommand(float time, float heading)
  {
    a = ((float)Value.clamp(time, 0.0D, 25.5D));
    b = ((int)heading % 360);
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.BOOST.getValue();
  }
  

  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[3];
    
    arrayOfByte[0] = ((byte)(int)(a * 10.0D));
    arrayOfByte[1] = ((byte)((int)b >> 8));
    arrayOfByte[2] = ((byte)(int)b);
    
    return arrayOfByte;
  }
}
