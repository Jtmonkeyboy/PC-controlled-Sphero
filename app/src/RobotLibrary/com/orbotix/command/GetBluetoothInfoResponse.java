package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;
import java.io.UnsupportedEncodingException;




public class GetBluetoothInfoResponse
  extends DeviceResponse
{
  private static final int a = 64;
  private static final int b = 32;
  private static final int c = 48;
  private static final int d = 16;
  private static final int e = 16;
  private String f;
  private String g;
  
  protected GetBluetoothInfoResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  

  protected void parseData()
  {
    super.parseData();
    
    if (getResponseCode() == ResponseCode.OK)
    {
      int i = getPacket().length - 5 - 1;
      int j = i == 64 ? 48 : 16;
      

      int k = j;
      for (int m = 0; m < j; m++) {
        if (getPacket()[(5 + m)] == 0) {
          k = m;
          break;
        }
      }
      
      m = 16;
      for (int n = 0; n < 16; n++) {
        if (getPacket()[(5 + n + j)] == 0) {
          m = n;
          break;
        }
      }
      try
      {
        f = new String(getPacket(), 5, k, "UTF-8");
        g = new String(getPacket(), 5 + j, m, "US-ASCII");
        g = g.toUpperCase();
      } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        localUnsupportedEncodingException.printStackTrace();
        f = null;
        g = null;
      }
    }
  }
  




  public String getName()
  {
    return f;
  }
  




  public String getAddress()
  {
    return g;
  }
}
