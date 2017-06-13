//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;

public class PowerNotificationAsyncData extends AsyncMessage {
  private int a;

  protected PowerNotificationAsyncData(byte[] packet) {
    super(packet);
  }

  protected void parseData() {
    super.parseData();
    byte[] var1 = this.getData();
    this.a = var1[0];
  }
}
