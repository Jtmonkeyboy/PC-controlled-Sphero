package com.orbotix.le;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import com.orbotix.command.SleepCommand;
import com.orbotix.command.SleepCommand.SleepType;
import com.orbotix.common.DLog;
import com.orbotix.common.RobotBase;
import com.orbotix.common.internal.BootloaderCommandId;
import com.orbotix.common.internal.CoreCommandId;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.MainProcessorState;
import com.orbotix.common.internal.RadioConnectionState;
import com.orbotix.common.internal.RadioLink;
import com.orbotix.common.utilities.ApplicationLifecycleMonitor;
import java.util.ArrayList;
import java.util.List;





public class RobotLE
  extends RobotBase
  implements LeLinkRadioACKListener
{
  private e a;
  private float b = -96.0F;
  private Integer c = Integer.valueOf(0);
  private boolean d = false;
  private final List<RobotLeRadioAckDelegate> e = new ArrayList();
  
  protected RobotLE(BluetoothDevice bleDevice, RobotRadioDescriptor radioDescriptor, Handler mainThreadHandler) {
    super(DiscoveryAgentLE.getInstance(), mainThreadHandler);
    a = new e(bleDevice, this, radioDescriptor, _mainThreadHandler);
  }
  




  public void addRadioAckListener(RobotLeRadioAckDelegate listener)
  {
    if (e.contains(listener)) {
      DLog.w("%s is already a radio ack listener for %s", new Object[] { listener, getName() });
      return;
    }
    e.add(listener);
  }
  




  public void removeRadioAckListener(RobotLeRadioAckDelegate listener)
  {
    if (e.contains(listener)) {
      e.remove(listener);
    }
  }
  
  public String toString() {
    if (a != null) {
      return String.format("<RobotLE %s %2.0f%% %s isConnecting: %s isConnected: %s>", new Object[] { getName(), Float.valueOf(getSignalQuality()), a.toString(), Boolean.valueOf(isConnecting()), Boolean.valueOf(isConnected()) });
    }
    
    return String.format("<RobotLE (no link)>", new Object[0]);
  }
  
  public long getAckLatency()
  {
    return a.getAckLatency().longValue();
  }
  
  public void setDeveloperMode(boolean enabled) { a.c(enabled); }
  
  protected void setAdPower(int txpower) {
    c = Integer.valueOf(txpower);
  }
  
  protected Integer adjustedRSSI() {
    return Integer.valueOf(a.d().intValue() - c.intValue());
  }
  
  public void setRSSI(Integer rssi) {
    a.a(rssi);
  }
  

  public float getSignalQuality()
  {
    int i = c.intValue() == -10 ? 48 : 30;
    

    float f1 = 1.0F / -(Math.abs(b) - i);
    float f2 = (1.0F - (Math.abs(b) + a.d().intValue())) * f1;
    if (f2 > 1.0F) f2 = 1.0F;
    return f2 * 100.0F;
  }
  
  public boolean isConnected()
  {
    return a.isConnected();
  }
  
  public boolean isConnecting()
  {
    return a.isConnecting();
  }
  
  public boolean isOnline()
  {
    return a.getMpState() == MainProcessorState.InMainApp;
  }
  
  public void sendCommand(DeviceCommand command)
  {
    a(command, false);
  }
  
  public void streamCommand(DeviceCommand command)
  {
    a(command, true);
  }
  
  private void a(DeviceCommand paramDeviceCommand, boolean paramBoolean) {
    if ((d) && (a(paramDeviceCommand))) {
      d = false;
      a.sendCommand(paramDeviceCommand);
      if (ApplicationLifecycleMonitor.getInstance().applicationIsBackground()) {
        DLog.i("Sleep requested while in bootloader, sleeping robot.");
        sleep();
      }
      else {
        DLog.i("Sleep was requested, but the application is active on jump to main. Not sleeping.");
      }
      return;
    }
    if ((a.getMpState() == MainProcessorState.InBootloader) && (b(paramDeviceCommand))) {
      DLog.i("Postponing sleep command since robot is in bootloader");
      d = true;
      return;
    }
    
    a.sendCommand(paramDeviceCommand);
  }
  




  private void setMaintainBackgroundConnection(boolean maintainBackgroundConnection)
  {
    a.d(maintainBackgroundConnection);
  }
  
  private boolean getMaintainBackgroundConnection() {
    return a.f();
  }
  
  private boolean a(DeviceCommand paramDeviceCommand) {
    return (paramDeviceCommand.getDeviceId() == DeviceId.BOOTLOADER.getValue()) && (paramDeviceCommand.getCommandId() == BootloaderCommandId.LEAVE_BOOTLOADER.getValue());
  }
  
  private boolean b(DeviceCommand paramDeviceCommand) {
    return (paramDeviceCommand.getDeviceId() == DeviceId.CORE.getValue()) && (paramDeviceCommand.getCommandId() == CoreCommandId.SLEEP.getValue());
  }
  
  void a(boolean paramBoolean)
  {
    a.a(paramBoolean);
  }
  

  public void disconnect()
  {
    DLog.i("RobotLE.disconnect()");
    sleep();
  }
  
  public void disconnectForReals() {
    clearResponseListeners();
    a.close();
  }
  
  public void sleep()
  {
    sleep(SleepCommand.SleepType.LOW_POWER);
  }
  
  public void sleep(SleepCommand.SleepType mode) {
    switch (2.a[mode.ordinal()]) {
    case 1: 
      sendCommand(new SleepCommand(0, 0));
      break;
    case 2: 
      sleep(SleepCommand.SleepType.NORMAL);
      if (a.getMpState() != MainProcessorState.InBootloader) {
        a.a((short)2);
      }
      break;
    case 3: 
      if (a.getRfState() == RadioConnectionState.Connected) {
        DLog.v("Link is online, deep sleeping.");
        a.b();
      }
      else {
        DLog.w("Link is offline, deep sleep not possible");
      }
      break;
    }
  }
  
  protected RadioLink getRadioLink()
  {
    return a;
  }
  
  public LeLinkInterface getLeLink() {
    return a;
  }
  
  public void didACK()
  {
    _mainThreadHandler.post(new Runnable()
    {
      public void run() {
        for (RobotLeRadioAckDelegate localRobotLeRadioAckDelegate : RobotLE.a(RobotLE.this)) {
          localRobotLeRadioAckDelegate.handleACK(RobotLE.this);
        }
      }
    });
  }
  
  public void handleLinkDidWake()
  {
    super.handleLinkDidWake();
    a.b(true);
  }
  
  public void handleLinkDidSleep()
  {
    super.handleLinkDidSleep();
    a.b(false);
  }
}
