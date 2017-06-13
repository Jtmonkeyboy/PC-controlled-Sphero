//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.command;

import com.orbotix.common.utilities.binary.Maskable;

public enum NonPersistentOptionFlags implements Maskable {
  StopOnDisconnect(1L),
  CompatibilityMode(2L),
  None(0L);

  private final long a;

  private NonPersistentOptionFlags(long flag) {
    this.a = flag;
  }

  public long longValue() {
    return this.a;
  }
}
