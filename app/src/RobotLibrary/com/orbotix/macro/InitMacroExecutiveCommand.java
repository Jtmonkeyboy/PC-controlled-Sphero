package com.orbotix.macro;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;



public class InitMacroExecutiveCommand
  extends DeviceCommand
{
  public InitMacroExecutiveCommand() {}
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.INIT_MACRO_EXECUTIVE.getValue();
  }
}
