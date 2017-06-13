//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.command;

import com.orbotix.command.GetPowerStateResponse.PowerState;
import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;

public class GetChargerStateResponse extends DeviceResponse {
  private PowerState a;
  private GetChargerStateResponse.ChargerState b;

  protected GetChargerStateResponse(byte[] packet, DeviceCommand command) {
    super(packet, command);
  }

  protected void parseData() {
    super.parseData();
    byte[] var1 = this.getPacket();
    byte var2 = var1[5];
    int var3 = var2 >> 4;
    int var4 = var2 & 15;
    this.a = PowerState.stateForInt(var3);
    this.b = GetChargerStateResponse.ChargerState.stateForInt(var4);
  }

  public PowerState getPowerState() {
    return this.a;
  }

  public GetChargerStateResponse.ChargerState getChargerState() {
    return this.b;
  }

  public static enum ChargerState {
    UNKNOWN(0),
    OUT_OF_CHARGER(1),
    IN_CHARGER(2);

    private byte a;

    public static GetChargerStateResponse.ChargerState stateForInt(int value) {
      GetChargerStateResponse.ChargerState[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
        GetChargerStateResponse.ChargerState var4 = var1[var3];
        if(var4.getValue() == value) {
          return var4;
        }
      }

      DLog.e("Could not make a ChargerState from int: " + value);
      return null;
    }

    private ChargerState(int value) {
      this.a = (byte)value;
    }

    public byte getValue() {
      return this.a;
    }
  }
}
