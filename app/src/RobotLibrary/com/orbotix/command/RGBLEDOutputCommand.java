package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.common.utilities.math.Value;







public final class RGBLEDOutputCommand
  extends DeviceCommand
{
  private final int a;
  private final int b;
  private final int c;
  private final boolean d;
  private static int e;
  private static int f;
  private static int g;
  private static boolean h;
  
  public RGBLEDOutputCommand(float red, float green, float blue)
  {
    this((int)(Value.clamp(red, 0.0F, 1.0F) * 255.0F), 
      (int)(Value.clamp(green, 0.0F, 1.0F) * 255.0F), 
      (int)(Value.clamp(blue, 0.0F, 1.0F) * 255.0F));
  }
  







  @Deprecated
  public RGBLEDOutputCommand(int r, int g, int b)
  {
    this(r, g, b, false);
  }
  









  public RGBLEDOutputCommand(int r, int g, int b, boolean userDefault)
  {
    a = Value.clamp(r, 0, 255);
    this.b = Value.clamp(g, 0, 255);
    c = Value.clamp(b, 0, 255);
    d = userDefault;
    setResponseRequested(false);
  }
  







  public RGBLEDOutputCommand(byte[] packet)
  {
    a = packet[6];
    b = packet[7];
    c = packet[8];
    d = (packet[9] == 1);
    setResponseRequested(false);
  }
  





  public static int getCurrentRed()
  {
    return e;
  }
  
  protected static void setCurrentRed(int red) {
    e = red;
  }
  





  public static int getCurrentGreen()
  {
    return f;
  }
  
  protected static void setCurrentGreen(int green) {
    f = green;
  }
  





  public static int getCurrentBlue()
  {
    return g;
  }
  
  protected static void setCurrentBlue(int blue) {
    g = blue;
  }
  





  public static boolean isCurrentColorUserDefault()
  {
    return h;
  }
  
  protected static void setIsCurrentColorUserDefault(boolean isColorUserDefault) {
    h = isColorUserDefault;
  }
  




  public int getRed()
  {
    return a;
  }
  




  public int getGreen()
  {
    return b;
  }
  




  public int getBlue()
  {
    return c;
  }
  




  public boolean isUserDefault()
  {
    return d;
  }
  

  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.RGB_LED_OUTPUT.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[4];
    
    arrayOfByte[0] = ((byte)a);
    arrayOfByte[1] = ((byte)b);
    arrayOfByte[2] = ((byte)c);
    arrayOfByte[3] = ((byte)(d ? 1 : 0));
    
    return arrayOfByte;
  }
}
