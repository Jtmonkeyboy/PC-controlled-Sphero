package com.orbotix.command;

import com.orbotix.common.internal.DeviceResponse;

public class GetChargerStateResponse extends DeviceResponse
{
  private GetPowerStateResponse.PowerState a;
  private ChargerState b;
  
  protected GetChargerStateResponse(byte[] packet, com.orbotix.common.internal.DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected void parseData()
  {
    super.parseData();
    byte[] arrayOfByte = getPacket();
    int i = arrayOfByte[5];
    int j = i >> 4;
    int k = i & 0xF;
    
    a = GetPowerStateResponse.PowerState.stateForInt(j);
    b = ChargerState.stateForInt(k);
  }
  
  public GetPowerStateResponse.PowerState getPowerState() {
    return a;
  }
  
  public ChargerState getChargerState() {
    return b;
  }
  

  public static enum ChargerState
  {
    private byte a;
    

    public static ChargerState stateForInt(int value)
    {
      for (ChargerState localChargerState : ) {
        if (localChargerState.getValue() == value) {
          return localChargerState;
        }
      }
      com.orbotix.common.DLog.e("Could not make a ChargerState from int: " + value);
      return null;
    }
    
    private ChargerState(int value) {
      a = ((byte)value);
    }
    
    public byte getValue() { return a; }
  }
}
