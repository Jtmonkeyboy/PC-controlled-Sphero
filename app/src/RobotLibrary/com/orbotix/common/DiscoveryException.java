package com.orbotix.common;


public class DiscoveryException
  extends Exception
{
  private DiscoveryExceptionCode a;
  
  public DiscoveryException(DiscoveryExceptionCode reason)
  {
    a = reason;
  }
  
  public DiscoveryExceptionCode getCode() {
    return a;
  }
  
  public String getMessage()
  {
    return String.format("Discovery Failed : %s", new Object[] { getCode() });
  }
}
