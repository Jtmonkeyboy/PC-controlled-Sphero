package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;













public class SleepCommand
  extends DeviceCommand
{
  public static final byte COMMAND_ID = 34;
  public static final int DEEP_SLEEP_INTERVAL = 65535;
  private final int a;
  private final int b;
  
  public SleepCommand(int time, int identifier)
  {
    a = time;
    b = identifier;
    setResponseRequested(true);
  }
  
  public SleepCommand() {
    this(0, 0);
  }
  




  public int getWakeUpTime()
  {
    return a;
  }
  




  public int getWakeUpMacroId()
  {
    return b;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId()
  {
    return 34;
  }
  

  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[3];
    
    arrayOfByte[0] = ((byte)(a >> 8));
    arrayOfByte[1] = ((byte)a);
    arrayOfByte[2] = ((byte)b);
    
    return arrayOfByte;
  }
  

  public static enum SleepType
  {
    private final byte a;
    
    private SleepType(int value)
    {
      a = ((byte)value);
    }
    
    public static SleepType sleepTypeForByte(byte b) {
      switch (b) {
      case 0: 
        return NORMAL;
      case 1: 
        return DEEP;
      }
      throw new IllegalArgumentException(String.format("Byte %d is not a valid value for SleepType", new Object[] { Byte.valueOf(b) }));
    }
  }
}
