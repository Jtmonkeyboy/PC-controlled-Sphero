package com.orbotix.le;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.orbotix.common.DLog;
import com.orbotix.common.DiscoveryAgentBase;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.DiscoveryExceptionCode;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener.RobotChangedStateNotificationType;
import com.orbotix.common.utilities.ApplicationLifecycleMonitor;
import com.orbotix.le.connectstrategy.ConnectStrategy;
import com.orbotix.le.connectstrategy.IfOneConnectStrategy;
import com.orbotix.le.connectstrategy.MultipleConditionConnectStrategy;
import com.orbotix.le.connectstrategy.ProximityConnectStrategy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class b
  extends DiscoveryAgentBase
  implements BluetoothAdapter.LeScanCallback
{
  private static final Comparator<Robot> a = new Comparator()
  {
    public int a(Robot paramAnonymousRobot1, Robot paramAnonymousRobot2) {
      return Float.compare(paramAnonymousRobot2.getSignalQuality(), paramAnonymousRobot1.getSignalQuality());
    }
  };
  
  private static String[] b = { "SM-G800F" };
  

  private static b c;
  
  private BluetoothAdapter d;
  
  private ConnectStrategy e;
  
  private Set<Robot> f = new HashSet();
  
  private c g;
  
  private Handler h;
  private Handler i;
  private RadioDescriptor j;
  
  private b()
  {
    HandlerThread localHandlerThread1 = new HandlerThread("com.orbotix.dale.parse");
    localHandlerThread1.start();
    h = new Handler(localHandlerThread1.getLooper());
    HandlerThread localHandlerThread2 = new HandlerThread("com.orbotix.dale.notify");
    localHandlerThread2.start();
    i = new Handler(localHandlerThread2.getLooper());
    e = c();
    j = new RobotRadioDescriptor();
  }
  
  public static b a() {
    if (c == null) {
      c = new b();
    }
    return c;
  }
  




  public boolean startDiscovery(Context context)
    throws DiscoveryException
  {
    if (!super.startDiscovery(context)) {
      return false;
    }
    
    if ((context instanceof Activity)) {
      ApplicationLifecycleMonitor.getInstance().setListeningApplication(((Activity)context).getApplication());
    }
    else {
      DLog.w("Cannot start activity lifecycle monitor: Context used for discovery is not an activity.");
    }
    
    if (!context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
      DLog.d("Device does not have BT 4.0");
      throw new DiscoveryException(DiscoveryExceptionCode.BluetoothLENotSupported);
    }
    
    BluetoothManager localBluetoothManager = (BluetoothManager)context.getSystemService("bluetooth");
    d = localBluetoothManager.getAdapter();
    
    DLog.v("BluetoothManager: " + localBluetoothManager);
    DLog.v("Bluetooth Adapter: " + d);
    

    ArrayList localArrayList = new ArrayList(localBluetoothManager.getConnectedDevices(7));
    DLog.v("Found " + localArrayList.size() + " preconnected devices");
    
    for (BluetoothDevice localBluetoothDevice : localArrayList)
    {
      if (j.nameStartsWithValidPrefix(localBluetoothDevice.getName())) {
        RobotLE localRobotLE = (RobotLE)b(localBluetoothDevice);
        if (!f.contains(localRobotLE)) {
          DLog.v("Robot? " + localRobotLE + " List: " + f.toString());
          addRobot(localRobotLE);
          connect(localRobotLE);
          if (getConnectingOrConnectedRobotsCount() >= getMaxConnectedRobots()) {
            DLog.v("Connecting a preconnected robot puts connected or connecting robots count at maximum. Stopping discovery.");
            return false;
          }
        } else {
          localRobotLE.disconnectForReals();
        }
      }
    }
    
    DLog.d(String.format("Bluetooth 4.0 Discovery requested: %d connected %d visible", new Object[] { Integer.valueOf(getConnectedRobots().size()), Integer.valueOf(super.getRobots().size()) }));
    
    g = new c(d, this);
    new Thread(g).start();
    notifyListenersOfDiscoveryStart();
    return true;
  }
  

  public void fireRobotStateChange(Robot robot, RobotChangedStateListener.RobotChangedStateNotificationType type)
  {
    DLog.v("State Change: " + type);
    if (type == RobotChangedStateListener.RobotChangedStateNotificationType.Disconnected) {
      DLog.v("Adding robot to blacklist " + robot);
      f.add(robot);
    } else if (type == RobotChangedStateListener.RobotChangedStateNotificationType.Connected)
    {
      if (f.contains(robot)) {
        DLog.v("Removing robot from blacklist " + robot);
        f.remove(robot);
      }
    }
    super.fireRobotStateChange(robot, type);
  }
  
  public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord)
  {
    if (a(device)) {
      return;
    }
    
    h.post(new Runnable()
    {
      public void run() {
        b.a(b.this, device, rssi, scanRecord);
      }
    });
  }
  
  private boolean a(BluetoothDevice paramBluetoothDevice) {
    return !j.nameStartsWithValidPrefix(paramBluetoothDevice.getName());
  }
  
  private void a(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfByte)
  {
    List localList = d.a(paramArrayOfByte);
    if (localList.size() < 3)
    {

      return;
    }
    
    byte[] arrayOfByte = get2c;
    int k = arrayOfByte[0];
    
    RobotLE localRobotLE1 = (RobotLE)b(paramBluetoothDevice);
    localRobotLE1.setAdPower(k);
    localRobotLE1.setRSSI(Integer.valueOf(paramInt));
    
    RobotLE localRobotLE2 = (RobotLE)e.getRobotToConnectFromAvailableNodes(super.getRobots(), localRobotLE1);
    if (localRobotLE2 != null) {
      DLog.v(String.format("CONNECTING %s with signal quality %f and ad power %d", new Object[] { localRobotLE2.getName(), Float.valueOf(localRobotLE2.getSignalQuality()), Integer.valueOf(k) }));
      connect(localRobotLE2);
    }
    
    i.post(new Runnable()
    {
      public void run() {
        b.a(b.this);
      }
    });
  }
  
  public void stopDiscovery()
  {
    if (!isDiscovering()) {
      return;
    }
    super.stopDiscovery();
    g.a();
    h.removeCallbacksAndMessages(null);
    notifyListenersOfDiscoveryStop();
  }
  

  public synchronized void connect(Robot robot)
  {
    if (!(robot instanceof RobotLE)) {
      throw new IllegalArgumentException("DiscoveryAgentLE cannot connect to robots of type " + robot.getClass().getName());
    }
    DLog.i("Connect Request: " + robot.toString());
    if (getConnectingOrConnectedRobotsCount() < getMaxConnectedRobots()) {
      a((RobotLE)robot, Boolean.valueOf(false));
      if (getConnectingOrConnectedRobotsCount() + 1 >= getMaxConnectedRobots()) {
        stopDiscovery();
      }
    } else {
      DLog.i("Skipping connect request because max connected robots already connected");
    }
  }
  



  void a(RobotLE paramRobotLE, Boolean paramBoolean)
  {
    DLog.i(paramRobotLE.getName() + " Bluetooth Low-Energy Connecting");
    paramRobotLE.a(paramBoolean.booleanValue());
  }
  
  public List<Robot> getRobots()
  {
    sortRobotsUsingComparator(a);
    return super.getRobots();
  }
  
  public void disconnectAll()
  {
    List localList = super.getRobots();
    for (Robot localRobot : localList) {
      if (localRobot.isConnected()) {
        localRobot.sleep();
      }
      else if (localRobot.isConnecting()) {
        localRobot.disconnect();
      }
    }
  }
  
  private Robot b(BluetoothDevice paramBluetoothDevice) {
    for (Object localObject = super.getRobots().iterator(); ((Iterator)localObject).hasNext();) { Robot localRobot = (Robot)((Iterator)localObject).next();
      RobotLE localRobotLE = (RobotLE)localRobot;
      
      if (localRobotLE.getAddress().equals(paramBluetoothDevice.getAddress())) {
        return localRobotLE;
      }
    }
    localObject = new RobotLE(paramBluetoothDevice, new RobotRadioDescriptor(), _mainThreadHandler);
    addRobot((Robot)localObject);
    return localObject;
  }
  
  public void a(RadioDescriptor paramRadioDescriptor) {
    j = paramRadioDescriptor;
  }
  
  public void a(ConnectStrategy paramConnectStrategy) {
    e = paramConnectStrategy;
  }
  
  public ConnectStrategy b() {
    return e;
  }
  
  private ConnectStrategy c() {
    String str1 = Build.MODEL;
    for (String str2 : b) {
      if (str2.equals(str1)) {
        DLog.w("This model does not support proximity connection, using fallback strategy");
        return new MultipleConditionConnectStrategy(new ConnectStrategy[] { new ProximityConnectStrategy(), new IfOneConnectStrategy() });
      }
    }
    return new ProximityConnectStrategy();
  }
}
