package com.orbotix.command;

import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;
import com.orbotix.common.utilities.binary.ByteUtil;


public class GetPowerStateResponse
  extends DeviceResponse
{
  private int a;
  private PowerState b;
  private float c;
  private int d;
  private int e;
  
  protected GetPowerStateResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected int getRecordVersion() {
    return a;
  }
  




  public PowerState getPowerState()
  {
    return b;
  }
  




  public float getBatteryVoltage()
  {
    return c;
  }
  




  public int getNumberOfCharges()
  {
    return d;
  }
  




  public int getTimeSinceLastCharge()
  {
    return e;
  }
  
  protected void parseData()
  {
    super.parseData();
    
    if (getResponseCode() == ResponseCode.OK) {
      byte[] arrayOfByte = getPacket();
      a = ByteUtil.convertUnsignedToShort(arrayOfByte[5]);
      b = PowerState.stateForInt(ByteUtil.convertUnsignedToShort(arrayOfByte[6]));
      c = (ByteUtil.convertUnsignedToInt(arrayOfByte[7], arrayOfByte[8]) / 100.0F);
      
      d = ByteUtil.convertUnsignedToInt(arrayOfByte[9], arrayOfByte[10]);
      
      e = ByteUtil.convertUnsignedToInt(arrayOfByte[11], arrayOfByte[12]);
    }
  }
  


  public static enum PowerState
  {
    private byte a;
    


    public static PowerState stateForInt(int value)
    {
      for (PowerState localPowerState : ) {
        if (localPowerState.getValue() == value) {
          return localPowerState;
        }
      }
      DLog.e("Could not build power stat from int: " + value);
      return null;
    }
    
    private PowerState(int value) {
      a = ((byte)value);
    }
    
    public byte getValue() {
      return a;
    }
  }
}
