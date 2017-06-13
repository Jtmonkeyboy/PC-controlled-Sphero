package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;



public class Delay
  implements MacroCommand
{
  private static final String a = "Delay";
  private int b = 1000;
  




  public int getDelay()
  {
    return b;
  }
  




  public void setDelay(int _delay)
  {
    b = (_delay & 0xFFFF);
  }
  




  public int getLength()
  {
    return 3;
  }
  




  public byte[] getByteRepresentation()
  {
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(getLength());
    localByteArrayBuffer.append(getCommandID());
    localByteArrayBuffer.append(b >> 8);
    localByteArrayBuffer.append(b & 0xFF);
    return localByteArrayBuffer.toByteArray();
  }
  
  public String getName()
  {
    return "Delay";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return Integer.toString(b);
  }
  




  public Delay(byte[] data)
  {
    b = ByteUtil.convertUnsignedToInt(data[1], data[2]);
  }
  
  public Delay(int _delay)
  {
    setDelay(_delay);
  }
  




  public static byte getCommandID()
  {
    return 11;
  }
}
