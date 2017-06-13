//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.command;

import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;
import com.orbotix.common.utilities.binary.ByteUtil;

public class GetPowerStateResponse extends DeviceResponse {
  private int a;
  private GetPowerStateResponse.PowerState b;
  private float c;
  private int d;
  private int e;

  protected GetPowerStateResponse(byte[] packet, DeviceCommand command) {
    super(packet, command);
  }

  protected int getRecordVersion() {
    return this.a;
  }

  public GetPowerStateResponse.PowerState getPowerState() {
    return this.b;
  }

  public float getBatteryVoltage() {
    return this.c;
  }

  public int getNumberOfCharges() {
    return this.d;
  }

  public int getTimeSinceLastCharge() {
    return this.e;
  }

  protected void parseData() {
    super.parseData();
    if(this.getResponseCode() == ResponseCode.OK) {
      byte[] var1 = this.getPacket();
      this.a = ByteUtil.convertUnsignedToShort(var1[5]);
      this.b = GetPowerStateResponse.PowerState.stateForInt(ByteUtil.convertUnsignedToShort(var1[6]));
      this.c = (float)ByteUtil.convertUnsignedToInt(var1[7], var1[8]) / 100.0F;
      this.d = ByteUtil.convertUnsignedToInt(var1[9], var1[10]);
      this.e = ByteUtil.convertUnsignedToInt(var1[11], var1[12]);
    }

  }

  public static enum PowerState {
    CHARGING(1),
    OK(2),
    LOW(3),
    CRITICAL(4);

    private byte a;

    public static GetPowerStateResponse.PowerState stateForInt(int value) {
      GetPowerStateResponse.PowerState[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
        GetPowerStateResponse.PowerState var4 = var1[var3];
        if(var4.getValue() == value) {
          return var4;
        }
      }

      DLog.e("Could not build power stat from int: " + value);
      return null;
    }

    private PowerState(int value) {
      this.a = (byte)value;
    }

    public byte getValue() {
      return this.a;
    }
  }
}
