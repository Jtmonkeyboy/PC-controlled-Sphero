package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;















public class ControlSystemPresetCommand
  extends DeviceCommand
{
  private byte a;
  
  public ControlSystemPresetCommand(byte presetID)
  {
    a = presetID;
  }
  

  public byte getDeviceId()
  {
    return 2;
  }
  
  public byte getCommandId()
  {
    return 116;
  }
  

  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[1];
    
    arrayOfByte[0] = a;
    
    return arrayOfByte;
  }
}
