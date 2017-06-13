package com.orbotix.ovalcompiler.response.async;

import android.support.annotation.Nullable;
import com.orbotix.common.DLog;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.utilities.binary.ByteUtil;

public class OvalDeviceBroadcast extends AsyncMessage
{
  private final int a = 4;
  private int[] b;
  private float[] c;
  
  public OvalDeviceBroadcast(byte[] packet) {
    super(packet);
  }
  
  protected void parseData()
  {
    byte[] arrayOfByte1 = ByteUtil.reverse(getData());
    int i = arrayOfByte1.length / 4;
    DLog.v("Oval async received %d values", new Object[] { Integer.valueOf(i) });
    c = new float[i];
    b = new int[i];
    for (int j = 0; j < i; j++) {
      byte[] arrayOfByte2 = new byte[4];
      System.arraycopy(arrayOfByte1, j * 4, arrayOfByte2, 0, arrayOfByte2.length);
      b[j] = ByteUtil.convertBytesTo32BitInt(arrayOfByte2);
      c[j] = Float.intBitsToFloat(b[j]);
    }
  }
  
  @Nullable
  public float[] getFloats() { return c; }
  
  @Nullable
  public int[] getInts() {
    return b;
  }
}
