package com.orbotix.classic;

import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;




class f
  implements Runnable
{
  private a a;
  private boolean b;
  private final LinkedList<DeviceCommand> c = new LinkedList();
  private OutputStream d;
  private final Object e = new Object();
  




  f(a paramA, OutputStream paramOutputStream)
  {
    a = paramA;
    b = false;
    d = paramOutputStream;
  }
  
  public void run()
  {
    DLog.v("Starting write thread");
    b = true;
    a.e();
    while (b)
    {

      DeviceCommand localDeviceCommand = b();
      
      if (localDeviceCommand == null) {
        try {
          c();
          if (!b) {
            DLog.w("Write loop notified while not running, assuming shutdown.");
            break;
          }
        } catch (InterruptedException localInterruptedException) {
          DLog.e("WriteRunnable:InterruptedException, stopping write thread");
          break;
        }
      }
      else
      {
        byte[] arrayOfByte = localDeviceCommand.getPacket();
        
        try
        {
          d.write(arrayOfByte);
          
          a.g();
        } catch (IOException localIOException) {
          DLog.e("Write fail: " + localIOException.getMessage());
          a();
          break;
        }
      } }
    e();
  }
  
  private DeviceCommand b() {
    synchronized (c) {
      return c.isEmpty() ? null : (DeviceCommand)c.remove(0);
    }
  }
  
  void a(DeviceCommand paramDeviceCommand) {
    synchronized (c) {
      c.add(paramDeviceCommand);
    }
    d();
  }
  
  void a() {
    b = false;
    d();
  }
  
  private void c() throws InterruptedException {
    synchronized (e) {
      e.wait();
    }
  }
  
  private void d() {
    synchronized (e) {
      e.notify();
    }
  }
  
  private void e() {
    d = null;
    c.clear();
    a.f();
    a = null;
  }
  
  static abstract interface a
  {
    public abstract void e();
    
    public abstract void f();
    
    public abstract void g();
  }
}
