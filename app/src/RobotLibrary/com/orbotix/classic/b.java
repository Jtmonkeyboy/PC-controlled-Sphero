package com.orbotix.classic;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.orbotix.common.DLog;
import java.io.IOException;
import java.util.UUID;




class b
  implements Runnable
{
  private static final UUID a = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
  
  private static final int b = 2;
  private static final int c = 300;
  private a d;
  private BluetoothDevice e;
  private int f;
  
  b(BluetoothDevice paramBluetoothDevice, a paramA)
  {
    e = paramBluetoothDevice;
    d = paramA;
    f = 0;
  }
  

  public void run()
  {
    BluetoothSocket localBluetoothSocket;
    try
    {
      localBluetoothSocket = e.createInsecureRfcommSocketToServiceRecord(a);
      DLog.v("Socket successfully created");
    }
    catch (IOException localIOException1) {
      DLog.e("Socket to device could not be created");
      d.b(); return;
    }
    
    do
    {
      try
      {
        d.a();
        localBluetoothSocket.connect();
        DLog.v("Connection success!");
        d.a(localBluetoothSocket);
        return;
      }
      catch (IOException localIOException2) {
        DLog.w("Connection failure: " + localIOException2.getMessage());
        f += 1;
        try {
          Thread.sleep(300L);
        }
        catch (InterruptedException localInterruptedException) {
          DLog.e("Connection thread wait interrupted, failing out");
          break;
        }
      }
    } while (f < 2);
    
    DLog.e("Connection retries exceeded, connection failed.");
    d.b();
    try {
      localBluetoothSocket.close();
    }
    catch (IOException localIOException3) {
      DLog.e("Could not close bluetooth socket post failure");
    }
  }
  
  static abstract interface a
  {
    public abstract void a();
    
    public abstract void a(BluetoothSocket paramBluetoothSocket);
    
    public abstract void b();
  }
}
