package com.orbotix.command;

import com.orbotix.common.internal.DeviceResponse;

public class GetOdometerResponse extends DeviceResponse
{
  private long a;
  
  protected GetOdometerResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command) {
    super(packet, command);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    a = getDataUint32Value();
  }
  
  public long getDistanceInCentimeters() {
    return a;
  }
}
