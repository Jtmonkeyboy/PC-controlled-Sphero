package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
























public class ConfigureCollisionDetectionCommand
  extends DeviceCommand
{
  public static final int DISABLE_DETECTION_METHOD = 0;
  public static final int DEFAULT_DETECTION_METHOD = 1;
  private final int a;
  private final int b;
  private final int c;
  private final int d;
  private final int e;
  private final int f;
  
  public ConfigureCollisionDetectionCommand(int xThreshold, int xSpeedThreshold, int yThreshold, int ySpeedThreshold, int deadTime)
  {
    a = 1;
    b = xThreshold;
    c = xSpeedThreshold;
    d = yThreshold;
    e = ySpeedThreshold;
    f = deadTime;
  }
  













  public ConfigureCollisionDetectionCommand(int detectionMethod, int xThreshold, int xSpeedThreshold, int yThreshold, int ySpeedThreshold, int deadTime)
  {
    a = detectionMethod;
    b = xThreshold;
    c = xSpeedThreshold;
    d = yThreshold;
    e = ySpeedThreshold;
    f = deadTime;
  }
  
  public static ConfigureCollisionDetectionCommand stopCommand() {
    return new ConfigureCollisionDetectionCommand(0, 0, 0, 0, 0);
  }
  





  public int getDetectionMethod()
  {
    return a;
  }
  




  public int getXThreshold()
  {
    return b;
  }
  





  public int getXSpeedThreshold()
  {
    return c;
  }
  




  public int getYThreshold()
  {
    return d;
  }
  





  public int getYSpeedThreshold()
  {
    return e;
  }
  





  public int getDeadTime()
  {
    return f;
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[6];
    arrayOfByte[0] = ((byte)a);
    arrayOfByte[1] = ((byte)b);
    arrayOfByte[2] = ((byte)c);
    arrayOfByte[3] = ((byte)d);
    arrayOfByte[4] = ((byte)e);
    arrayOfByte[5] = ((byte)f);
    
    return arrayOfByte;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.CONFIGURE_COLLISION_DETECTION.getValue();
  }
}
