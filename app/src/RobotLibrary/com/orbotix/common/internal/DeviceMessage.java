package com.orbotix.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Date;


public abstract class DeviceMessage
  implements Parcelable, Serializable
{
  private final Date a;
  private byte[] b;
  private byte c;
  
  public DeviceMessage()
  {
    a = new Date();
  }
  




  public Date getTimeStamp()
  {
    return a;
  }
  
  public byte[] getPacket() {
    return b;
  }
  
  protected void setPacket(byte[] packet) {
    b = packet;
  }
  
  public void setSequenceNumber(byte sequenceNumber) {
    c = sequenceNumber;
  }
  
  public byte getSequenceNumber() {
    return c;
  }
  

  public abstract byte getDeviceId();
  
  public abstract byte getCommandId();
  
  public int describeContents()
  {
    return 0;
  }
  


  public void writeToParcel(Parcel parcel, int i)
  {
    parcel.writeSerializable(this);
  }
}
