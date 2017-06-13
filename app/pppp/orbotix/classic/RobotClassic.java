//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.classic;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import com.orbotix.classic.DiscoveryAgentClassic;
import com.orbotix.classic.a;
import com.orbotix.command.SleepCommand;
import com.orbotix.command.SleepCommand.SleepType;
import com.orbotix.common.DLog;
import com.orbotix.common.RobotBase;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.RadioLink;

public class RobotClassic extends RobotBase {
  private a a;

  protected RobotClassic(BluetoothDevice device, Handler mainThreadHandler) {
    super(DiscoveryAgentClassic.getInstance(), mainThreadHandler);
    this.a = new a(device, this);
  }

  public void sendCommand(DeviceCommand command) {
    this.a.sendCommand(command);
  }

  /** @deprecated */
  @Deprecated
  public void streamCommand(DeviceCommand command) {
    DLog.w("RobotClassic is not able to perform command streaming.");
    this.sendCommand(command);
  }

  public float getSignalQuality() {
    return 100.0F;
  }

  public String getRadioFirmwareRevision() {
    return "classic_robot_fw";
  }

  public boolean isOnline() {
    return this.isConnected();
  }

  public boolean isConnected() {
    return this.a.isConnected();
  }

  public boolean isConnecting() {
    return this.a.isConnecting();
  }

  public String toString() {
    return this.a != null?String.format("<RobotClassic %s %s>", new Object[]{this.getName(), this.a.toString()}):String.format("<RobotClassic %s>", new Object[]{this.getName()});
  }

  void a(boolean var1) {
    this.a.open();
  }

  public void disconnect() {
    DLog.v("RobotClassic.disconnect()");
    this.clearResponseListeners();
    this.a.close();
  }

  public void sleep() {
    this.sleep(SleepType.NORMAL);
  }

  public void sleep(SleepType type) {
    switch(null.a[type.ordinal()]) {
      case 1:
        this.sendCommand(new SleepCommand());
        break;
      case 2:
        this.sendCommand(new SleepCommand('\uffff', 0));
        break;
      default:
        throw new IllegalArgumentException("RobotClassic cannot perform sleep type: " + type);
    }

  }

  protected RadioLink getRadioLink() {
    return this.a;
  }
}
