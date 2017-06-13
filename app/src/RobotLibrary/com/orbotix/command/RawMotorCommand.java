package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;












public class RawMotorCommand
  extends DeviceCommand
{
  private final MotorMode a;
  private final int b;
  private final MotorMode c;
  private final int d;
  
  public static enum MotorMode
  {
    private int a;
    
    private MotorMode(int value)
    {
      a = value;
    }
    
    public int getValue() {
      return a;
    }
  }
  













  public RawMotorCommand(MotorMode leftMode, int leftSpeed, MotorMode rightMode, int rightSpeed)
  {
    a = leftMode;
    b = leftSpeed;
    c = rightMode;
    d = rightSpeed;
  }
  





  public MotorMode getLeftMode()
  {
    return a;
  }
  




  public int getLeftSpeed()
  {
    return b;
  }
  




  public MotorMode getRightMode()
  {
    return c;
  }
  




  public int getRightSpeed()
  {
    return d;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.RAW_MOTOR.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[4];
    
    arrayOfByte[0] = ((byte)a.getValue());
    arrayOfByte[1] = ((byte)b);
    arrayOfByte[2] = ((byte)c.getValue());
    arrayOfByte[3] = ((byte)d);
    
    return arrayOfByte;
  }
}
