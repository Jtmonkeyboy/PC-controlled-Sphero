package com.orbotix.common.utilities.binary;

public class BitMask<T extends Maskable> implements Maskable
{
  long a = 0L;
  
  public BitMask(Maskable... flags) {
    for (Maskable localMaskable : flags) {
      a |= localMaskable.longValue();
    }
  }
  
  public BitMask(long hexMask) {
    a = hexMask;
  }
  
  public void addFlag(Maskable flag) {
    a |= flag.longValue();
  }
  
  public void removeFlag(Maskable flag) {
    a &= (flag.longValue() ^ 0xFFFFFFFFFFFFFFFF);
  }
  
  public long longValue()
  {
    return a;
  }
  
  public void setFlag(Maskable flag, boolean on) {
    if (on) {
      addFlag(flag);
    } else {
      removeFlag(flag);
    }
  }
  
  public String toString() {
    return String.format("%08X", new Object[] { Long.valueOf(a) });
  }
  
  public byte[] getByteArray() {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = ((byte)(int)(a >> 24));
    arrayOfByte[1] = ((byte)(int)(a >> 16));
    arrayOfByte[2] = ((byte)(int)(a >> 8));
    arrayOfByte[3] = ((byte)(int)a);
    return arrayOfByte;
  }
  
  public boolean isEnabled(Maskable flag)
  {
    return (a & flag.longValue()) != 0L;
  }
}
