package com.orbotix.common;

import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceResponse;

public abstract interface ResponseListener
{
  public abstract void handleResponse(DeviceResponse paramDeviceResponse, Robot paramRobot);
  
  public abstract void handleStringResponse(String paramString, Robot paramRobot);
  
  public abstract void handleAsyncMessage(AsyncMessage paramAsyncMessage, Robot paramRobot);
}
