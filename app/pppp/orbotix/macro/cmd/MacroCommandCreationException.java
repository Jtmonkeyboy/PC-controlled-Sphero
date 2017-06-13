package com.orbotix.macro.cmd;




public class MacroCommandCreationException
  extends Exception
{
  public MacroCommandCreationException(String message)
  {
    super(message);
  }
  
  public MacroCommandCreationException(String message, Throwable t) {
    super(message, t);
  }
}
