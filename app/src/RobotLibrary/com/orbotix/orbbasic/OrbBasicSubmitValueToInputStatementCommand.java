package com.orbotix.orbbasic;

import com.orbotix.common.internal.DeviceCommand;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class OrbBasicSubmitValueToInputStatementCommand extends DeviceCommand
{
  private int a;
  
  public OrbBasicSubmitValueToInputStatementCommand(int value)
  {
    a = value;
  }
  
  public byte getDeviceId()
  {
    return 2;
  }
  
  public byte getCommandId()
  {
    return 100;
  }
  
  public byte[] getData()
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(4);
    localByteBuffer.order(ByteOrder.BIG_ENDIAN);
    localByteBuffer.putInt(a);
    localByteBuffer.flip();
    return localByteBuffer.array();
  }
}
