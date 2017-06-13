package com.orbotix.common.internal;

public abstract interface RadioLinkSessionListener
{
  public abstract void handleResponseReceived(DeviceResponse paramDeviceResponse);
  
  public abstract void handleAsyncMessageReceived(AsyncMessage paramAsyncMessage);
  
  public abstract void handleStringResponseReceived(String paramString);
}
