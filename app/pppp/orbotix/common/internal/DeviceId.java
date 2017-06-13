//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.common.internal;

public enum DeviceId {
  CORE(0),
  BOOTLOADER(1),
  ROBOT(2);

  private byte a;

  private DeviceId(int value) {
    this.a = (byte)value;
  }

  public byte getValue() {
    return this.a;
  }
}
