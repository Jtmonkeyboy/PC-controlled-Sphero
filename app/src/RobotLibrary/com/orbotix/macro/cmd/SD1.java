package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;



public class SD1
  implements MacroCommand
{
  private static final String a = "Saved Delay 1";
  private Integer b = Integer.valueOf(1000);
  




  public Integer getDelay()
  {
    return b;
  }
  




  public void setDelay(Integer _delay)
  {
    if ((_delay.intValue() >= 0) && (_delay.intValue() < 65535)) {
      b = _delay;
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
    return "Saved Delay 1";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return new Integer(b.intValue()).toString();
  }
  




  public SD1(byte[] data)
  {
    b = Integer.valueOf(ByteUtil.convertUnsignedToInt(data[1], data[2]));
  }
  
  public SD1(Integer _delay)
  {
    setDelay(_delay);
  }
  




  public static byte getCommandID()
  {
    return 1;
  }
}
