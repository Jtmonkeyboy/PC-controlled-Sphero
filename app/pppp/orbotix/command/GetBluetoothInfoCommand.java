package com.orbotix.command;

import com.orbotix.common.internal.CoreCommandId;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;





public class GetBluetoothInfoCommand
  extends DeviceCommand
{
  public GetBluetoothInfoCommand() {}
  
  public byte getDeviceId()
  {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId()
  {
    return CoreCommandId.GET_BLUETOOTH_INFO.getValue();
  }
}
