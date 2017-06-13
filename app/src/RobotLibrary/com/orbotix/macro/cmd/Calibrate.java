package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;



public class Calibrate
  implements MacroCommand
{
  public static final byte COMMAND_ID = 4;
  private static final String a = "Set Orientation";
  private int b = 0;
  private int c = 0;
  
  public Calibrate(int heading, int delay) {
    setHeading(heading);
    setDelay(delay);
  }
  
  public Calibrate(byte[] bytes) {
    setHeading(ByteUtil.convertUnsignedToInt(bytes[1], bytes[2]));
    setDelay(bytes[3]);
  }
  
  public int getHeading() {
    return b;
  }
  
  public void setHeading(int heading) {
    b = heading;
  }
  
  public void setDelay(int delay) {
    c = (delay & 0xFF);
  }
  
  public int getDelay() {
    return c;
  }
  
  public int getLength()
  {
    return 4;
  }
  
  public byte[] getByteRepresentation()
  {
    byte[] arrayOfByte = new byte[4];
    
    arrayOfByte[0] = getCommandId();
    arrayOfByte[1] = ((byte)(b >> 8));
    arrayOfByte[2] = ((byte)(b & 0xFF));
    arrayOfByte[3] = ((byte)c);
    
    return arrayOfByte;
  }
  
  public String getName()
  {
    return "Set Orientation";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 4;
  }
  
  public String getSettingsString()
  {
    return new Integer(b).toString();
  }
}
