package com.orbotix.ovalcompiler.command;

import com.orbotix.common.internal.DeviceCommand;

public class ResetOvmCommand extends DeviceCommand {
  private boolean a;
  
  public ResetOvmCommand(boolean resetLibrary) {
    a = resetLibrary;
  }
  
  public byte getDeviceId()
  {
    return 2;
  }
  
  public byte getCommandId()
  {
    return -127;
  }
  
  public byte[] getData()
  {
    return new byte[] { (byte)(a ? 1 : 0) };
  }
}
