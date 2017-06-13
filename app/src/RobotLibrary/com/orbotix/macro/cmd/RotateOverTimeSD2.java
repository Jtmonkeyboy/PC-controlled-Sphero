package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;



public class RotateOverTimeSD2
  implements MacroCommand
{
  public static final byte COMMAND_ID = 34;
  private static final String a = "Rotate Over Time, Saved Delay 2";
  private short b = 0;
  private static byte c;
  
  public RotateOverTimeSD2(int angle) {
    setAngle(angle);
  }
  
  public RotateOverTimeSD2(byte[] bytes) {
    int i = bytes[1] >> 7 == 1 ? 1 : 0;
    int j = bytes[1] << 1 >> 1;
    
    int k = j << 8 | ByteUtil.convertUnsignedToInt(bytes[2]);
    k = i != 0 ? k : k * -1;
    
    setAngle(k);
  }
  
  public static byte getCommandID() {
    return 34;
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
    return "Rotate Over Time, Saved Delay 2";
  }
  
  public byte getCommandId()
  {
    return 34;
  }
  
  public String getSettingsString()
  {
    return new Short(b).toString();
  }
}
