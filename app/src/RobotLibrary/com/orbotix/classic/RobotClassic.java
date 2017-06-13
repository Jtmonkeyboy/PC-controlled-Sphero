package com.orbotix.classic;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import com.orbotix.command.SleepCommand;
import com.orbotix.command.SleepCommand.SleepType;
import com.orbotix.common.DLog;
import com.orbotix.common.RobotBase;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.RadioLink;


public class RobotClassic
  extends RobotBase
{
  private a a;
  
  protected RobotClassic(BluetoothDevice device, Handler mainThreadHandler)
  {
    super(DiscoveryAgentClassic.getInstance(), mainThreadHandler);
    a = new a(device, this);
  }
  



  public void sendCommand(DeviceCommand command)
  {
    a.sendCommand(command);
  }
  





  @Deprecated
  public void streamCommand(DeviceCommand command)
  {
    DLog.w("RobotClassic is not able to perform command streaming.");
    sendCommand(command);
  }
  
  public float getSignalQuality()
  {
    return 100.0F;
  }
  
  public String getRadioFirmwareRevision()
  {
    return "classic_robot_fw";
  }
  
  public boolean isOnline()
  {
    return isConnected();
  }
  
  public boolean isConnected()
  {
    return a.isConnected();
  }
  
  public boolean isConnecting()
  {
    return a.isConnecting();
  }
  
  public String toString() {
    if (a != null) {
      return String.format("<RobotClassic %s %s>", new Object[] { getName(), a.toString() });
    }
    
    return String.format("<RobotClassic %s>", new Object[] { getName() });
  }
  



  void a(boolean paramBoolean)
  {
    a.open();
  }
  
  public void disconnect()
  {
    DLog.v("RobotClassic.disconnect()");
    clearResponseListeners();
    a.close();
  }
  
  public void sleep()
  {
    sleep(SleepCommand.SleepType.NORMAL);
  }
  
  public void sleep(SleepCommand.SleepType type)
  {
    switch (1.a[type.ordinal()]) {
    case 1: 
      sendCommand(new SleepCommand());
      break;
    case 2: 
      sendCommand(new SleepCommand(65535, 0));
      break;
    default: 
      throw new IllegalArgumentException("RobotClassic cannot perform sleep type: " + type);
    }
  }
  
  protected RadioLink getRadioLink()
  {
    return a;
  }
}
