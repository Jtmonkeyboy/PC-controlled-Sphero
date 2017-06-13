package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.sensor.DeviceSensorsData;
import com.orbotix.common.utilities.binary.BitMask;
import java.util.ArrayList;












public class DeviceSensorAsyncMessage
  extends AsyncMessage
{
  private static final String a = "mask";
  private static final String b = "frameCount";
  private static final String c = "dataFrames";
  public static long sMask = 0L;
  public static int sPacketFrames = 0;
  

  private byte[] d;
  

  private ArrayList<DeviceSensorsData> e;
  


  public DeviceSensorAsyncMessage(byte[] packet)
  {
    super(packet);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    d = getData();
    

    if (sMask == 0L)
    {
      return;
    }
    


    int i = a(sMask) * 2;
    




    if (d.length / sPacketFrames < i)
    {
      sMask &= 0xFFFFFFFF;
      i = a(sMask) * 2;
    }
    

    int j = d.length / i;
    

    e = new ArrayList(j);
    for (int k = 0; k < j; k++) {
      byte[] arrayOfByte = new byte[i];
      System.arraycopy(d, k * i, arrayOfByte, 0, i);
      BitMask localBitMask = new BitMask(sMask);
      DeviceSensorsData localDeviceSensorsData = new DeviceSensorsData(localBitMask, arrayOfByte);
      e.add(localDeviceSensorsData);
    }
  }
  












  public byte[] getRawData()
  {
    return d;
  }
  





  public ArrayList<DeviceSensorsData> getAsyncData()
  {
    return e;
  }
  




  private int a(long paramLong)
  {
    int i = 0;
    for (int j = 0; j < 64; j++) {
      if ((1L & paramLong >> j) == 1L) i++;
    }
    return i;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (byte b1 : d) {
      localStringBuilder.append(String.format("%02X ", new Object[] { Byte.valueOf(b1) }));
    }
    
    return "DeviceSensorsAsyncData{mRawData=" + localStringBuilder.toString() + ", mAsyncData=" + e + '}';
  }
}
