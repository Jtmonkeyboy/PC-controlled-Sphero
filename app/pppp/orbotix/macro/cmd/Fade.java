package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;



public class Fade
  implements MacroCommand
{
  public static final byte COMMAND_ID = 20;
  private static final String a = "Fade";
  private int b = 0;
  private int c = 0;
  private int d = 0;
  private int e = 0;
  
  public Fade(int red, int green, int blue, int durration) {
    setColor(red, green, blue);
    setDuration(durration);
  }
  
  public Fade(byte[] bytes) {
    setColor(
      ByteUtil.convertUnsignedToInt(bytes[1]), 
      ByteUtil.convertUnsignedToInt(bytes[2]), 
      ByteUtil.convertUnsignedToInt(bytes[3]));
    
    setDuration(ByteUtil.convertUnsignedToInt(bytes[4], bytes[5]));
  }
  
  public void setColor(int red, int green, int blue) {
    b = (red & 0xFF);
    c = (green & 0xFF);
    d = (blue & 0xFF);
  }
  
  public int[] getColor() {
    return new int[] { b, c, d };
  }
  
  public void setDuration(int duration) {
    e = (duration & 0xFFFF);
  }
  
  public int getDuration() {
    return e;
  }
  
  public int getLength()
  {
    return 6;
  }
  

  public byte[] getByteRepresentation()
  {
    byte[] arrayOfByte = { getCommandId(), (byte)b, (byte)c, (byte)d, (byte)(e >> 8), (byte)(e & 0xFF) };
    





    return arrayOfByte;
  }
  
  public String getName()
  {
    return "Fade";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 20;
  }
  
  public String getSettingsString()
  {
    return b + " " + c + " " + d + " " + e;
  }
}
