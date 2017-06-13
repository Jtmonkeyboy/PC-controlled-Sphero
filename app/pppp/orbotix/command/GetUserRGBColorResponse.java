package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;
import com.orbotix.common.utilities.binary.ByteUtil;






public class GetUserRGBColorResponse
  extends DeviceResponse
{
  private short a;
  private short b;
  private short c;
  
  protected GetUserRGBColorResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
    
    if (getResponseCode() == ResponseCode.OK) {
      a = ByteUtil.convertUnsignedToShort(packet[5]);
      b = ByteUtil.convertUnsignedToShort(packet[6]);
      c = ByteUtil.convertUnsignedToShort(packet[7]);
      a = ByteUtil.convertUnsignedToShort(packet[5]);
      b = ByteUtil.convertUnsignedToShort(packet[6]);
      c = ByteUtil.convertUnsignedToShort(packet[7]);
    }
  }
  
  public short getRedIntensity()
  {
    return a;
  }
  
  public short getGreenIntensity()
  {
    return b;
  }
  
  public short getBlueIntensity()
  {
    return c;
  }
}
