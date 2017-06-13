package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;







public class PowerNotificationAsyncData
  extends AsyncMessage
{
  private int a;
  
  protected PowerNotificationAsyncData(byte[] packet)
  {
    super(packet);
  }
  
  protected void parseData()
  {
    super.parseData();
    byte[] arrayOfByte = getData();
    a = arrayOfByte[0];
  }
}
