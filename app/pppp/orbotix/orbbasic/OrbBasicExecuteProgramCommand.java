package com.orbotix.orbbasic;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;















public class OrbBasicExecuteProgramCommand
  extends DeviceCommand
{
  private final boolean a;
  private final int b;
  
  public OrbBasicExecuteProgramCommand(boolean storageType, int startingLine)
  {
    a = storageType;
    b = startingLine;
  }
  

  public boolean getStorageType()
  {
    return a;
  }
  
  public int getStartingLine()
  {
    return b;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.ORB_BASIC_EXECUTE_PROGRAM.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[3];
    
    arrayOfByte[0] = ((byte)(a ? 1 : 0));
    arrayOfByte[1] = ((byte)(b >> 8));
    arrayOfByte[2] = ((byte)b);
    
    return arrayOfByte;
  }
}
