package com.orbotix.command;

import android.support.annotation.NonNull;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.common.utilities.math.Value;



public final class RollCommand
  extends DeviceCommand
{
  private final float a;
  private final float b;
  private final State c;
  
  public static enum State
  {
    private byte a;
    
    private State(int value)
    {
      a = ((byte)value);
    }
    
    public byte getValue() {
      return a;
    }
    
    public static State stateWithByte(byte b) {
      if (STOP.getValue() == b) return STOP;
      if (GO.getValue() == b) return GO;
      if (CALIBRATE.getValue() == b) return CALIBRATE;
      return null;
    }
  }
  










  public RollCommand(float heading, float velocity)
  {
    this(heading, velocity, State.GO);
  }
  







  public RollCommand(float heading, float velocity, @NonNull State state)
  {
    if (state == State.STOP) {
      b = 0.0F;
    } else {
      b = ((float)Value.clamp(velocity, 0.0D, 1.0D));
    }
    
    c = state;
    a = ((int)heading % 360);
    setResponseRequested(false);
  }
  
  public RollCommand(byte[] packet)
  {
    b = (packet[6] / 255.0F);
    a = ((packet[7] << 8) + packet[8]);
    c = State.stateWithByte(packet[9]);
    setResponseRequested(false);
  }
  
  public float getHeading() {
    return a;
  }
  




  public float getVelocity()
  {
    return b;
  }
  
  public State getState() {
    return c;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  


  public byte getCommandId()
  {
    return RobotCommandId.ROLL.getValue();
  }
  
  public byte[] getData() {
    byte[] arrayOfByte = new byte[4];
    
    arrayOfByte[0] = ((byte)(int)(b * 255.0D));
    arrayOfByte[1] = ((byte)((int)a >> 8));
    arrayOfByte[2] = ((byte)(int)a);
    arrayOfByte[3] = c.getValue();
    
    return arrayOfByte;
  }
  
  public String toString()
  {
    return "<" + super.toString() + " h:" + a + " v:" + b + " " + c;
  }
}
