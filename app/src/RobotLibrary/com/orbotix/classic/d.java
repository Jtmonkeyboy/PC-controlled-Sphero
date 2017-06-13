package com.orbotix.classic;

import com.orbotix.common.DLog;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.util.ByteArrayBuffer;





class d
  implements Runnable
{
  private static final String a = "OBX-ClassicLinkReadRunnable";
  private final b b;
  private boolean c;
  private a d;
  private InputStream e;
  
  d(a paramA, b paramB, InputStream paramInputStream)
  {
    c = false;
    b = paramB;
    d = paramA;
    e = paramInputStream;
  }
  
  public void run()
  {
    c = true;
    d.a();
    byte[] arrayOfByte1 = new byte[''];
    while (c)
    {
      try
      {
        int i = e.read(arrayOfByte1);
        if (i > 0) {
          ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(i);
          localByteArrayBuffer.append(arrayOfByte1, 0, i);
          byte[] arrayOfByte2 = localByteArrayBuffer.toByteArray();
          
          b.a(arrayOfByte2);
        }
      } catch (IOException localIOException) {
        DLog.e("Could not read bytes from stream");
        c = false;
      }
    }
    b();
  }
  
  void a() {
    c = false;
  }
  
  private void b() {
    e = null;
    d.b();
  }
  
  static abstract interface b
  {
    public abstract void a(byte[] paramArrayOfByte);
  }
  
  static abstract interface a
  {
    public abstract void a();
    
    public abstract void b();
  }
}
