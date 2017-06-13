//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;

public class SelfLevelCompleteAsyncData extends AsyncMessage {
  public static final int RESULT_CODE_UKNOWN = 0;
  public static final int RESULT_CODE_TIMEOUT = 1;
  public static final int RESULT_CODE_SENSORS_ERROR = 2;
  public static final int RESULT_CODE_SELF_LEVEL_DISABLED = 3;
  public static final int RESULT_CODE_ABORTED = 4;
  public static final int RESULT_CODE_CHARGER_NOT_FOUND = 5;
  public static final int RESULT_CODE_SUCCESS = 6;
  private int a;

  public SelfLevelCompleteAsyncData(byte[] packet) {
    super(packet);
  }

  protected void parseData() {
    super.parseData();
    byte[] var1 = this.getData();
    this.a = var1[0];
  }

  public int getResultCode() {
    return this.a;
  }
}
