package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;



public class RGBSD2
  implements MacroCommand
{
  private static final String a = "RGB Saved Delay 2";
  private int b = 255;
  private int c = 255;
  private int d = 255;
  




  public int[] getColor()
  {
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = b;
    arrayOfInt[1] = c;
    arrayOfInt[2] = d;
    return arrayOfInt;
  }
  






  public void setColor(int _red, int _green, int _blue)
  {
    if ((_red >= 0) && (_red <= 255)) {
      b = _red;
    }
    if ((_blue >= 0) && (_blue <= 255)) {
      d = _blue;
    }
    if ((_green >= 0) && (_green <= 255)) {
      c = _green;
    }
  }
  




  public int getLength()
  {
    return 4;
  }
  




  public byte[] getByteRepresentation()
  {
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(getLength());
    localByteArrayBuffer.append(getCommandID());
    localByteArrayBuffer.append(b);
    localByteArrayBuffer.append(c);
    localByteArrayBuffer.append(d);
    return localByteArrayBuffer.toByteArray();
  }
  
  public String getName()
  {
    return "RGB Saved Delay 2";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return b + " " + c + " " + d;
  }
  




  public RGBSD2(byte[] data)
  {
    setColor(ByteUtil.convertUnsignedToInt(data[1]), ByteUtil.convertUnsignedToInt(data[2]), ByteUtil.convertUnsignedToInt(data[3]));
  }
  
  public RGBSD2(Integer _red, Integer _green, Integer _blue)
  {
    setColor(_red.intValue(), _green.intValue(), _blue.intValue());
  }
  




  public static byte getCommandID()
  {
    return 8;
  }
}
