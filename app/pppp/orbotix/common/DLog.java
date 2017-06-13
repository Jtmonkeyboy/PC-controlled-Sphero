package com.orbotix.common;

import android.util.Log;


public class DLog
{
  private static final Boolean a = Boolean.valueOf(true);
  public static final String TAG = "obx-sdk";
  
  public DLog() {}
  
  public static void v(String message) { if (a.booleanValue()) Log.v("obx-sdk", Thread.currentThread().getName() + " \t:: " + message);
  }
  
  public static void v(String format, Object... params) {
    v(String.format(format, params));
  }
  
  public static void d(String message) {
    if (a.booleanValue()) Log.d("obx-sdk", Thread.currentThread().getName() + " \t:: " + message);
  }
  
  public static void d(String format, Object... params) {
    d(String.format(format, params));
  }
  
  public static void i(String message) {
    Log.i("obx-sdk", Thread.currentThread().getName() + " \t:: " + message);
  }
  
  public static void i(String format, Object... params) {
    i(String.format(format, params));
  }
  
  public static void w(String message) {
    Log.w("obx-sdk", Thread.currentThread().getName() + " \t:: " + message);
  }
  
  public static void w(String format, Object... params) {
    w(String.format(format, params));
  }
  
  public static void e(String message) {
    Log.e("obx-sdk", Thread.currentThread().getName() + " \t:: " + message);
  }
  
  public static void e(String format, Object... params) {
    e(String.format(format, params));
  }
}
