//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.command;

import android.support.annotation.NonNull;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.common.utilities.math.Value;

public final class RollCommand extends DeviceCommand {
  private final float a;
  private final float b;
  private final RollCommand.State c;

  public RollCommand(float heading, float velocity) {
    this(heading, velocity, RollCommand.State.GO);
  }

  public RollCommand(float heading, float velocity, @NonNull RollCommand.State state) {
    if(state == RollCommand.State.STOP) {
      this.b = 0.0F;
    } else {
      this.b = (float)Value.clamp((double)velocity, 0.0D, 1.0D);
    }

    this.c = state;
    this.a = (float)((int)heading % 360);
    this.setResponseRequested(false);
  }

  public RollCommand(byte[] packet) {
    this.b = (float)packet[6] / 255.0F;
    this.a = (float)((packet[7] << 8) + packet[8]);
    this.c = RollCommand.State.stateWithByte(packet[9]);
    this.setResponseRequested(false);
  }

  public float getHeading() {
    return this.a;
  }

  public float getVelocity() {
    return this.b;
  }

  public RollCommand.State getState() {
    return this.c;
  }

  public byte getDeviceId() {
    return DeviceId.ROBOT.getValue();
  }

  public byte getCommandId() {
    return RobotCommandId.ROLL.getValue();
  }

  public byte[] getData() {
    byte[] var1 = new byte[]{(byte)((int)((double)this.b * 255.0D)), (byte)((int)this.a >> 8), (byte)((int)this.a), this.c.getValue()};
    return var1;
  }

  public String toString() {
    return "<" + super.toString() + " h:" + this.a + " v:" + this.b + " " + this.c;
  }

  public static enum State {
    STOP(0),
    GO(1),
    CALIBRATE(2);

    private byte a;

    private State(int value) {
      this.a = (byte)value;
    }

    public byte getValue() {
      return this.a;
    }

    public static RollCommand.State stateWithByte(byte b) {
      return STOP.getValue() == b?STOP:(GO.getValue() == b?GO:(CALIBRATE.getValue() == b?CALIBRATE:null));
    }
  }
}
