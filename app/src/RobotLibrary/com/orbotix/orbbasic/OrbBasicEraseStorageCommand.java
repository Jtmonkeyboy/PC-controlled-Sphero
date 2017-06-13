package com.orbotix.orbbasic;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;












public class OrbBasicEraseStorageCommand
  extends DeviceCommand
{
  private final boolean a;
  
  public OrbBasicEraseStorageCommand(boolean storageType)
  {
    a = storageType;
  }
  
  public boolean getStorageType()
  {
    return a;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.ORB_BASIC_ERASE_STORAGE.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[1];
    
    arrayOfByte[0] = ((byte)(a ? 1 : 0));
    
    return arrayOfByte;
  }
}
