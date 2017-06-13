package com.orbotix.common;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.orbotix.common.stat.StatEventListener;
import com.orbotix.common.stat.StatRecorder;
import com.orbotix.common.utilities.SynchronousSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;





public abstract class DiscoveryAgentBase
  implements DiscoveryAgent
{
  private static final int b = 4;
  private final SynchronousSet<Robot> c = new SynchronousSet();
  private final SynchronousSet<DiscoveryAgentEventListener> d = new SynchronousSet();
  private final SynchronousSet<RobotChangedStateListener> e = new SynchronousSet();
  private final SynchronousSet<DiscoveryStateChangedListener> f = new SynchronousSet();
  
  protected final Handler _mainThreadHandler;
  
  private Context g;
  private int h = 1;
  private boolean i;
  
  protected DiscoveryAgentBase()
  {
    _mainThreadHandler = new Handler(Looper.getMainLooper());
    StatEventListener.getInstance().registerDiscoveryAgent(this);
  }
  
  public void fireRobotStateChange(final Robot robot, final RobotChangedStateListener.RobotChangedStateNotificationType type)
  {
    DLog.v("Robot state change to: " + type);
    if ((!a) && (robot == null)) { throw new AssertionError();
    }
    if ((type == RobotChangedStateListener.RobotChangedStateNotificationType.Disconnected) && 
      (getConnectedRobots().isEmpty())) {
      DLog.v("Disabling stats engine");
      StatRecorder.getInstance().stop();
      StatEventListener.getInstance().stopListener();
    }
    
    final List localList = e.toList();
    
    _mainThreadHandler.post(new Runnable()
    {
      public void run() {
        for (RobotChangedStateListener localRobotChangedStateListener : localList) {
          if (localRobotChangedStateListener != null) {
            localRobotChangedStateListener.handleRobotChangedState(robot, type);
          }
        }
      }
    });
  }
  
  protected final void notifyListenersOfDiscoveryStart() {
    _mainThreadHandler.post(new Runnable()
    {
      public void run() {
        for (DiscoveryStateChangedListener localDiscoveryStateChangedListener : DiscoveryAgentBase.a(DiscoveryAgentBase.this)) {
          localDiscoveryStateChangedListener.onDiscoveryDidStart(DiscoveryAgentBase.this);
        }
      }
    });
  }
  
  protected final void notifyListenersOfDiscoveryStop() {
    _mainThreadHandler.post(new Runnable()
    {
      public void run() {
        for (DiscoveryStateChangedListener localDiscoveryStateChangedListener : DiscoveryAgentBase.a(DiscoveryAgentBase.this)) {
          localDiscoveryStateChangedListener.onDiscoveryDidStop(DiscoveryAgentBase.this);
        }
      }
    });
  }
  
  protected final void cleanAvailableRobots() {
    ArrayList localArrayList = new ArrayList();
    for (Iterator localIterator = getRobots().iterator(); localIterator.hasNext();) { localRobot = (Robot)localIterator.next();
      if (!localRobot.isConnected())
        localArrayList.add(localRobot);
    }
    Robot localRobot;
    for (localIterator = localArrayList.iterator(); localIterator.hasNext();) { localRobot = (Robot)localIterator.next();
      removeRobot(localRobot);
    }
  }
  
  protected int getConnectingOrConnectedRobotsCount() {
    int j = 0;
    for (Robot localRobot : getRobots()) {
      if ((localRobot.isConnected()) || (localRobot.isConnecting())) {
        j++;
      }
    }
    return j;
  }
  
  protected final void updateAvailableRobots() {
    final List localList1 = getRobots();
    final List localList2 = d.toList();
    
    _mainThreadHandler.post(new Runnable()
    {
      public void run() {
        for (DiscoveryAgentEventListener localDiscoveryAgentEventListener : localList2) {
          localDiscoveryAgentEventListener.handleRobotsAvailable(localList1);
        }
      }
    });
  }
  
  protected final void addRobot(Robot robot) {
    c.add(robot);
  }
  
  protected final void removeRobot(Robot robot) {
    c.remove(robot);
  }
  
  protected final void sortRobotsUsingComparator(Comparator<Robot> comparator) {
    c.sortUsingComparator(comparator);
  }
  


  public abstract void connect(Robot paramRobot);
  

  public abstract void disconnectAll();
  

  public boolean startDiscovery(Context context)
    throws DiscoveryException
  {
    if (i) {
      DLog.w("Discovery already running, skipping call...");
      return false;
    }
    if (context == null) {
      DLog.e("Cannot start discovery with a null context");
      return false;
    }
    if (getConnectingOrConnectedRobotsCount() >= getMaxConnectedRobots()) {
      DLog.v("Connecting or Connected robots count is already at max: " + getMaxConnectedRobots());
      return false;
    }
    if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
      DLog.d("Bluetooth not enabled");
      throw new DiscoveryException(DiscoveryExceptionCode.BluetoothNotEnabled);
    }
    
    g = context;
    cleanAvailableRobots();
    StatRecorder.getInstance().start(context);
    StatEventListener.getInstance().startListener();
    i = true;
    return true;
  }
  
  public void stopDiscovery()
  {
    i = false;
  }
  

  public final boolean isDiscovering()
  {
    return i;
  }
  

  public List<Robot> getRobots()
  {
    return c.toList();
  }
  
  public List<Robot> getConnectedRobots()
  {
    List localList = getRobots();
    ArrayList localArrayList = new ArrayList();
    for (Robot localRobot : localList) {
      if (localRobot.isConnected()) {
        localArrayList.add(localRobot);
      }
    }
    return localArrayList;
  }
  
  public List<Robot> getConnectingRobots()
  {
    List localList = getRobots();
    ArrayList localArrayList = new ArrayList();
    for (Robot localRobot : localList) {
      if (localRobot.isConnecting()) {
        localArrayList.add(localRobot);
      }
    }
    return localArrayList;
  }
  
  public List<Robot> getOnlineRobots() {
    List localList = getRobots();
    ArrayList localArrayList = new ArrayList();
    for (Robot localRobot : localList) {
      if (localRobot.isOnline()) {
        localArrayList.add(localRobot);
      }
    }
    return localArrayList;
  }
  
  public final void addDiscoveryListener(DiscoveryAgentEventListener listener)
  {
    d.add(listener);
  }
  
  public final void removeDiscoveryListener(DiscoveryAgentEventListener listener)
  {
    d.remove(listener);
  }
  
  public final void addRobotStateListener(RobotChangedStateListener listener)
  {
    e.add(listener);
  }
  
  public final void removeRobotStateListener(RobotChangedStateListener listener)
  {
    e.remove(listener);
  }
  
  public final void addDiscoveryChangedStateListener(DiscoveryStateChangedListener listener)
  {
    f.add(listener);
  }
  
  public final void removeDiscoveryChangedStateListener(DiscoveryStateChangedListener listener)
  {
    f.remove(listener);
  }
  
  public final Context getContext()
  {
    return g;
  }
  
  public final int getMaxConnectedRobots()
  {
    return h;
  }
  
  public final void setMaxConnectedRobots(int maxConnectedRobots)
  {
    if ((maxConnectedRobots >= 0) && (maxConnectedRobots <= 4)) {
      h = maxConnectedRobots;
    }
  }
}
