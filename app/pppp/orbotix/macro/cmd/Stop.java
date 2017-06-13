package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;

public class Stop implements MacroCommand
{
  private final String a = "Stop";
  
  private int b = 0;
  private float c = 0.0F;
  
  public int getDelay()
  {
    return b;
  }
  
  public String getSettingsString() {
    return Integer.toString(b);
  }
  
  public String getName()
  {
    return "Stop";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public Stop(byte[] data)
  {
    setDelay(ByteUtil.convertUnsignedToInt(data[2]));
  }
  
  public void setDelay(int delay) {
    b = (delay & 0xFF);
  }
  
  public byte[] getByteRepresentation() {
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(getLength());
    localByteArrayBuffer.append(getCommandID());
    localByteArrayBuffer.append(0);
    localByteArrayBuffer.append(b);
    return localByteArrayBuffer.toByteArray();
  }
  
  public int getLength()
  {
    return 3;
  }
  


  public Stop(int delay)
  {
    b = delay;
    setSpeed(0.0F);
  }
  

  public void setSpeed(float speed)
  {
    c = 0.0F;
  }
  
  public static byte getCommandID()
  {
    return 37;
  }
}
