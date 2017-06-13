package com.orbotix.ovalcompiler;

import android.support.annotation.NonNull;
import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.ovalcompiler.command.AppendCompleteOvalCommand;
import com.orbotix.ovalcompiler.command.AppendFragmentOvalCommand;
import java.util.ArrayList;
import java.util.List;

class a
{
  private byte[][] a;
  
  public a(@NonNull byte[][] paramArrayOfByte)
  {
    if (paramArrayOfByte.length == 0) {
      DLog.w("Just so you know... You're making a program with no chunks. You probably shouldn't do that.");
    }
    a = paramArrayOfByte;
  }
  
  public List<DeviceCommand> a() {
    ArrayList localArrayList = new ArrayList(a.length);
    if (a.length == 0) {
      return localArrayList;
    }
    for (int i = 0; i < a.length - 1; i++) {
      localArrayList.add(new AppendFragmentOvalCommand(a[i]));
    }
    localArrayList.add(new AppendCompleteOvalCommand(a[(a.length - 1)]));
    return localArrayList;
  }
  
  public byte[][] b() {
    return a;
  }
}
