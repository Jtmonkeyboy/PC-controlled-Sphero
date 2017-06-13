package com.orbotix.command;

import com.orbotix.command.sphero.PersistentOptionFlags;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.common.utilities.binary.BitMask;




















public class SetOptionFlagsCommand
  extends DeviceCommand
{
  private final long a;
  
  @Deprecated
  public SetOptionFlagsCommand(long optionFlags)
  {
    a = optionFlags;
  }
  
  public SetOptionFlagsCommand(BitMask<PersistentOptionFlags> mask)
  {
    a = mask.longValue();
  }
  




  public long getOptionFlags()
  {
    return a;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.SET_OPTION_FLAGS.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = ((byte)(int)(a >> 24));
    arrayOfByte[1] = ((byte)(int)(a >> 16));
    arrayOfByte[2] = ((byte)(int)(a >> 8));
    arrayOfByte[3] = ((byte)(int)a);
    return arrayOfByte;
  }
  


  public String toString()
  {
    return "OptionFlagsCommand{optionFlags=" + String.format("%08x", new Object[] { Long.valueOf(a) }) + '}';
  }
}
