package com.orbotix.classic;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.support.annotation.NonNull;
import com.orbotix.command.JumpToMainCommand;
import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.RadioLink;
import java.io.IOException;









class a
  extends RadioLink
  implements b.a, d.b, e.a
{
  private BluetoothSocket a;
  private e b;
  
  public a(@NonNull BluetoothDevice paramBluetoothDevice, @NonNull RobotClassic paramRobotClassic)
  {
    super(paramBluetoothDevice, paramRobotClassic, paramRobotClassic);
  }
  
  public void open() {
    super.open();
    b localB = new b(getDevice(), this);
    Thread localThread = new Thread(localB);
    localThread.start();
  }
  
  public void close() {
    handleConnectionEnding();
    b.d();
  }
  
  public String getRadioFirmwareRevision()
  {
    return "classic_robot_fw";
  }
  
  protected void sendCommandInternal(DeviceCommand command) {
    b.a(command);
  }
  



  public void a()
  {
    handleConnectionInitiated();
  }
  
  public void a(BluetoothSocket paramBluetoothSocket)
  {
    a = paramBluetoothSocket;
    b = new e(this, this, a);
    b.c();
    handleConnectionSucceeded();
  }
  
  public void b()
  {
    handleConnectionFailed();
  }
  

  public void c()
  {
    sendCommand(new JumpToMainCommand());
  }
  
  public void d()
  {
    try {
      a.close();
    } catch (IOException localIOException) {
      DLog.e("Could not close the bluetooth socket");
    }
    handleConnectionClosed();
  }
  

  public void e()
  {
    handleCommandWritten();
  }
  



  public void a(byte[] paramArrayOfByte)
  {
    processRawData(paramArrayOfByte);
  }
  
  protected void handleSleepResponse()
  {
    DLog.w("Robot is sleeping, manually disconnecting early");
    close();
  }
  
  public String toString() {
    return String.format("<ClassicLink " + getAddress() + ">", new Object[0]);
  }
}
