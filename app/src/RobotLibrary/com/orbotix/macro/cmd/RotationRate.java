package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;



public class RotationRate
  implements MacroCommand
{
  private static final String a = "Rotation Rate";
  private float b;
  
  public int getLength()
  {
    return 2;
  }
  
  public byte[] getByteRepresentation()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[0] = 19;
    arrayOfByte[1] = ((byte)(int)(b * 255.0D));
    
    return arrayOfByte;
  }
  
  public String getName()
  {
    return "Rotation Rate";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return new Float(b).toString();
  }
  
  public RotationRate(byte[] data) {
    setRotationRate(ByteUtil.convertUnsignedToInt(data[1]) / 255.0F);
  }
  
  public void setRotationRate(float rate) {
    b = (rate > 1.0F ? 1.0F : rate);
    b = (b < 0.0F ? 0.0F : b);
  }
  
  public float getRotationRate() {
    return b;
  }
  
  public RotationRate(float rate)
  {
    setRotationRate(rate);
  }
  
  public static byte getCommandID() {
    return 19;
  }
}
