package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;





public class GetDeviceModeResponse
  extends DeviceResponse
{
  public static final int DeviceModeNormal = 0;
  public static final int DeviceModeUserHack = 1;
  private int a;
  
  protected GetDeviceModeResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    if (getResponseCode() == ResponseCode.OK) {
      a = getPacket()[5];
    }
  }
  


  public int getDeviceMode()
  {
    return a;
  }
}
