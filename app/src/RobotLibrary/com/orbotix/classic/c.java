package com.orbotix.classic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.orbotix.common.DLog;
import com.orbotix.common.DiscoveryAgentBase;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.DiscoveryExceptionCode;
import com.orbotix.common.Robot;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


class c
  extends DiscoveryAgentBase
{
  private final String b = "00:06:66";
  private final String c = "68:86:E7";
  
  BluetoothAdapter a = null;
  
  private static c d;
  private boolean e;
  
  public static c a()
  {
    if (d == null) {
      d = new c();
    }
    return d;
  }
  
  private c()
  {
    if (Looper.myLooper() == null) {
      Looper.prepare();
    }
    e = false;
  }
  
  private final IntentFilter f = new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
  private final IntentFilter g = new IntentFilter("android.bluetooth.device.action.FOUND");
  private final BroadcastReceiver h = new BroadcastReceiver()
  {
    public void onReceive(Context context, Intent intent) {
      String str = intent.getAction();
      BluetoothDevice localBluetoothDevice;
      if ("android.bluetooth.device.action.FOUND".equals(str)) {
        localBluetoothDevice = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int i = intent.getShortExtra("android.bluetooth.device.extra.RSSI", (short)Short.MIN_VALUE);
        if (a.getBondedDevices().contains(localBluetoothDevice)) {
          DLog.i("Discovered Paired Device: " + localBluetoothDevice.getName() + " @ " + localBluetoothDevice.getAddress() + " rssi=" + i);
          c.a(c.this, localBluetoothDevice);
        }
        
      }
      else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(str)) {
        if (isDiscovering()) {
          DLog.w("Discovery finished, restarting as nothing was found");
          a.startDiscovery();
        }
      }
      else if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(str)) {
        localBluetoothDevice = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        for (Robot localRobot : getConnectedRobots()) {
          RobotClassic localRobotClassic = (RobotClassic)localRobot;
          if (localRobotClassic.getIdentifier().equals(localBluetoothDevice.getAddress())) {
            DLog.v("Disconnecting robot based on ACL intent");
            localRobot.disconnect();
          }
        }
      }
    }
  };
  
  private void a(final RobotClassic paramRobotClassic) {
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run() {
        paramRobotClassic.a(false);
      }
    });
  }
  
  private Robot a(BluetoothDevice paramBluetoothDevice) {
    for (Object localObject = getRobots().iterator(); ((Iterator)localObject).hasNext();) { Robot localRobot = (Robot)((Iterator)localObject).next();
      RobotClassic localRobotClassic = (RobotClassic)localRobot;
      if (localRobotClassic.getIdentifier().equals(paramBluetoothDevice.getAddress())) {
        return localRobotClassic;
      }
    }
    localObject = new RobotClassic(paramBluetoothDevice, _mainThreadHandler);
    addRobot((Robot)localObject);
    return localObject;
  }
  





  private void b(BluetoothDevice paramBluetoothDevice)
  {
    if ((paramBluetoothDevice.getAddress().startsWith("00:06:66")) || (paramBluetoothDevice.getAddress().startsWith("68:86:E7"))) {
      RobotClassic localRobotClassic = (RobotClassic)a(paramBluetoothDevice);
      DLog.v("Found a Sphero " + localRobotClassic + ", updating Discovery Listeners...");
      
      connect(localRobotClassic);
      

      updateAvailableRobots();
    }
  }
  
  private void b() {
    if (e) {
      DLog.v("Skipping call to applyDiscoveryIntentFilters(), receivers are already registered");
      return;
    }
    getContext().registerReceiver(h, f);
    getContext().registerReceiver(h, g);
    e = true;
  }
  
  private void c() {
    getContext().unregisterReceiver(h);
    e = false;
  }
  
  private boolean d() {
    return (null != a) && (a.isEnabled());
  }
  


  public boolean startDiscovery(Context context)
    throws DiscoveryException
  {
    if (!super.startDiscovery(context)) {
      return false;
    }
    
    DLog.v("DiscoveryAgentClassic.startDiscovery()");
    
    a = BluetoothAdapter.getDefaultAdapter();
    
    if (!d()) {
      DLog.w("BluetoothAdapter not available");
      return false;
    }
    

    b();
    

    if (!a.startDiscovery()) {
      throw new DiscoveryException(DiscoveryExceptionCode.Unknown);
    }
    notifyListenersOfDiscoveryStart();
    return true;
  }
  
  public void stopDiscovery()
  {
    if (!isDiscovering()) {
      return;
    }
    super.stopDiscovery();
    
    DLog.v("DiscoveryAgentClassic.stopDiscovery()");
    
    if ((a != null) && (a.isDiscovering())) {
      a.cancelDiscovery();
    }
    

    if (getContext() != null) {
      try {
        c();
        e = false;
      } catch (IllegalArgumentException localIllegalArgumentException) {
        DLog.v("Ignoring exception on unRegister intent discoveryIntentReceiver. - This is normal.");
      }
    }
    notifyListenersOfDiscoveryStop();
  }
  
  public void connect(Robot robot)
  {
    if (!(robot instanceof RobotClassic)) {
      throw new IllegalArgumentException("DiscoveryAgentClassic cannot connect to robots of type " + robot.getClass().getName());
    }
    
    DLog.i("Connect Request: " + robot.toString());
    if (getConnectingOrConnectedRobotsCount() < getMaxConnectedRobots()) {
      a((RobotClassic)robot);
      if (getConnectingOrConnectedRobotsCount() + 1 >= getMaxConnectedRobots()) {
        stopDiscovery();
      }
    } else {
      DLog.i("Skipping connect request because max connected robots already connected");
    }
  }
  
  public void disconnectAll()
  {
    List localList = getRobots();
    for (Robot localRobot : localList) {
      if ((localRobot.isConnected()) || (localRobot.isConnecting())) {
        localRobot.disconnect();
      }
    }
  }
}
