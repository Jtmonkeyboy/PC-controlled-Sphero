package com.orbotix.common;

import com.orbotix.command.SleepCommand.SleepType;
import com.orbotix.command.VersioningResponse;
import com.orbotix.common.internal.DeviceCommand;

public abstract interface Robot
{
  public abstract String getIdentifier();
  
  public abstract String getAddress();
  
  public abstract String getSerialNumber();
  
  public abstract String getName();
  
  public abstract void sendCommand(DeviceCommand paramDeviceCommand);
  
  public abstract void streamCommand(DeviceCommand paramDeviceCommand);
  
  public abstract void addResponseListener(ResponseListener paramResponseListener);
  
  public abstract void removeResponseListener(ResponseListener paramResponseListener);
  
  public abstract int getConnectTimeInSeconds();
  
  public abstract VersioningResponse getVersions();
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isOnline();
  
  public abstract boolean isBootloader();
  
  public abstract void sleep();
  
  public abstract void sleep(SleepCommand.SleepType paramSleepType);
  
  public abstract void disconnect();
  
  public abstract float getSignalQuality();
  
  public abstract String getRadioFirmwareRevision();
}
