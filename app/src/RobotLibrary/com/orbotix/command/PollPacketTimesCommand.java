package com.orbotix.command;

import com.orbotix.common.internal.CoreCommandId;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import java.util.Date;















public class PollPacketTimesCommand
  extends DeviceCommand
{
  public PollPacketTimesCommand() {}
  
  public byte getDeviceId()
  {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId()
  {
    return CoreCommandId.POLL_PACKET_TIMES.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[4];
    
    long l = getTransmitTimeStamp().getTime();
    arrayOfByte[0] = ((byte)(int)(l >> 24));
    arrayOfByte[1] = ((byte)(int)(l >> 16));
    arrayOfByte[2] = ((byte)(int)(l >> 8));
    arrayOfByte[3] = ((byte)(int)l);
    
    return arrayOfByte;
  }
}
