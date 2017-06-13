package com.orbotix.ovalcompiler.response.async;

import com.orbotix.common.DLog;
import com.orbotix.common.internal.AsyncMessage;

public class OvalErrorBroadcast extends AsyncMessage
{
  private int a;
  
  public OvalErrorBroadcast(byte[] packet)
  {
    super(packet);
  }
  
  protected void parseData()
  {
    byte[] arrayOfByte = getData();
    if (arrayOfByte.length != 1) {
      DLog.e("Error broadcast is of invalid length! Data should be length 1.");
    }
    a = (arrayOfByte[0] & 0xFF);
  }
  
  public int getErrorCode() {
    return a;
  }
}
