package com.orbotix.orbbasic;

import com.orbotix.common.internal.AsyncMessage;














public class OrbBasicErrorASCIIAsyncData
  extends AsyncMessage
{
  private String a;
  
  public OrbBasicErrorASCIIAsyncData(byte[] packet)
  {
    super(packet);
  }
  
  protected void parseData()
  {
    super.parseData();
    a = new String(getData());
  }
  





  public String getErrorASCII()
  {
    return a;
  }
}
