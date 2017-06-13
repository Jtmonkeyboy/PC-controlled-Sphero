package com.orbotix.orbbasic;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;





















public class OrbBasicAppendFragmentCommand
  extends DeviceCommand
{
  private final boolean a;
  private final byte[] b;
  
  public OrbBasicAppendFragmentCommand(boolean storageType, byte[] fragment)
  {
    a = storageType;
    b = fragment;
  }
  
  public boolean getStorageType()
  {
    return a;
  }
  
  public byte[] getFragment()
  {
    return b;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.ORB_BASIC_APPEND_FRAGMENT.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[1 + b.length];
    
    arrayOfByte[0] = ((byte)(a ? 1 : 0));
    

    for (int i = 0; i < b.length; i++) {
      arrayOfByte[(i + 1)] = b[i];
    }
    
    return arrayOfByte;
  }
}
