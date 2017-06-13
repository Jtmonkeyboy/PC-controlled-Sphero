package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;


public class RGB
  implements MacroCommand
{
  private static final String a = "RGB";
  private int b = 0;
  private int c = 255;
  private int d = 255;
  private int e = 255;
  




  public int getDelay()
  {
    return b;
  }
  




  public void setDelay(int _delay)
  {
    b = (_delay & 0xFF);
  }
  




  public int[] getColor()
  {
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = c;
    arrayOfInt[1] = d;
    arrayOfInt[2] = e;
    return arrayOfInt;
  }
  






  public void setColor(int _red, int _green, int _blue)
  {
    c = (_red & 0xFF);
    e = (_blue & 0xFF);
    d = (_green & 0xFF);
  }
  




  public int getLength()
  {
    return 5;
  }
  




  public byte[] getByteRepresentation()
  {
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(getLength());
    localByteArrayBuffer.append(getCommandID());
    localByteArrayBuffer.append(c);
    localByteArrayBuffer.append(d);
    localByteArrayBuffer.append(e);
    localByteArrayBuffer.append(b);
    return localByteArrayBuffer.toByteArray();
  }
  
  public String getName()
  {
    return "RGB";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return c + " " + d + " " + e + " " + b;
  }
  




  public RGB(byte[] data)
  {
    setColor(ByteUtil.convertUnsignedToInt(data[1]), ByteUtil.convertUnsignedToInt(data[2]), ByteUtil.convertUnsignedToInt(data[3]));
    setDelay(ByteUtil.convertUnsignedToInt(data[4]));
  }
  
  public RGB(Integer _red, Integer _green, Integer _blue, Integer _delay)
  {
    setColor(_red.intValue(), _green.intValue(), _blue.intValue());
    setDelay(_delay.intValue());
  }
  




  public static byte getCommandID()
  {
    return 7;
  }
}
