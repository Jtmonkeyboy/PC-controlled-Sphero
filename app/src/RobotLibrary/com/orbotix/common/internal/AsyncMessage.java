package com.orbotix.common.internal;

import android.support.annotation.NonNull;

















public class AsyncMessage
  extends DeviceMessage
{
  private static final int a = 5;
  
  public static enum Type
  {
    private final byte a;
    
    private Type(int value)
    {
      a = ((byte)value);
    }
    
    public byte getValue() {
      return a;
    }
    
    public static Type fromByte(byte typeAsByte) {
      for (Type localType : ) {
        if (localType.getValue() == typeAsByte) {
          return localType;
        }
      }
      return Error;
    }
  }
  
  public AsyncMessage(byte[] packet)
  {
    setPacket(packet);
    parseData();
  }
  


  protected void parseData() {}
  

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("<AsyncMessage: ");
    localStringBuilder.append(getType());
    localStringBuilder.append(" l=").append(getPacket().length);
    localStringBuilder.append(">");
    return localStringBuilder.toString();
  }
  
  public byte getDeviceId()
  {
    return -1;
  }
  


  public byte getCommandId() { return -1; }
  
  @NonNull
  protected byte[] getData() {
    byte[] arrayOfByte1 = getPacket();
    int i = arrayOfByte1[3] << 8;
    i += arrayOfByte1[4] - 1;
    

    byte[] arrayOfByte2 = new byte[i];
    for (int j = 0; j < i; j++) {
      arrayOfByte2[j] = getPacket()[(5 + j)];
    }
    return arrayOfByte2;
  }
  
  public Type getType() {
    return Type.fromByte(getPacket()[2]);
  }
}
