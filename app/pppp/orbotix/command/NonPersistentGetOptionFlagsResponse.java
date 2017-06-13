package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;



public class NonPersistentGetOptionFlagsResponse
  extends DeviceResponse
{
  private long a;
  
  protected NonPersistentGetOptionFlagsResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    if (getResponseCode() == ResponseCode.OK) {
      a = ((getPacket()[5] & 0xFF) << 24);
      a += ((getPacket()[6] & 0xFF) << 16);
      a += ((getPacket()[7] & 0xFF) << 8);
      a += (getPacket()[8] & 0xFF);
    }
  }
  
  public long getMask() {
    return a;
  }
  
  public String toString()
  {
    return super.toString() + String.format("%X", new Object[] { Long.valueOf(a) });
  }
}
