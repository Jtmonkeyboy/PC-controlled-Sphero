//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.macro.cmd;

import com.orbotix.macro.cmd.MacroCommand;

public class RawMotor implements MacroCommand {
  public static final byte COMMAND_ID = 10;
  private static final String a = "Raw Motor";
  private int b;
  private int c;
  private int d;
  private int e;
  private int f;

  public RawMotor(RawMotor.DriveMode left_mode, int left_power, RawMotor.DriveMode right_mode, int right_power, int delay) {
    this.b = 1;
    this.c = 0;
    this.d = 1;
    this.e = 0;
    this.f = 0;
    this.setLeftDriveMode(left_mode);
    this.setLeftPower(left_power);
    this.setRightDriveMode(right_mode);
    this.setRightPower(right_power);
    this.setDelay(delay);
  }

  public RawMotor(byte[] bytes) {
    this(RawMotor.DriveMode.getDriveMode(bytes[1]), bytes[2], RawMotor.DriveMode.getDriveMode(bytes[3]), bytes[4], bytes[5]);
  }

  public void setLeftDriveMode(RawMotor.DriveMode mode) {
    this.b = mode.ordinal();
  }

  public void setLeftDriveMode(int mode) {
    this.b = mode < 0?0:mode;
    this.b = this.b > 4?4:this.b;
  }

  public RawMotor.DriveMode getLeftDriveMode() {
    return RawMotor.DriveMode.getDriveMode(this.b);
  }

  public int getLeftDriveModeId() {
    return this.b;
  }

  public void setRightDriveMode(RawMotor.DriveMode mode) {
    this.d = mode.ordinal();
  }

  public void setRightDriveMode(int mode) {
    this.d = mode < 0?0:mode;
    this.d = this.d > 4?0:this.d;
  }

  public RawMotor.DriveMode getRightDriveMode() {
    return RawMotor.DriveMode.getDriveMode(this.d);
  }

  public int getRightDriveModeId() {
    return this.d;
  }

  public void setLeftPower(int power) {
    this.c = power & 255;
  }

  public int getLeftPower() {
    return this.c;
  }

  public void setRightPower(int power) {
    this.e = power & 255;
  }

  public int getRightPower() {
    return this.e;
  }

  public void setDelay(int delay) {
    this.f = delay & 255;
  }

  public int getDelay() {
    return this.f;
  }

  public int getLength() {
    return 6;
  }

  public byte[] getByteRepresentation() {
    return new byte[]{this.getCommandId(), (byte)this.getLeftDriveModeId(), (byte)this.getLeftPower(), (byte)this.getRightDriveModeId(), (byte)this.getRightPower(), (byte)this.getDelay()};
  }

  public String getName() {
    return "Raw Motor";
  }

  public byte getCommandId() {
    return getCommandID();
  }

  public static byte getCommandID() {
    return 10;
  }

  public String getSettingsString() {
    return "Left: " + this.getLeftDriveMode() + " " + this.c + " Right: " + this.getRightDriveMode() + " " + this.getRightPower() + " " + this.getDelay();
  }

  public static enum DriveMode {
    OFF,
    FORWARD,
    REVERSE,
    BRAKE,
    IGNORE;

    private DriveMode() {
    }

    public static RawMotor.DriveMode getDriveMode(int ordinal) {
      RawMotor.DriveMode[] var1 = values();
      return var1[ordinal];
    }
  }
}
