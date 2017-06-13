package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;







public class MacroEmitMarker
  extends AsyncMessage
{
  private int a;
  private int b;
  private int c;
  
  public MacroEmitMarker(byte[] packet)
  {
    super(packet);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    byte[] arrayOfByte = getData();
    a = arrayOfByte[0];
    b = arrayOfByte[1];
    c = (arrayOfByte[2] << 8);
    c += arrayOfByte[3];
  }
  




  public int getMarkerId()
  {
    return a;
  }
  




  public int getMacroLineNumber()
  {
    return c;
  }
  




  public int getMacroId()
  {
    return b;
  }
}
