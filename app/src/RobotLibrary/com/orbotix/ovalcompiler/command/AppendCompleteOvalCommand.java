package com.orbotix.ovalcompiler.command;

import android.support.annotation.NonNull;

public class AppendCompleteOvalCommand extends com.orbotix.common.internal.DeviceCommand
{
  private byte[] a;
  
  public AppendCompleteOvalCommand(@NonNull byte[] data)
  {
    a = data;
  }
  
  public byte getDeviceId()
  {
    return 2;
  }
  
  public byte getCommandId()
  {
    return Byte.MIN_VALUE;
  }
  
  public byte[] getData()
  {
    return a;
  }
}
