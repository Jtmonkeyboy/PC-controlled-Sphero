package com.orbotix.macro.cmd;


public class RawMotor
  implements MacroCommand
{
  public static final byte COMMAND_ID = 10;
  
  private static final String a = "Raw Motor";
  private int b = 1;
  private int c = 0;
  private int d = 1;
  private int e = 0;
  private int f = 0;
  
  public RawMotor(DriveMode left_mode, int left_power, DriveMode right_mode, int right_power, int delay) {
    setLeftDriveMode(left_mode);
    setLeftPower(left_power);
    setRightDriveMode(right_mode);
    setRightPower(right_power);
    setDelay(delay);
  }
  
  public RawMotor(byte[] bytes) {
    this(DriveMode.getDriveMode(bytes[1]), bytes[2], DriveMode.getDriveMode(bytes[3]), bytes[4], bytes[5]);
  }
  
  public void setLeftDriveMode(DriveMode mode) {
    b = mode.ordinal();
  }
  
  public void setLeftDriveMode(int mode) {
    b = (mode < 0 ? 0 : mode);
    b = (b > 4 ? 4 : b);
  }
  
  public DriveMode getLeftDriveMode() {
    return DriveMode.getDriveMode(b);
  }
  
  public int getLeftDriveModeId() {
    return b;
  }
  
  public void setRightDriveMode(DriveMode mode) {
    d = mode.ordinal();
  }
  
  public void setRightDriveMode(int mode) {
    d = (mode < 0 ? 0 : mode);
    d = (d > 4 ? 0 : d);
  }
  
  public DriveMode getRightDriveMode() {
    return DriveMode.getDriveMode(d);
  }
  
  public int getRightDriveModeId() {
    return d;
  }
  
  public void setLeftPower(int power) {
    c = (power & 0xFF);
  }
  
  public int getLeftPower() {
    return c;
  }
  
  public void setRightPower(int power) {
    e = (power & 0xFF);
  }
  
  public int getRightPower() {
    return e;
  }
  
  public void setDelay(int delay) {
    f = (delay & 0xFF);
  }
  
  public int getDelay() {
    return f;
  }
  

  public int getLength()
  {
    return 6;
  }
  






  public byte[] getByteRepresentation()
  {
    return new byte[] {getCommandId(), (byte)getLeftDriveModeId(), (byte)getLeftPower(), (byte)getRightDriveModeId(), (byte)getRightPower(), (byte)getDelay() };
  }
  

  public String getName()
  {
    return "Raw Motor";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 10;
  }
  
  public String getSettingsString()
  {
    return "Left: " + getLeftDriveMode() + " " + c + " Right: " + getRightDriveMode() + " " + getRightPower() + " " + getDelay();
  }
  


  public static enum DriveMode
  {
    private DriveMode() {}
    

    public static DriveMode getDriveMode(int ordinal)
    {
      DriveMode[] arrayOfDriveMode = values();
      return arrayOfDriveMode[ordinal];
    }
  }
}
