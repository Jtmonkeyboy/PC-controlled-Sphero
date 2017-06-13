package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.utilities.math.Value;














public final class RotationRateCommand
  extends DeviceCommand
{
  public static final byte COMMAND_ID = 3;
  private final float a;
  
  public RotationRateCommand(float rate)
  {
    if ((rate < 0.0F) || (rate > 1.0F)) {
      throw new IllegalArgumentException("Expects Rate from 0.0 to 1.0 inclusive");
    }
    a = ((float)Value.clamp(rate, 0.0D, 1.0D));
  }
  




  public float getRate()
  {
    return a;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  

  public byte getCommandId()
  {
    return 3;
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = ((byte)(int)(a * 255.0D));
    return arrayOfByte;
  }
}
