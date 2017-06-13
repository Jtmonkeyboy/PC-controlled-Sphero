//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.async;

import com.orbotix.common.DLog;
import com.orbotix.common.internal.AsyncMessage;

public class GyroLimitsExceededAsyncData extends AsyncMessage {
  protected GyroLimitsExceededAsyncData(byte[] packet) {
    super(packet);
    DLog.v("Hooray you exceeded the gyro limits!");
  }
}
