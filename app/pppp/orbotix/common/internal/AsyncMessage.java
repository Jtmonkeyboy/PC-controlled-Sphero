//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.common.internal;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import com.orbotix.common.internal.DeviceMessage;

@SuppressLint("ParcelCreator")
public class AsyncMessage extends DeviceMessage {
  private static final int a = 5;

  public AsyncMessage(byte[] packet) {
    this.setPacket(packet);
    this.parseData();
  }

  protected void parseData() {
  }

  public String toString() {
    StringBuilder var1 = new StringBuilder("<AsyncMessage: ");
    var1.append(this.getType());
    var1.append(" l=").append(this.getPacket().length);
    var1.append(">");
    return var1.toString();
  }

  public byte getDeviceId() {
    return -1;
  }

  public byte getCommandId() {
    return -1;
  }

  @NonNull
  protected byte[] getData() {
    byte[] var1 = this.getPacket();
    int var2 = var1[3] << 8;
    var2 += var1[4] - 1;
    byte[] var3 = new byte[var2];

    for(int var4 = 0; var4 < var2; ++var4) {
      var3[var4] = this.getPacket()[5 + var4];
    }

    return var3;
  }

  public AsyncMessage.Type getType() {
    return AsyncMessage.Type.fromByte(this.getPacket()[2]);
  }

  public static enum Type {
    Error(0),
    PowerAsyncMessage(1),
    L1DiagnosticAsyncMessage(2),
    SensorAsyncMessage(3),
    ConfigBlockContents(4),
    WillSleepAsyncMessage(5),
    MacroEmitMarker(6),
    CollisionDetected(7),
    OrbBasicPrint(8),
    OrbBasicErrorASCII(9),
    OrbBasicErrorBinary(10),
    SelfLevelComplete(11),
    GyroLimitsExceeded(12),
    OvalErrorBroadcast(18),
    OvalDeviceBroadcast(19),
    DidSleepAsyncMessage(20);

    private final byte a;

    private Type(int value) {
      this.a = (byte)value;
    }

    public byte getValue() {
      return this.a;
    }

    public static AsyncMessage.Type fromByte(byte typeAsByte) {
      AsyncMessage.Type[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
        AsyncMessage.Type var4 = var1[var3];
        if(var4.getValue() == typeAsByte) {
          return var4;
        }
      }

      return Error;
    }
  }
}
