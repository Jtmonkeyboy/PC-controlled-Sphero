//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;

public class MacroEmitMarker extends AsyncMessage {
  private int a;
  private int b;
  private int c;

  public MacroEmitMarker(byte[] packet) {
    super(packet);
  }

  protected void parseData() {
    super.parseData();
    byte[] var1 = this.getData();
    this.a = var1[0];
    this.b = var1[1];
    this.c = var1[2] << 8;
    this.c += var1[3];
  }

  public int getMarkerId() {
    return this.a;
  }

  public int getMacroLineNumber() {
    return this.c;
  }

  public int getMacroId() {
    return this.b;
  }
}
