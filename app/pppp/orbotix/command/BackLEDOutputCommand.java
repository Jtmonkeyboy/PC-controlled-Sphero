package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.common.utilities.math.Value;












public final class BackLEDOutputCommand
  extends DeviceCommand
{
  private final float a;
  
  public BackLEDOutputCommand(float brightness)
  {
    a = ((float)Value.clamp(brightness, 0.0D, 1.0D));
  }
  




  public float getBrightness()
  {
    return a;
  }
  
  public byte getDeviceId() { return DeviceId.ROBOT.getValue(); }
  public byte getCommandId() { return RobotCommandId.BACK_LED_OUTPUT.getValue(); }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = ((byte)(int)(255.0D * a));
    return arrayOfByte;
  }
}
