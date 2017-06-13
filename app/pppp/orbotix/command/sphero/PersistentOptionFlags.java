//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.command.sphero;

import com.orbotix.common.utilities.binary.Maskable;

public enum PersistentOptionFlags implements Maskable {
  PreventSleepInCharger(1L),
  EnableVectorDrive(2L),
  DisableSelfLevelInCharger(4L),
  EnablePersistentTailLight(8L),
  EnableMotionTimeout(16L),
  DefaultSettings(2L);

  private final long a;

  private PersistentOptionFlags(long flag) {
    this.a = flag;
  }

  public long longValue() {
    return this.a;
  }
}
