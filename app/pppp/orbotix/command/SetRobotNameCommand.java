package com.orbotix.command;

import com.orbotix.common.internal.CoreCommandId;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class SetRobotNameCommand
  extends DeviceCommand
{
  private final String a;
  
  public SetRobotNameCommand(String name)
  {
    a = name;
  }
  
  public String getName() {
    return a;
  }
  
  public byte getDeviceId()
  {
    return DeviceId.CORE.getValue();
  }
  
  public byte getCommandId()
  {
    return CoreCommandId.SET_BLUETOOTH_NAME.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte1 = new byte[48];
    
    Arrays.fill(arrayOfByte1, (byte)0);
    byte[] arrayOfByte2 = null;
    try {
      arrayOfByte2 = a.getBytes("UTF-8");
    } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
      localUnsupportedEncodingException.printStackTrace();
      return null;
    }
    for (int i = 0; (i < 48) && (i < arrayOfByte2.length); i++) {
      arrayOfByte1[i] = arrayOfByte2[i];
    }
    return arrayOfByte1;
  }
}
