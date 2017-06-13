package com.orbotix.macro.cmd;


public class Sleep
  implements MacroCommand
{
  public static final byte COMMAND_ID = 14;
  private static final String a = "Sleep";
  private int b = 0;
  
  public Sleep(int delay) {
    setDelay(delay);
  }
  
  public Sleep(byte[] bytes) {
    this(bytes[1]);
  }
  
  public void setDelay(int delay) {
    b = (delay & 0xFFFF);
  }
  
  public int getDelay() {
    return b;
  }
  
  public int getLength()
  {
    return 3;
  }
  



  public byte[] getByteRepresentation()
  {
    return new byte[] {getCommandId(), (byte)(getDelay() >> 8), (byte)(getDelay() & 0xFF) };
  }
  

  public String getName()
  {
    return "Sleep";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 14;
  }
  
  public String getSettingsString()
  {
    return new Integer(b).toString();
  }
}
