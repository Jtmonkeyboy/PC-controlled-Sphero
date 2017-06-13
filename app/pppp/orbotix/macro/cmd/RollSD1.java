package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;




public class RollSD1
  implements MacroCommand
{
  public static final byte ID = 6;
  private static final String a = "Roll Saved Delay 1";
  private int b = 0;
  private float c = 0.5F;
  




  public int getHeading()
  {
    return b;
  }
  




  public void setHeading(int _heading)
  {
    if ((_heading >= 0) && (_heading <= 359)) {
      b = _heading;
    }
  }
  




  public float getSpeed()
  {
    return c;
  }
  




  public void setSpeed(float _speed)
  {
    c = (_speed < 0.0F ? 0.0F : _speed);
    c = (c > 1.0F ? 1.0F : c);
  }
  




  public int getLength()
  {
    return 4;
  }
  




  public byte[] getByteRepresentation()
  {
    Integer localInteger = Integer.valueOf((int)(c * 255.0D));
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(getLength());
    localByteArrayBuffer.append(getCommandID());
    localByteArrayBuffer.append(localInteger.intValue());
    localByteArrayBuffer.append(b >> 8);
    localByteArrayBuffer.append(b & 0xFF);
    return localByteArrayBuffer.toByteArray();
  }
  
  public String getName()
  {
    return "Roll Saved Delay 1";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return c + " " + b;
  }
  




  public RollSD1(byte[] data)
  {
    setSpeed(ByteUtil.convertUnsignedToInt(data[1]) / 255.0F);
    setHeading(ByteUtil.convertUnsignedToInt(data[2], data[3]));
  }
  
  public RollSD1(float _speed, int _heading)
  {
    setSpeed(_speed);
    setHeading(_heading);
  }
  




  public static byte getCommandID()
  {
    return 6;
  }
}
