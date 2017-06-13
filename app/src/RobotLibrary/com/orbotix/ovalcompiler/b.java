package com.orbotix.ovalcompiler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.orbotix.common.internal.DeviceCommand;
import java.util.ArrayList;
import java.util.List;


class b
{
  private List<DeviceCommand> a = new ArrayList();
  
  public b(@NonNull a[] paramArrayOfA) {
    for (a localA : paramArrayOfA) {
      a.addAll(localA.a());
    }
  }
  

  public b(@NonNull a paramA) { this(new a[] { paramA }); }
  
  @Nullable
  public DeviceCommand a() {
    if (a.size() < 1) {
      return null;
    }
    return (DeviceCommand)a.get(0);
  }
  
  public void b() {
    if (a.size() > 0) {
      a.remove(0);
    }
  }
  
  public boolean c() {
    return a.size() < 1;
  }
}
