package com.orbotix.macro;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;

























public class SaveMacroCommand
  extends DeviceCommand
{
  public static final byte MacroFlagNone = 0;
  public static final byte MacroFlagMotorControl = 1;
  public static final byte MacroFlagExclusiveDrive = 2;
  public static final byte MacroFlagUseVersion3 = 4;
  public static final byte MacroFlagInhibitIfConnected = 8;
  public static final byte MacroFlagEndMarker = 16;
  public static final byte MacroFlagStealth = 32;
  public static final byte MacroFlagUnkillable = 64;
  public static final byte MacroFlagExtendedFlags = -128;
  public static final byte MacroStreamingDestination = -2;
  private final byte[] a;
  private final byte b;
  private final byte c;
  
  public SaveMacroCommand(byte flags, byte[] macro, byte destination)
  {
    b = flags;
    a = macro;
    c = destination;
  }
  



  public byte getFlags()
  {
    return b;
  }
  



  public byte[] getMacro()
  {
    return a;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.SAVE_MACRO.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[a.length + 2];
    
    arrayOfByte[0] = c;
    arrayOfByte[1] = b;
    for (int i = 0; i < a.length; i++) {
      arrayOfByte[(i + 2)] = a[i];
    }
    
    return arrayOfByte;
  }
}
