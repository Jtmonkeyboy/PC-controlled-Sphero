package com.orbotix.macro.cmd;

import java.io.Serializable;

public abstract interface MacroCommand
  extends Serializable
{
  public static final byte MAC_END = 0;
  public static final byte MAC_SD1 = 1;
  public static final byte MAC_SD2 = 2;
  public static final byte MAC_STABILIZATION = 3;
  public static final byte MAC_CALIBRATE = 4;
  public static final byte MAC_ROLL = 5;
  public static final byte MAC_ROLL_SD1 = 6;
  public static final byte MAC_RGB = 7;
  public static final byte MAC_RGB_SD2 = 8;
  public static final byte MAC_FRONT_LED = 9;
  public static final byte MAC_RAW_MOTOR = 10;
  public static final byte MAC_DELAY = 11;
  public static final byte MAC_GOTO = 12;
  public static final byte MAC_GOSUB = 13;
  public static final byte MAC_SLEEP = 14;
  public static final byte MAC_SPD1 = 15;
  public static final byte MAC_SPD2 = 16;
  public static final byte MAC_ROLL_SPD1_SD1 = 17;
  public static final byte MAC_ROLL_SPD2_SD1 = 18;
  public static final byte MAC_ROTATION_RATE = 19;
  public static final byte MAC_FADE = 20;
  public static final byte MAC_EMIT = 21;
  public static final byte MAC_WAIT_UNTIL_STOP = 25;
  public static final byte MAC_ROTATE = 26;
  public static final byte MAC_STREAM_END = 27;
  public static final byte MAC_STOP = 37;
  
  public abstract int getLength();
  
  public abstract byte[] getByteRepresentation();
  
  public abstract String getName();
  
  public abstract byte getCommandId();
  
  public abstract String getSettingsString();
}
