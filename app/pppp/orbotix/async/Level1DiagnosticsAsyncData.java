//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;
import java.io.UnsupportedEncodingException;

public class Level1DiagnosticsAsyncData extends AsyncMessage {
  private String a;

  public Level1DiagnosticsAsyncData(byte[] packet) {
    super(packet);
  }

  protected void parseData() {
    super.parseData();
    byte[] var1 = this.getData();

    try {
      this.a = new String(var1, "US-ASCII");
    } catch (UnsupportedEncodingException var3) {
      var3.printStackTrace();
    }

  }

  public String getDiagnotics() {
    return this.a;
  }
}
