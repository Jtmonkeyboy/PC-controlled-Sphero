package com.orbotix.common;

public abstract interface DiscoveryStateChangedListener
{
  public abstract void onDiscoveryDidStart(DiscoveryAgent paramDiscoveryAgent);
  
  public abstract void onDiscoveryDidStop(DiscoveryAgent paramDiscoveryAgent);
}
