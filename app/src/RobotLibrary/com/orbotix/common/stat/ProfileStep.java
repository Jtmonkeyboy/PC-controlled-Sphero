package com.orbotix.common.stat;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;

class ProfileStep
{
  private DeviceCommand a;
  private Class<? extends DeviceResponse> b;
  private ProfileStepHandler c;
  
  public ProfileStep(@android.support.annotation.NonNull DeviceCommand command, @android.support.annotation.NonNull Class<? extends DeviceResponse> responseClass, @android.support.annotation.NonNull ProfileStepHandler profileStepHandler)
  {
    a = command;
    b = responseClass;
    c = profileStepHandler;
  }
  
  public DeviceCommand a() {
    return a;
  }
  
  public Class<? extends DeviceResponse> b() {
    return b;
  }
  
  public ProfileStepHandler c() {
    return c;
  }
  
  public static abstract interface ProfileStepHandler
  {
    public abstract void handleProfileStepResponse(DeviceResponse paramDeviceResponse);
  }
}
