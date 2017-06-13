package com.orbotix.command;

import com.orbotix.common.internal.DeviceId;

public class GetFactoryConfigBlockCRCCommand extends com.orbotix.common.internal.DeviceCommand
{
  public GetFactoryConfigBlockCRCCommand() {}
  
  public byte getDeviceId() {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId()
  {
    return com.orbotix.common.internal.CoreCommandId.GET_FACTORY_CONFIG_BLOCK_CRC.getValue();
  }
}
