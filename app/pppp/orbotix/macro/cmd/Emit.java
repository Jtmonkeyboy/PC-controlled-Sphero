package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;



public class Emit
  implements MacroCommand
{
  private static final String a = "Emit";
  private int b = 1;
  




  public int getIdentifier()
  {
    return b;
  }
  




  public void setIdentifier(int _identifier)
  {
    b = (_identifier & 0xFF);
  }
  




  public int getLength()
  {
    return 2;
  }
  




  public byte[] getByteRepresentation()
  {
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(getLength());
    localByteArrayBuffer.append(getCommandID());
    localByteArrayBuffer.append(b);
    return localByteArrayBuffer.toByteArray();
  }
  
  public String getName()
  {
    return "Emit";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return new Integer(b).toString();
  }
  




  public Emit(byte[] data)
  {
    setIdentifier(ByteUtil.convertUnsignedToInt(data[1]));
  }
  
  public Emit(int _identifier)
  {
    setIdentifier(_identifier);
  }
  




  public static byte getCommandID()
  {
    return 21;
  }
}
