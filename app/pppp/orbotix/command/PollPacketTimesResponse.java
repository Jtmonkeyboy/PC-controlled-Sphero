package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;








public class PollPacketTimesResponse
  extends DeviceResponse
{
  private long a;
  private long b;
  private long c;
  
  protected PollPacketTimesResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    if (getResponseCode() == ResponseCode.OK) {
      byte[] arrayOfByte = getPacket();
      a = ((arrayOfByte[5] & 0xFF) << 24);
      a += ((arrayOfByte[6] & 0xFF) << 16);
      a += ((arrayOfByte[7] & 0xFF) << 8);
      a += (arrayOfByte[8] & 0xFF);
      
      b = ((arrayOfByte[9] & 0xFF) << 24);
      b += ((arrayOfByte[10] & 0xFF) << 16);
      b += ((arrayOfByte[11] & 0xFF) << 8);
      b += (arrayOfByte[12] & 0xFF);
      
      c = ((arrayOfByte[13] & 0xFF) << 24);
      c += ((arrayOfByte[14] & 0xFF) << 16);
      c += ((arrayOfByte[15] & 0xFF) << 8);
      c += (arrayOfByte[16] & 0xFF);
    }
  }
  



  public long getCommandTransmitTime()
  {
    return a;
  }
  




  public long getRobotReceiveTime()
  {
    return b;
  }
  




  public long getRobotTransmitTime()
  {
    return c;
  }
  




  public long getTimeOffset()
  {
    return (a - b + (getReceivedTimeStamp() - c)) / 2L;
  }
  




  public long getTimeDelay()
  {
    return getReceivedTimeStamp() - a - (c - b);
  }
}
