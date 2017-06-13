package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;



public class SPD2
  implements MacroCommand
{
  private static final String a = "Saved Speed 2";
  private float b = 0.5F;
  




  public float getSpeed()
  {
    return b;
  }
  




  public void setSpeed(float _speed)
  {
    if ((_speed >= 0.0D) && (_speed <= 1.0D)) {
      b = _speed;
    }
  }
  




  public int getLength()
  {
    return 2;
  }
  




  public byte[] getByteRepresentation()
  {
    Integer localInteger = Integer.valueOf((int)(b * 255.0D));
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(getLength());
    localByteArrayBuffer.append(getCommandID());
    localByteArrayBuffer.append(localInteger.intValue());
    return localByteArrayBuffer.toByteArray();
  }
  
  public String getName()
  {
    return "Saved Speed 2";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return new Float(b).toString();
  }
  




  public SPD2(byte[] data)
  {
    b = (ByteUtil.convertUnsignedToInt(data[1]) / 255.0F);
  }
  
  public SPD2(float _speed)
  {
    setSpeed(_speed);
  }
  




  public static byte getCommandID()
  {
    return 16;
  }
}
