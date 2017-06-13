package com.orbotix.macro.cmd;


public class GoTo
  implements MacroCommand
{
  public static final byte COMMAND_ID = 12;
  
  private static final String a = "GoTo";
  private int b = 0;
  
  public GoTo(int identifier) {
    setIdentifier(identifier);
  }
  
  public GoTo(byte[] bytes) {
    this(bytes[1]);
  }
  
  public int getLength()
  {
    return 2;
  }
  
  public void setIdentifier(int identifier) {
    b = (identifier & 0xFF);
  }
  
  public int getIdentifier() {
    return b;
  }
  


  public byte[] getByteRepresentation()
  {
    return new byte[] {getCommandId(), (byte)getIdentifier() };
  }
  

  public String getName()
  {
    return "GoTo";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 12;
  }
  
  public String getSettingsString()
  {
    return new Integer(b).toString();
  }
}
