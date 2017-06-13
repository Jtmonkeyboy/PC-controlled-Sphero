package com.orbotix.macro.cmd;



public class BackLED
  implements MacroCommand
{
  public static final byte COMMAND_ID = 9;
  

  private static final String a = "Back LED";
  

  private int b = 0;
  private int c = 0;
  





  public BackLED(int intensity, int delay)
  {
    setIntensity(intensity);
    setDelay(delay);
  }
  




  public BackLED(byte[] bytes)
  {
    this(bytes[1], bytes[2]);
  }
  





  public int getLength()
  {
    return 3;
  }
  




  public void setIntensity(int intensity)
  {
    b = (intensity & 0xFF);
  }
  




  public int getIntensity()
  {
    return b;
  }
  




  public void setDelay(int delay)
  {
    c = (delay & 0xFF);
  }
  




  public int getDelay()
  {
    return c;
  }
  








  public byte[] getByteRepresentation()
  {
    return new byte[] {getCommandId(), (byte)getIntensity(), (byte)getDelay() };
  }
  






  public String getName()
  {
    return "Back LED";
  }
  




  public byte getCommandId()
  {
    return getCommandID();
  }
  



  public static byte getCommandID()
  {
    return 9;
  }
  





  public String getSettingsString()
  {
    return b + " " + c;
  }
}
