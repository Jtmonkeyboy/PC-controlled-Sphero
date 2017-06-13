package com.orbotix.classic;

import android.bluetooth.BluetoothSocket;
import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;








class e
  implements d.a, f.a
{
  private InputStream a;
  private OutputStream b;
  private Thread c;
  private Thread d;
  private f e;
  private d f;
  private a g;
  private b h;
  
  e(a paramA, d.b paramB, BluetoothSocket paramBluetoothSocket)
  {
    g = paramA;
    
    InputStream localInputStream;
    OutputStream localOutputStream;
    try
    {
      localInputStream = paramBluetoothSocket.getInputStream();
      localOutputStream = paramBluetoothSocket.getOutputStream();
    } catch (IOException localIOException) {
      DLog.e("Error getting streams for reading or writing");
      return;
    }
    

    a = localInputStream;
    b = localOutputStream;
    
    h = b.a;
    

    f = new d(this, paramB, a);
    e = new f(this, b);
  }
  


  void c()
  {
    h = b.b;
    d = new Thread(f);
    c = new Thread(e);
    d.start();
    c.start();
  }
  
  void a(DeviceCommand paramDeviceCommand) {
    if (h == b.e) {
      e.a(paramDeviceCommand);
    }
  }
  
  void d() {
    h = b.f;
    f.a();
    e.a();
  }
  
  public void a()
  {
    switch (1.a[h.ordinal()]) {
    case 1: 
      h = b.e;
      g.c();
      break;
    case 2: 
      h = b.c;
      break;
    default: 
      throw new IllegalStateException("Read loop started improperly from state: " + h);
    }
  }
  
  public void e()
  {
    switch (1.a[h.ordinal()]) {
    case 3: 
      h = b.e;
      g.c();
      break;
    case 2: 
      h = b.d;
      break;
    default: 
      throw new IllegalStateException("Write loop started improperly from state: " + h);
    }
  }
  
  public void b()
  {
    switch (1.a[h.ordinal()]) {
    case 4: 
      h = b.i;
      g.d();
      d = null;
      c = null;
      break;
    case 5: 
      h = b.g;
      break;
    default: 
      DLog.e("Read loop stopped unexpectedly from state: " + h);
      h = b.g;
      e.a();
    }
    try
    {
      a.close();
    } catch (IOException localIOException) {
      DLog.e("Could not close input stream: " + localIOException.getMessage());
    }
    a = null;
  }
  

  public void f()
  {
    switch (1.a[h.ordinal()]) {
    case 6: 
      h = b.i;
      g.d();
      d = null;
      c = null;
      break;
    case 5: 
      h = b.h;
      break;
    default: 
      DLog.e("Write loop stopped unexpectedly from state: " + h);
      h = b.h;
      f.a();
    }
    try
    {
      b.close();
    } catch (IOException localIOException) {
      DLog.e("Could not close output stream: " + localIOException.getMessage());
    }
    b = null;
  }
  
  public void g()
  {
    g.e();
  }
  
  private static enum b
  {
    private b() {}
  }
  
  static abstract interface a
  {
    public abstract void c();
    
    public abstract void d();
    
    public abstract void e();
  }
}
