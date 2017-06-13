package com.orbotix.orbbasic;

import com.orbotix.common.internal.AsyncMessage;














public class OrbBasicErrorBinaryAsyncData
  extends AsyncMessage
{
  private byte[] a;
  
  public OrbBasicErrorBinaryAsyncData(byte[] packet)
  {
    super(packet);
  }
  
  protected void parseData()
  {
    super.parseData();
    a = getData();
  }
  




  public byte[] getErrorBinary()
  {
    return a;
  }
}
