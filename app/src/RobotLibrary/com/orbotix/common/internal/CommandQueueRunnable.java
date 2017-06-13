package com.orbotix.common.internal;

import com.orbotix.command.FlashWritingCommandDetector;
import com.orbotix.common.DLog;
import java.util.LinkedList;
import java.util.Queue;

public class CommandQueueRunnable
  implements Runnable
{
  private static final String a = "OBX-CQR";
  private static final int b = 250;
  private SendExecutor c;
  private final Queue<DeviceCommand> d = new LinkedList();
  private boolean e;
  private final Object f = new Object();
  
  public CommandQueueRunnable(SendExecutor sendExecutor) {
    c = sendExecutor;
  }
  
  public void run()
  {
    e = true;
    while (e) {
      a();
    }
  }
  
  public void stop() {
    e = false;
  }
  
  public void enqueue(DeviceCommand command) {
    synchronized (d) {
      d.add(command);
    }
    synchronized (f) {
      f.notify();
    }
  }
  
  private void a() {
    if (b() < 1) {
      try {
        synchronized (f) {
          f.wait();
        }
      } catch (InterruptedException localInterruptedException1) {
        DLog.v("ProcessNextCommand:InterruptedException");
      }
    }
    else {
      DeviceCommand localDeviceCommand = c();
      c.sendCommand(localDeviceCommand);
      if (FlashWritingCommandDetector.isFlashWritingCommand(localDeviceCommand)) {
        DLog.v("Flash writing command detected, pausing thread.");
        try {
          Thread.sleep(250L);
        } catch (InterruptedException localInterruptedException2) {
          DLog.w("Flash writing delay was interrupted");
        }
      }
    }
  }
  
  private int b() {
    int i;
    synchronized (d) {
      i = d.size();
    }
    return i;
  }
  
  private DeviceCommand c() {
    synchronized (d) {
      return (DeviceCommand)d.remove();
    }
  }
  
  public static abstract interface SendExecutor
  {
    public abstract void sendCommand(DeviceCommand paramDeviceCommand);
  }
}
