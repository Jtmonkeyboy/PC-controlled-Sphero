package com.orbotix.common.internal;







public class ByteResponse
  extends DeviceResponse
{
  public ByteResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("<");
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append(String.format(" dev=0x%02x", new Object[] { Byte.valueOf(getDeviceId()) }));
    localStringBuilder.append(String.format(" cmd=0x%02x", new Object[] { Byte.valueOf(getCommandId()) }));
    localStringBuilder.append(String.format(" seq=0x%02x ", new Object[] { Byte.valueOf(getSequenceNumber()) }));
    localStringBuilder.append(getCode());
    localStringBuilder.append('>');
    return localStringBuilder.toString();
  }
}
