package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;



public class RotateOverTime
  implements MacroCommand
{
  public static final byte COMMAND_ID = 26;
  private static final String a = "Rotate Over Time";
  private short b = 0;
  private int c = 0;
  
  public RotateOverTime(int angle, int duration) {
    setAngle(angle);
    setDuration(duration);
  }
  
  public RotateOverTime(byte[] bytes) {
    int i = bytes[1] >> 7 == 1 ? 1 : 0;
    int j = bytes[1] << 1 >> 1;
    
    int k = j << 8 | ByteUtil.convertUnsignedToInt(bytes[2]);
    k = i != 0 ? k * -1 : k;
    
    setAngle(k);
    setDuration(ByteUtil.convertUnsignedToInt(bytes[3], bytes[4]));
  }
  
  public void setAngle(int angle) {
    b = ((short)angle);
  }
  
  public short getAngle() {
    return b;
  }
  
  public void setDuration(int duration) {
    c = (duration & 0xFFFF);
  }
  
  public int getDuration() {
    return c;
  }
  
  public int getLength()
  {
    return 5;
  }
  

  public byte[] getByteRepresentation()
  {
    return new byte[] {getCommandID(), (byte)(b >> 8), (byte)(b & 0xFF), (byte)(c >> 8), (byte)(c & 0xFF) };
  }
  





  public String getName()
  {
    return "Rotate Over Time";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 26;
  }
  
  public String getSettingsString()
  {
    return b + " " + c;
  }
}
