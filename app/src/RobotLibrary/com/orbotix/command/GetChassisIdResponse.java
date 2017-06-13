package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;

public class GetChassisIdResponse extends DeviceResponse
{
  private int a;
  
  protected GetChassisIdResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected void parseData()
  {
    byte[] arrayOfByte1 = getPacket();
    
    if (getResponseCode() == com.orbotix.common.internal.ResponseCode.OK) {
      byte[] arrayOfByte2 = new byte[arrayOfByte1[4] - 1];
      System.arraycopy(arrayOfByte1, 5, arrayOfByte2, 0, arrayOfByte2.length);
      
      a = com.orbotix.common.utilities.binary.ByteUtil.convertBytesTo16BitInt(arrayOfByte2);
    }
  }
  
  public int getChassisId() {
    return a;
  }
}
