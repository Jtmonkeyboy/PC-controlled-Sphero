package com.orbotix.command;

import com.orbotix.common.internal.CoreCommandId;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;

























public class SetInactivityTimeoutCommand
  extends DeviceCommand
{
  private static final int a = 60;
  private final int b;
  
  public SetInactivityTimeoutCommand(int inactivityTimeout)
  {
    if (inactivityTimeout < 60) inactivityTimeout = 60;
    b = inactivityTimeout;
  }
  
  public int getInactivityTimeout()
  {
    return b;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId()
  {
    return CoreCommandId.SET_INACTIVITY_TIMEOUT.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[2];
    
    arrayOfByte[0] = ((byte)(b >> 8));
    arrayOfByte[1] = ((byte)b);
    
    return arrayOfByte;
  }
}
