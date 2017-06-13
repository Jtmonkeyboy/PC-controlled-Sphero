package com.orbotix.command;

import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.RobotCommandId;

public final class StabilizationResponse extends DeviceResponse
{
  protected StabilizationResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command)
  {
    super(packet, command);
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.STABILIZATION.getValue();
  }
}
