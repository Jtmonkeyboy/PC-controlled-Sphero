package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;

public class GetFactoryConfigBlockCRCResponse extends DeviceResponse
{
  private long a;
  
  protected GetFactoryConfigBlockCRCResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    if (getResponseCode() == com.orbotix.common.internal.ResponseCode.OK) {
      a = getDataUint32Value();
    }
    else {
      com.orbotix.common.DLog.e("Factory config block response does not have OK code");
      a = 0L;
    }
  }
  
  public long getFactoryConfigBlockCRC() {
    return a;
  }
}
