package com.orbotix.macro;




public class SaveTemporaryMacroChunkCommand
  extends SaveTemporaryMacroCommand
{
  public static final byte COMMAND_ID = 88;
  

  private boolean a = false;
  
  public SaveTemporaryMacroChunkCommand(byte flags, byte[] macro) {
    super(flags, macro);
  }
  
  public byte getCommandId()
  {
    return 88;
  }
  

  public byte[] getData()
  {
    if (a) {
      return super.getData();
    }
    
    return (byte[])macroData.clone();
  }
  

  public void setIsFirst(boolean val)
  {
    a = val;
  }
}
