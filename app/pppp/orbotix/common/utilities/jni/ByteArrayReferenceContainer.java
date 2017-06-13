package com.orbotix.common.utilities.jni;


public class ByteArrayReferenceContainer
{
  private byte[] a;
  
  public ByteArrayReferenceContainer()
  {
    a = new byte[0];
  }
  
  public byte[] getByteArray() {
    return a;
  }
  
  public void setByteArray(byte[] byteArray) {
    a = byteArray;
  }
}
