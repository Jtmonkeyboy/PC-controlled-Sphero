package com.orbotix.macro.cmd;


public class Stabilization
  implements MacroCommand
{
  public static final byte COMMAND_ID = 3;
  
  private static final String a = "Stabilization";
  private boolean b = true;
  private int c = 0;
  
  public Stabilization(boolean stabilized, int delay) {
    setStabilization(stabilized);
    setDelay(delay);
  }
  
  public Stabilization(byte[] bytes) {
    this(bytes[1] == 1, bytes[2]);
  }
  
  public int getLength()
  {
    return 3;
  }
  
  public void setStabilization(boolean stabilized) {
    b = stabilized;
  }
  
  public boolean getIsStabilized() {
    return b;
  }
  
  public void setDelay(int delay) {
    c = (delay & 0xFF);
  }
  
  public int getDelay() {
    return c;
  }
  



  public byte[] getByteRepresentation()
  {
    return new byte[] {getCommandID(), (byte)(getIsStabilized() ? 1 : 0), (byte)getDelay() };
  }
  

  public String getName()
  {
    return "Stabilization";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 3;
  }
  

  public String getSettingsString()
  {
    return "OFF " + c;
  }
}
