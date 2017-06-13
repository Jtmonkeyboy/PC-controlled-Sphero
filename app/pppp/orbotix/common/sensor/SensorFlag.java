//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.common.sensor;

import com.orbotix.common.utilities.binary.Maskable;

public enum SensorFlag implements Maskable {
  NONE(0L),
  ACCELEROMETER_NORMALIZED(57344L),
  GYRO_NORMALIZED(7168L),
  ATTITUDE(458752L, 1.0F),
  LOCATOR(864691128455135232L, 1.17F),
  VELOCITY(108086391056891904L, 1.17F),
  MOTOR_BACKEMF_NORMALIZED(96L),
  QUATERNION(-1152921504606846976L, 1.17F);

  private final long a;
  private final float b;

  private SensorFlag(long flag) {
    this.a = flag;
    this.b = 0.9F;
  }

  private SensorFlag(long flag, float param4) {
    this.a = flag;
    this.b = minFirmwareVersion;
  }

  public long longValue() {
    return this.a;
  }
}
