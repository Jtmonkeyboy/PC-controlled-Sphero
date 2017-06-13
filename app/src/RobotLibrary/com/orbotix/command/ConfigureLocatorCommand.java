package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
































public class ConfigureLocatorCommand
  extends DeviceCommand
{
  public static final int ROTATE_WITH_CALIBRATE_FLAG_OFF = 0;
  public static final int ROTATE_WITH_CALIBRATE_FLAG_ON = 1;
  private final int a;
  private final int b;
  private final int c;
  private final int d;
  
  public ConfigureLocatorCommand(int flag, int newX, int newY, int newYaw)
  {
    a = flag;
    b = newX;
    c = newY;
    d = newYaw;
  }
  




  public int getFlag()
  {
    return a;
  }
  




  public int getNewPositionX()
  {
    return b;
  }
  




  public int getNewPositionY()
  {
    return c;
  }
  




  public int getNewYawTare()
  {
    return d;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.CONFIGURE_LOCATOR.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[7];
    
    arrayOfByte[0] = ((byte)a);
    arrayOfByte[1] = ((byte)(b >> 8));
    arrayOfByte[2] = ((byte)b);
    arrayOfByte[3] = ((byte)(c >> 8));
    arrayOfByte[4] = ((byte)c);
    arrayOfByte[5] = ((byte)(d >> 8));
    arrayOfByte[6] = ((byte)d);
    
    return arrayOfByte;
  }
}
