package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;
import java.io.UnsupportedEncodingException;

public class Level1DiagnosticsAsyncData extends AsyncMessage
{
  private String a;
  
  public Level1DiagnosticsAsyncData(byte[] packet)
  {
    super(packet);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    byte[] arrayOfByte = getData();
    try
    {
      a = new String(arrayOfByte, "US-ASCII");
    } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
      localUnsupportedEncodingException.printStackTrace();
    }
  }
  
  public String getDiagnotics()
  {
    return a;
  }
}
