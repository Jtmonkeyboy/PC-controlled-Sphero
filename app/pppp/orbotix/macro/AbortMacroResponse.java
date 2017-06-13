package com.orbotix.macro;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;



public class AbortMacroResponse
  extends DeviceResponse
{
  static final int a = 0;
  private int b;
  
  public AbortMacroResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    if (getResponseCode() == ResponseCode.OK) {
      b = getPacket()[5];
    }
  }
  







  public int getMacroId()
  {
    return b;
  }
}
