//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.classic;

import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

class f implements Runnable {
  private f.a a;
  private boolean b;
  private final LinkedList<DeviceCommand> c = new LinkedList();
  private OutputStream d;
  private final Object e = new Object();

  f(f.a var1, OutputStream var2) {
    this.a = var1;
    this.b = false;
    this.d = var2;
  }

  public void run() {
    DLog.v("Starting write thread");
    this.b = true;
    this.a.e();

    while(this.b) {
      DeviceCommand var1 = this.b();
      if(var1 == null) {
        try {
          this.c();
          if(!this.b) {
            DLog.w("Write loop notified while not running, assuming shutdown.");
            break;
          }
        } catch (InterruptedException var4) {
          DLog.e("WriteRunnable:InterruptedException, stopping write thread");
          break;
        }
      } else {
        byte[] var2 = var1.getPacket();

        try {
          this.d.write(var2);
          this.a.g();
        } catch (IOException var5) {
          DLog.e("Write fail: " + var5.getMessage());
          this.a();
          break;
        }
      }
    }

    this.e();
  }

  private DeviceCommand b() {
    LinkedList var1 = this.c;
    synchronized(this.c) {
      return this.c.isEmpty()?null:(DeviceCommand)this.c.remove(0);
    }
  }

  void a(DeviceCommand var1) {
    LinkedList var2 = this.c;
    synchronized(this.c) {
      this.c.add(var1);
    }

    this.d();
  }

  void a() {
    this.b = false;
    this.d();
  }

  private void c() throws InterruptedException {
    Object var1 = this.e;
    synchronized(this.e) {
      this.e.wait();
    }
  }

  private void d() {
    Object var1 = this.e;
    synchronized(this.e) {
      this.e.notify();
    }
  }

  private void e() {
    this.d = null;
    this.c.clear();
    this.a.f();
    this.a = null;
  }

  interface a {
    void e();

    void f();

    void g();
  }
}
