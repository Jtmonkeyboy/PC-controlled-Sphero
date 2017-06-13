package com.orbotix.ovalcompiler.command;

import android.support.annotation.NonNull;

public class AppendFragmentOvalCommand extends com.orbotix.common.internal.DeviceCommand
{
  private byte[] a;
  
  public AppendFragmentOvalCommand(@NonNull byte[] data)
  {
    a = data;
  }
  
  public byte getDeviceId()
  {
    return 2;
  }
  
  public byte getCommandId()
  {
    return -125;
  }
  
  public byte[] getData()
  {
    return a;
  }
}
