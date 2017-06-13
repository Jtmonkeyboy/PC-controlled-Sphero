//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.sensor.DeviceSensorsData;
import com.orbotix.common.utilities.binary.BitMask;
import java.util.ArrayList;

public class DeviceSensorAsyncMessage extends AsyncMessage {
  private static final String a = "mask";
  private static final String b = "frameCount";
  private static final String c = "dataFrames";
  public static long sMask = 0L;
  public static int sPacketFrames = 0;
  private byte[] d;
  private ArrayList<DeviceSensorsData> e;

  public DeviceSensorAsyncMessage(byte[] packet) {
    super(packet);
  }

  protected void parseData() {
    super.parseData();
    this.d = this.getData();
    if(sMask != 0L) {
      int var1 = this.a(sMask) * 2;
      if(this.d.length / sPacketFrames < var1) {
        sMask &= 4294967295L;
        var1 = this.a(sMask) * 2;
      }

      int var2 = this.d.length / var1;
      this.e = new ArrayList(var2);

      for(int var3 = 0; var3 < var2; ++var3) {
        byte[] var4 = new byte[var1];
        System.arraycopy(this.d, var3 * var1, var4, 0, var1);
        BitMask var5 = new BitMask(sMask);
        DeviceSensorsData var6 = new DeviceSensorsData(var5, var4);
        this.e.add(var6);
      }

    }
  }

  public byte[] getRawData() {
    return this.d;
  }

  public ArrayList<DeviceSensorsData> getAsyncData() {
    return this.e;
  }

  private int a(long var1) {
    int var3 = 0;

    for(int var4 = 0; var4 < 64; ++var4) {
      if((1L & var1 >> var4) == 1L) {
        ++var3;
      }
    }

    return var3;
  }

  public String toString() {
    StringBuilder var1 = new StringBuilder();
    byte[] var2 = this.d;
    int var3 = var2.length;

    for(int var4 = 0; var4 < var3; ++var4) {
      byte var5 = var2[var4];
      var1.append(String.format("%02X ", new Object[]{Byte.valueOf(var5)}));
    }

    return "DeviceSensorsAsyncData{mRawData=" + var1.toString() + ", mAsyncData=" + this.e + '}';
  }
}
