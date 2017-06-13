package com.orbotix.orbbasic;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.RobotCommandId;




public class OrbBasicAbortProgramCommand
  extends DeviceCommand
{
  public OrbBasicAbortProgramCommand() {}
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.ORB_BASIC_ABORT_PROGRAM.getValue();
  }
}
