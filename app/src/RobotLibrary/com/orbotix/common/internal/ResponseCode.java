package com.orbotix.common.internal;

import com.orbotix.common.DLog;


















public enum ResponseCode
{
  private final byte a;
  private final String b;
  
  private ResponseCode(int code, String description)
  {
    a = ((byte)code);
    b = description;
  }
  
  public byte getCode() {
    return a;
  }
  
  public static ResponseCode byteToResponseCode(byte val) {
    for (ResponseCode localResponseCode : ) {
      if (localResponseCode.getCode() == val) {
        return localResponseCode;
      }
    }
    
    DLog.e("RobotKit", new Object[] { "Invalid ResponseCode: " + String.format("%02X", new Object[] { Byte.valueOf(val) }) });
    return GENERAL_ERROR;
  }
  
  public String toString()
  {
    return b;
  }
}
