package com.orbotix.command;

import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.RobotCommandId;

public final class RGBLEDOutputResponse extends DeviceResponse
{
  public RGBLEDOutputResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command)
  {
    super(packet, command);
  }
  














  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.RGB_LED_OUTPUT.getValue();
  }
}
