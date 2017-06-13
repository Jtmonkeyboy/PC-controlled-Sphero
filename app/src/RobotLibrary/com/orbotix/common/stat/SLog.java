package com.orbotix.common.stat;

import android.util.Log;

public class SLog { public SLog() {}
  
  private static final Boolean a = Boolean.valueOf(false);
  
  public static void log(String format, Object... params) {
    log(String.format(format, params));
  }
  
  public static void log(String message) {
    if (a.booleanValue()) Log.v("obx-stat", message);
  }
}
