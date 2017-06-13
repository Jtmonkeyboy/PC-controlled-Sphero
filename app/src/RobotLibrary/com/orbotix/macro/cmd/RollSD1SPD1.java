package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;




public class RollSD1SPD1
  implements MacroCommand
{
  public static final int ID = 17;
  private static final String a = "Roll Saved Delay 1, Saved Speed 1";
  private Integer b = Integer.valueOf(0);
  




  public Integer getHeading()
  {
    return b;
  }
  




  public void setHeading(Integer _heading)
  {
    if ((_heading.intValue() >= 0) && (_heading.intValue() <= 359)) {
      b = _heading;
    }
  }
  




  public int getLength()
  {
    return 3;
  }
  




  public byte[] getByteRepresentation()
  {
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(getLength());
    localByteArrayBuffer.append(getCommandID());
    localByteArrayBuffer.append(b.intValue() >> 8);
    localByteArrayBuffer.append(b.intValue() & 0xFF);
    return localByteArrayBuffer.toByteArray();
  }
  
  public String getName()
  {
    return "Roll Saved Delay 1, Saved Speed 1";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return new Integer(b.intValue()).toString();
  }
  




  public RollSD1SPD1(byte[] data)
  {
    b = Integer.valueOf(ByteUtil.convertUnsignedToInt(data[1], data[2]));
  }
  
  public RollSD1SPD1(Integer _heading)
  {
    setHeading(_heading);
  }
  




  public static byte getCommandID()
  {
    return 17;
  }
}
