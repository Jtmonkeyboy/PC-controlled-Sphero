package com.orbotix.le;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;

class c implements Runnable
{
  private static final long a = 1000L;
  private BluetoothAdapter b;
  private BluetoothAdapter.LeScanCallback c;
  private boolean d = false;
  
  public c(BluetoothAdapter paramBluetoothAdapter, BluetoothAdapter.LeScanCallback paramLeScanCallback) {
    b = paramBluetoothAdapter;
    c = paramLeScanCallback;
  }
  
  public void run()
  {
    d = true;
    for (;;) {
      if (!d) {
        com.orbotix.common.DLog.v("Discovery runnable no longer running");
        return;
      }
      b.startLeScan(c);
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException localInterruptedException) {
        com.orbotix.common.DLog.v("Discovery runnable interrupted");
        return;
      }
      b.stopLeScan(c);
    }
  }
  
  public void a() {
    com.orbotix.common.DLog.v("Interrupted discovery runnable");
    d = false;
  }
}
