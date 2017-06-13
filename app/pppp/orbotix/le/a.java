package com.orbotix.le;

import android.bluetooth.BluetoothGattCharacteristic;
import com.orbotix.common.internal.DeviceCommand;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



class a
{
  private final Queue<a> a;
  private b b;
  private final Object c = new Object();
  private boolean d;
  private a e;
  private static final int f = 20;
  
  public a(b paramB) {
    a = new LinkedList();
    b = paramB;
    d = false;
  }
  
  public void a() {
    synchronized (a) {
      a.clear();
    }
    synchronized (c) {
      d = false;
    }
  }
  
  public void a(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, DeviceCommand paramDeviceCommand) {
    byte[] arrayOfByte1 = paramDeviceCommand.getPacket();
    
    int i = (int)Math.ceil(arrayOfByte1.length / 20.0F);
    
    for (int j = 0; j < i; j++) {
      int k = j * 20;
      int m = Math.min(20, arrayOfByte1.length - k);
      int n = k + m;
      byte[] arrayOfByte2 = Arrays.copyOfRange(arrayOfByte1, k, n);
      
      int i1 = j >= i - 1 ? 1 : 0;
      int i2 = i1 != 0 ? 2 : 1;
      
      synchronized (a) {
        a.add(new a(paramBluetoothGattCharacteristic, arrayOfByte2, i2));
      }
    }
    
    c();
  }
  
  public void a(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, byte[] paramArrayOfByte, int paramInt) {
    synchronized (a) {
      a.add(new a(paramBluetoothGattCharacteristic, paramArrayOfByte, paramInt));
    }
    c();
  }
  
  public void a(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, byte[] paramArrayOfByte) {
    a(paramBluetoothGattCharacteristic, paramArrayOfByte, 2);
  }
  
  public void b() {
    synchronized (a) {
      if (a.size() == 0)
      {
        synchronized (c) {
          d = false;
          return;
        }
      }
      e = ((a)a.remove());
    }
    b.a(e.a, e.c, e.b);
  }
  
  private void c() {
    int i = 0;
    synchronized (c) {
      if (!d) {
        d = true;
        i = 1;
      }
    }
    if (i != 0) {
      b();
    }
  }
  

  private class a
  {
    public BluetoothGattCharacteristic a;
    
    public int b;
    public byte[] c;
    
    public a(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, byte[] paramArrayOfByte, int paramInt)
    {
      a = paramBluetoothGattCharacteristic;
      b = paramInt;
      c = paramArrayOfByte;
    }
  }
  
  static abstract interface b
  {
    public abstract void a(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, byte[] paramArrayOfByte, int paramInt);
  }
}
