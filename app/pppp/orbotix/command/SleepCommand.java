//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;

public class SleepCommand extends DeviceCommand {
  public static final byte COMMAND_ID = 34;
  public static final int DEEP_SLEEP_INTERVAL = 65535;
  private final int a;
  private final int b;

  public SleepCommand(int time, int identifier) {
    this.a = time;
    this.b = identifier;
    this.setResponseRequested(true);
  }

  public SleepCommand() {
    this(0, 0);
  }

  public int getWakeUpTime() {
    return this.a;
  }

  public int getWakeUpMacroId() {
    return this.b;
  }

  public byte getDeviceId() {
    return DeviceId.CORE.getValue();
  }

  public byte getCommandId() {
    return 34;
  }

  public byte[] getData() {
    byte[] var1 = new byte[]{(byte)(this.a >> 8), (byte)this.a, (byte)this.b};
    return var1;
  }

  public static enum SleepType {
    NORMAL(0),
    DEEP(1),
    LOW_POWER(2);

    private final byte a;

    private SleepType(int value) {
      this.a = (byte)value;
    }

    public static SleepCommand.SleepType sleepTypeForByte(byte b) {
      switch(b) {
        case 0:
          return NORMAL;
        case 1:
          return DEEP;
        default:
          throw new IllegalArgumentException(String.format("Byte %d is not a valid value for SleepType", new Object[]{Byte.valueOf(b)}));
      }
    }
  }
}
