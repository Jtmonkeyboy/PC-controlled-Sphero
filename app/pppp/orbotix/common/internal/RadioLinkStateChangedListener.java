package com.orbotix.common.internal;

public abstract interface RadioLinkStateChangedListener
{
  public abstract void handleLinkConnectionInitiated();
  
  public abstract void handleLinkFailedToConnect();
  
  public abstract void handleLinkDidConnect();
  
  public abstract void handleLinkDidWake();
  
  public abstract void handleLinkDidSleep();
  
  public abstract void handleLinkDidDisconnect();
}
