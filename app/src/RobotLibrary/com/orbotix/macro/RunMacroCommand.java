package com.orbotix.macro;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;













public class RunMacroCommand
  extends DeviceCommand
{
  public static final byte TEMPORARY_MACRO_ID = -1;
  private final byte a;
  
  public RunMacroCommand(byte identifier)
  {
    a = identifier;
  }
  




  public byte getMacroId()
  {
    return a;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.RUN_MACRO.getValue();
  }
  





  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = a;
    return arrayOfByte;
  }
}
