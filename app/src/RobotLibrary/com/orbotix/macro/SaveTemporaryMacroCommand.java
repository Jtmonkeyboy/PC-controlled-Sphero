package com.orbotix.macro;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;

public class SaveTemporaryMacroCommand
  extends DeviceCommand
{
  protected final byte[] macroData;
  private final byte a;
  
  public SaveTemporaryMacroCommand(byte flags, byte[] macro)
  {
    a = flags;
    macroData = macro;
  }
  
  public byte getFlags() {
    return a;
  }
  
  public byte[] getMacro() {
    return macroData;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.SAVE_TEMPORARY_MACRO.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[macroData.length + 2];
    
    arrayOfByte[0] = -1;
    arrayOfByte[1] = a;
    System.arraycopy(macroData, 0, arrayOfByte, 2, macroData.length);
    
    return arrayOfByte;
  }
}
