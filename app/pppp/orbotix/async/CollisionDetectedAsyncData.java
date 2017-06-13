//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.sensor.Acceleration;

public class CollisionDetectedAsyncData extends AsyncMessage {
  private Acceleration a;
  private boolean b;
  private boolean c;
  private CollisionDetectedAsyncData.CollisionPower d;
  private float e;
  private long f;

  public CollisionDetectedAsyncData(byte[] packet) {
    super(packet);
  }

  protected void parseData() {
    super.parseData();
    byte[] var1 = this.getData();
    short var2 = (short)(((short)var1[0] << 8) + ((short)var1[1] & 255));
    short var3 = (short)(((short)var1[2] << 8) + ((short)var1[3] & 255));
    short var4 = (short)(((short)var1[4] << 8) + ((short)var1[5] & 255));
    this.a = new Acceleration(var2, var3, var4);
    this.b = (var1[6] & 1) > 0;
    this.c = (var1[6] & 2) > 0;
    short var5 = (short)(((short)var1[7] << 8) + ((short)var1[8] & 255));
    short var6 = (short)(((short)var1[9] << 8) + ((short)var1[10] & 255));
    this.d = new CollisionDetectedAsyncData.CollisionPower(var5, var6);
    this.e = (float)((short)var1[11] & 255) / 255.0F;
    this.f = ((long)var1[12] & 255L) << 24;
    this.f += ((long)var1[13] & 255L) << 16;
    this.f += ((long)var1[14] & 255L) << 8;
    this.f += (long)var1[15] & 255L;
  }

  public Acceleration getImpactAcceleration() {
    return this.a;
  }

  public boolean hasImpactXAxis() {
    return this.b;
  }

  public boolean hasImpactYAxis() {
    return this.c;
  }

  public CollisionDetectedAsyncData.CollisionPower getImpactPower() {
    return this.d;
  }

  public float getImpactSpeed() {
    return this.e;
  }

  public long getImpactTimeStamp() {
    return this.f;
  }

  public String toString() {
    return "CollisionDetectedAsyncData{Accel=" + this.a + ", ImpactX=" + this.b + ", ImpactY=" + this.c + ", ImpactPower=" + this.d + ", ImpactSpeed=" + this.e + ", ImpactTimeStamp=" + this.f + '}';
  }

  public static class CollisionPower {
    public final short x;
    public final short y;

    public CollisionPower(short x, short y) {
      this.x = x;
      this.y = y;
    }

    public boolean equals(Object o) {
      if(!(o instanceof CollisionDetectedAsyncData.CollisionPower)) {
        return false;
      } else {
        CollisionDetectedAsyncData.CollisionPower var2 = (CollisionDetectedAsyncData.CollisionPower)o;
        return var2.x == this.x && var2.y == this.y;
      }
    }

    public int hashCode() {
      short var1 = this.x;
      int var2 = 31 * var1 + this.y;
      return var2;
    }

    public String toString() {
      return "CollisionPower{x=" + this.x + ", y=" + this.y + '}';
    }
  }
}
