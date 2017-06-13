package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;































































public class SelfLevelCommand
  extends DeviceCommand
{
  public static final int OPTION_START = 1;
  public static final int OPTION_KEEP_HEADING = 2;
  public static final int OPTION_SLEEP_AFTER = 4;
  public static final int OPTION_CONTROL_SYSTEM_ON = 8;
  private final int a;
  private final int b;
  private final int c;
  private final int d;
  
  public SelfLevelCommand(int options, int angleLimit, int timeout, int accuracy)
  {
    a = options;
    b = angleLimit;
    c = timeout;
    d = accuracy;
  }
  





  public int getOptions()
  {
    return a;
  }
  




  public int getAngleLimit()
  {
    return b;
  }
  




  public int getTimeout()
  {
    return c;
  }
  




  public int getAccuracy()
  {
    return d;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.SELF_LEVEL.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[4];
    
    arrayOfByte[0] = ((byte)a);
    arrayOfByte[1] = ((byte)b);
    arrayOfByte[2] = ((byte)c);
    arrayOfByte[3] = ((byte)d);
    
    return arrayOfByte;
  }
}
