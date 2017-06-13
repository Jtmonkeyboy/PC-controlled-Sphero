package com.orbotix.macro.cmd;


public class LoopStart
  implements MacroCommand
{
  public static final byte COMMAND_ID = 30;
  
  private static final String a = "Loop Start";
  private int b = 0;
  
  public LoopStart(int count) {
    setCount(count);
  }
  
  public LoopStart(byte[] bytes) {
    this(bytes[1]);
  }
  
  public void setCount(int count) {
    b = (count & 0xFF);
  }
  
  public int getCount() {
    return b;
  }
  
  public int getLength()
  {
    return 2;
  }
  


  public byte[] getByteRepresentation()
  {
    return new byte[] {getCommandId(), (byte)(getCount() & 0xFF) };
  }
  

  public String getName()
  {
    return "Loop Start";
  }
  
  public byte getCommandId()
  {
    return 30;
  }
  
  public static byte getCommandID() {
    return 30;
  }
  
  public String getSettingsString()
  {
    return new Integer(b).toString();
  }
}
