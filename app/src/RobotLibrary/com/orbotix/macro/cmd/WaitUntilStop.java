package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;



public class WaitUntilStop
  implements MacroCommand
{
  private static final String a = "Wait Until Stopped";
  private int b = 0;
  
  public WaitUntilStop(int delay)
  {
    b = delay;
  }
  
  public int getLength()
  {
    return 3;
  }
  
  public byte[] getByteRepresentation()
  {
    byte[] arrayOfByte = new byte[3];
    arrayOfByte[0] = 25;
    arrayOfByte[1] = ((byte)(b >> 8));
    arrayOfByte[2] = ((byte)(b & 0xFF));
    
    return arrayOfByte;
  }
  
  public String getName()
  {
    return "Wait Until Stopped";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return Integer.toString(b);
  }
  
  public void setDelay(int delay) {
    b = (delay & 0xFFFF);
  }
  
  public int getDelay() {
    return b;
  }
  
  public WaitUntilStop(byte[] data) {
    b = ByteUtil.convertUnsignedToInt(data[1], data[2]);
  }
  
  public static byte getCommandID() {
    return 25;
  }
}
