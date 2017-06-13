package com.orbotix.common.internal;

public class AdHocCommand
  extends DeviceCommand
{
  byte a;
  byte b;
  byte[] c = new byte[0];
  
  public AdHocCommand(byte deviceId, byte commandId) {
    a = deviceId;
    b = commandId;
  }
  
  public AdHocCommand(int did, int cid) {
    a = ((byte)did);
    b = ((byte)cid);
  }
  
  public void setData(byte[] data) {
    c = data;
  }
  
  public byte[] getData()
  {
    return c;
  }
  
  public byte getDeviceId()
  {
    return a;
  }
  
  public byte getCommandId()
  {
    return b;
  }
}
