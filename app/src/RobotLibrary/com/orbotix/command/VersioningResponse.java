package com.orbotix.command;

import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.RobotVersion;

public final class VersioningResponse extends DeviceResponse
{
  private RobotVersion a;
  
  protected VersioningResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command)
  {
    super(packet, command);
  }
  
  public static VersioningResponse createDefaultVersioningResponse() {
    return new VersioningResponse(null, null);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    if (getPacket() == null) {
      a = new RobotVersion();
      return;
    }
    
    if (getResponseCode() == com.orbotix.common.internal.ResponseCode.OK) {
      byte[] arrayOfByte = getPacket();
      int i = arrayOfByte[5];
      String str1 = (i >> 4) + "." + (0xF & i);
      
      int j = arrayOfByte[6];
      
      int k = arrayOfByte[7];
      String str2 = (k >> 4) + "." + (0xF & k);
      
      String str3 = arrayOfByte[8] + "." + arrayOfByte[9];
      

      int m = arrayOfByte[10];
      String str4 = (m >> 4) + "." + (0xF & m);
      
      int n = arrayOfByte[11];
      String str5 = (n >> 4) + "." + (0xF & n);
      
      int i1 = arrayOfByte[12];
      String str6 = (i1 >> 4) + "." + (0xF & i1);
      
      a = new RobotVersion(j, str1, str2, str3, str4, str5, str6);
    }
    else
    {
      a = new RobotVersion();
    }
  }
  
  public RobotVersion getVersion() {
    return a;
  }
}
