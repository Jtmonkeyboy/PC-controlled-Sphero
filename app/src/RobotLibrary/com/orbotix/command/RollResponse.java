package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.RobotCommandId;






public final class RollResponse
  extends DeviceResponse
{
  protected RollResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.ROLL.getValue();
  }
}
