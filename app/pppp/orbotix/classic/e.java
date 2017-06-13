//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.classic;

import android.bluetooth.BluetoothSocket;
import com.orbotix.classic.d;
import com.orbotix.classic.f;
import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class e implements com.orbotix.classic.d.a, com.orbotix.classic.f.a {
  private InputStream a;
  private OutputStream b;
  private Thread c;
  private Thread d;
  private f e;
  private d f;
  private e.a g;
  private e.b h;

  e(e.a var1, com.orbotix.classic.d.b var2, BluetoothSocket var3) {
    this.g = var1;

    InputStream var4;
    OutputStream var5;
    try {
      var4 = var3.getInputStream();
      var5 = var3.getOutputStream();
    } catch (IOException var7) {
      DLog.e("Error getting streams for reading or writing");
      return;
    }

    this.a = var4;
    this.b = var5;
    this.h = e.b.a;
    this.f = new d(this, var2, this.a);
    this.e = new f(this, this.b);
  }

  void c() {
    this.h = e.b.b;
    this.d = new Thread(this.f);
    this.c = new Thread(this.e);
    this.d.start();
    this.c.start();
  }

  void a(DeviceCommand var1) {
    if(this.h == e.b.e) {
      this.e.a(var1);
    }

  }

  void d() {
    this.h = e.b.f;
    this.f.a();
    this.e.a();
  }

  public void a() {
    switch(null.a[this.h.ordinal()]) {
      case 1:
        this.h = e.b.e;
        this.g.c();
        break;
      case 2:
        this.h = e.b.c;
        break;
      default:
        throw new IllegalStateException("Read loop started improperly from state: " + this.h);
    }

  }

  public void e() {
    switch(null.a[this.h.ordinal()]) {
      case 2:
        this.h = e.b.d;
        break;
      case 3:
        this.h = e.b.e;
        this.g.c();
        break;
      default:
        throw new IllegalStateException("Write loop started improperly from state: " + this.h);
    }

  }

  public void b() {
    switch(null.a[this.h.ordinal()]) {
      case 4:
        this.h = e.b.i;
        this.g.d();
        this.d = null;
        this.c = null;
        break;
      case 5:
        this.h = e.b.g;
        break;
      default:
        DLog.e("Read loop stopped unexpectedly from state: " + this.h);
        this.h = e.b.g;
        this.e.a();
    }

    try {
      this.a.close();
    } catch (IOException var2) {
      DLog.e("Could not close input stream: " + var2.getMessage());
    }

    this.a = null;
  }

  public void f() {
    switch(null.a[this.h.ordinal()]) {
      case 5:
        this.h = e.b.h;
        break;
      case 6:
        this.h = e.b.i;
        this.g.d();
        this.d = null;
        this.c = null;
        break;
      default:
        DLog.e("Write loop stopped unexpectedly from state: " + this.h);
        this.h = e.b.h;
        this.f.a();
    }

    try {
      this.b.close();
    } catch (IOException var2) {
      DLog.e("Could not close output stream: " + var2.getMessage());
    }

    this.b = null;
  }

  public void g() {
    this.g.e();
  }

  private static enum b {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i;

    private b() {
    }
  }

  interface a {
    void c();

    void d();

    void e();
  }
}
