package com.orbotix.common.internal;

import com.orbotix.common.utilities.binary.ByteUtil;
import java.util.Date;




public class DeviceResponse
  extends DeviceMessage
{
  protected static final int TOTAL_PACKET_PREFIX_LENGTH = 5;
  private DeviceCommand a;
  
  private DeviceResponse() {}
  
  protected DeviceResponse(byte[] packet, DeviceCommand command)
  {
    setPacket(packet);
    a = command;
    parseData();
  }
  

  protected void parseData() {}
  
  public byte getDeviceId()
  {
    if (a == null) {
      return 0;
    }
    return a.getDeviceId();
  }
  
  public byte getCommandId() { if (a == null) {
      return 0;
    }
    return a.getCommandId();
  }
  


  public ResponseCode getCode()
  {
    return ResponseCode.byteToResponseCode(getPacket()[2]);
  }
  
  public ResponseCode getResponseCode() {
    return getCode();
  }
  
  public byte getSequenceNumber()
  {
    return getPacket()[3];
  }
  
  public DeviceCommand getCommand() {
    return a;
  }
  



  protected long getReceivedTimeStamp()
  {
    return getTimeStamp().getTime();
  }
  
  public long getDataUint32Value() {
    byte[] arrayOfByte1 = getPacket();
    int i = arrayOfByte1[4] - 1;
    if (i != 4) {
      return 0L;
    }
    
    byte[] arrayOfByte2 = new byte[i];
    System.arraycopy(arrayOfByte1, 5, arrayOfByte2, 0, arrayOfByte2.length);
    
    return ByteUtil.convertBytesTo32BitUnsignedInt(arrayOfByte2);
  }
  

  public String toString()
  {
    return "<" + getClass().getSimpleName() + String.format(" seq:0x%02x ", new Object[] { Byte.valueOf(getSequenceNumber()) }) + getCode() + '>';
  }
}
