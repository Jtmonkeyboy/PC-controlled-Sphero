package com.orbotix.common.internal;

import java.util.Date;




public abstract class DeviceCommand
  extends DeviceMessage
{
  private Date a = null;
  private boolean b = true;
  private boolean c = true;
  
  public DeviceCommand()
  {
    a = getTimeStamp();
  }
  


  public abstract byte getDeviceId();
  

  public abstract byte getCommandId();
  

  protected Date getTransmitTimeStamp()
  {
    return a;
  }
  





  protected void setTransmitTimeStamp(Date timeStamp)
  {
    a = timeStamp;
  }
  




  protected long getTimeToTransmit()
  {
    Date localDate = getTimeStamp();
    

    return a.getTime() - localDate.getTime();
  }
  
  public void setKeepAlive(boolean keepAlive) {
    b = keepAlive;
  }
  
  public void setResponseRequested(boolean responseRequested) {
    c = responseRequested;
  }
  
  public boolean isResponseRequested() {
    return c;
  }
  
  public boolean isKeepAlive() {
    return b;
  }
  





  public byte[] getData()
  {
    return new byte[0];
  }
  
  protected int getDLEN()
  {
    byte[] arrayOfByte = getData();
    return arrayOfByte.length + 1;
  }
  
  public String toString()
  {
    return "<" + getClass().getSimpleName() + String.format(" seq=0x%02x ", new Object[] { Byte.valueOf(getSequenceNumber()) }) + ">";
  }
}
