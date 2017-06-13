package com.orbotix.macro.cmd;


public class LoopEnd
  implements MacroCommand
{
  public static final int COMMAND_ID = 31;
  
  private static final String a = "Loop End";
  

  public LoopEnd() {}
  
  public LoopEnd(byte[] bytes) {}
  
  public int getLength()
  {
    return 1;
  }
  

  public byte[] getByteRepresentation()
  {
    return new byte[] {getCommandID() };
  }
  

  public String getName()
  {
    return "Loop End";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 31;
  }
  
  public String getSettingsString()
  {
    return "";
  }
}
