//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.command;

import android.annotation.SuppressLint;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
@SuppressLint("ParcelCreator")
public class RawMotorCommand extends DeviceCommand {
  private final RawMotorCommand.MotorMode a;
  private final int b;
  private final RawMotorCommand.MotorMode c;
  private final int d;

  public RawMotorCommand(RawMotorCommand.MotorMode leftMode, int leftSpeed, RawMotorCommand.MotorMode rightMode, int rightSpeed) {
    this.a = leftMode;
    this.b = leftSpeed;
    this.c = rightMode;
    this.d = rightSpeed;
  }

  public RawMotorCommand.MotorMode getLeftMode() {
    return this.a;
  }

  public int getLeftSpeed() {
    return this.b;
  }

  public RawMotorCommand.MotorMode getRightMode() {
    return this.c;
  }

  public int getRightSpeed() {
    return this.d;
  }

  public byte getDeviceId() {
    return DeviceId.ROBOT.getValue();
  }

  public byte getCommandId() {
    return RobotCommandId.RAW_MOTOR.getValue();
  }

  public byte[] getData() {
    byte[] var1 = new byte[]{(byte)this.a.getValue(), (byte)this.b, (byte)this.c.getValue(), (byte)this.d};
    return var1;
  }

  public static enum MotorMode {
    MOTOR_MODE_OFF(0),
    MOTOR_MODE_FORWARD(1),
    MOTOR_MODE_REVERSE(2),
    MOTOR_MODE_BRAKE(3),
    MOTOR_MODE_IGNORE(4);

    private int a;

    private MotorMode(int value) {
      this.a = value;
    }

    public int getValue() {
      return this.a;
    }
  }
}
