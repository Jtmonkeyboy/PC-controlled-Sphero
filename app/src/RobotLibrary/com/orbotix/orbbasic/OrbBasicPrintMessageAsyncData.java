package com.orbotix.orbbasic;

import com.orbotix.common.internal.AsyncMessage;














public class OrbBasicPrintMessageAsyncData
  extends AsyncMessage
{
  private String a;
  
  public OrbBasicPrintMessageAsyncData(byte[] packet)
  {
    super(packet);
  }
  

  protected void parseData()
  {
    super.parseData();
    byte[] arrayOfByte = getData();
    
    a = new String(arrayOfByte);
  }
  





  public String getMessage()
  {
    return a;
  }
}
