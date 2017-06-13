package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import java.util.ArrayList;
import java.util.List;


public class FlashWritingCommandDetector
{
  private static List<Byte> a = new ArrayList() {};
  






  private static List<Byte> b = new ArrayList() {};
  





  private static List<Byte> c = new ArrayList() {};
  










  public FlashWritingCommandDetector() {}
  










  public static boolean isFlashWritingCommand(DeviceCommand command)
  {
    switch (command.getDeviceId()) {
    case 0: 
      return a.contains(Byte.valueOf(command.getCommandId()));
    case 1: 
      return b.contains(Byte.valueOf(command.getCommandId()));
    case 2: 
      return c.contains(Byte.valueOf(command.getCommandId()));
    }
    return false;
  }
}
