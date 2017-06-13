package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;

public class WritePersistentPageCommand extends DeviceCommand
{
  private static final short a = 0;
  private static final short b = 3;
  private short c;
  private byte[] d;
  
  public WritePersistentPageCommand(short blockId, byte[] page) {
    if (blockId < 0) {
      throw new IllegalArgumentException("Block id cannot be lower than the minimum: 0");
    }
    if (blockId > 3) {
      throw new IllegalArgumentException("Block id cannot be higher than the maximum: 3");
    }
    c = blockId;
    d = page;
  }
  
  public byte getDeviceId()
  {
    return 2;
  }
  
  public byte getCommandId()
  {
    return -112;
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[d.length + 2];
    arrayOfByte[0] = ((byte)(c >> 4 & 0xFF));
    arrayOfByte[1] = ((byte)(c & 0xFF));
    System.arraycopy(d, 0, arrayOfByte, 2, d.length);
    return arrayOfByte;
  }
}
