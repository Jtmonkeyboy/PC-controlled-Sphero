package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;




public class RotateOverTimeSD1
  implements MacroCommand
{
  public static final byte COMMAND_ID = 33;
  private static final String a = "Rotate Over Time, Saved Delay 1";
  private short b = 0;
  
  public RotateOverTimeSD1(int angle) {
    setAngle(angle);
  }
  
  public RotateOverTimeSD1(byte[] bytes) {
    int i = bytes[1] >> 7 == 1 ? 1 : 0;
    int j = bytes[1] << 1 >> 1;
    
    int k = j << 8 | ByteUtil.convertUnsignedToInt(bytes[2]);
    k = i != 0 ? k : k * -1;
    
    setAngle(k);
  }
  
  public void setAngle(int angle) {
    b = ((short)angle);
  }
  
  public short getAngle() {
    return b;
  }
  
  public int getLength()
  {
    return 3;
  }
  

  public byte[] getByteRepresentation()
  {
    return new byte[] {getCommandId(), (byte)(b >> 8), (byte)(b & 0xFF) };
  }
  



  public String getName()
  {
    return "Rotate Over Time, Saved Delay 1";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 33;
  }
  
  public String getSettingsString()
  {
    return new Short(b).toString();
  }
}
