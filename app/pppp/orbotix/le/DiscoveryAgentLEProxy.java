package com.orbotix.le;

import com.orbotix.common.DiscoveryAgentProxy;
import com.orbotix.le.connectstrategy.ConnectStrategy;

public abstract interface DiscoveryAgentLEProxy
  extends DiscoveryAgentProxy
{
  public abstract void setRadioDescriptor(RadioDescriptor paramRadioDescriptor);
  
  public abstract void setConnectStrategy(ConnectStrategy paramConnectStrategy);
}
