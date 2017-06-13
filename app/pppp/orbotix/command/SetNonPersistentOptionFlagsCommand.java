package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.common.utilities.binary.BitMask;
import com.orbotix.common.utilities.binary.Maskable;

public class SetNonPersistentOptionFlagsCommand
  extends DeviceCommand
{
  private BitMask<NonPersistentOptionFlags> a;
  
  public SetNonPersistentOptionFlagsCommand()
  {
    a = new BitMask(new Maskable[0]);
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.SET_TEMPORARY_OPTION_FLAGS.getValue();
  }
  





  public SetNonPersistentOptionFlagsCommand(BitMask<NonPersistentOptionFlags> mask)
  {
    a = mask;
  }
  

  public SetNonPersistentOptionFlagsCommand(NonPersistentOptionFlags... flags)
  {
    a = new BitMask(new Maskable[0]);
    
    for (NonPersistentOptionFlags localNonPersistentOptionFlags : flags) {
      a.addFlag(localNonPersistentOptionFlags);
    }
  }
  
  public BitMask<NonPersistentOptionFlags> getMask() {
    return a;
  }
  
  public byte[] getData()
  {
    return a.getByteArray();
  }
  
  public String toString()
  {
    return super.toString() + " " + getMask();
  }
}
