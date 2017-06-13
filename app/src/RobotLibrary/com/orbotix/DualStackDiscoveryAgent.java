package com.orbotix;

import android.content.Context;
import com.orbotix.classic.DiscoveryAgentClassic;
import com.orbotix.classic.RobotClassic;
import com.orbotix.common.DiscoveryAgent;
import com.orbotix.common.DiscoveryAgentEventListener;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.DiscoveryExceptionCode;
import com.orbotix.common.DiscoveryStateChangedListener;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.common.RobotChangedStateListener.RobotChangedStateNotificationType;
import com.orbotix.common.utilities.SynchronousSet;
import com.orbotix.le.DiscoveryAgentLE;
import com.orbotix.le.RobotLE;
import java.util.ArrayList;
import java.util.List;

public class DualStackDiscoveryAgent implements DiscoveryAgent, RobotChangedStateListener
{
  private static final Object a = new Object();
  
  private static DualStackDiscoveryAgent b;
  
  private Context c;
  private DiscoveryAgentLE d;
  private DiscoveryAgentClassic e;
  private boolean f;
  private int g = 1;
  private SynchronousSet<DiscoveryAgentEventListener> h = new SynchronousSet();
  private SynchronousSet<RobotChangedStateListener> i = new SynchronousSet();
  private SynchronousSet<DiscoveryStateChangedListener> j = new SynchronousSet();
  
  public static DualStackDiscoveryAgent getInstance() {
    synchronized (a) {
      if (b == null) {
        b = new DualStackDiscoveryAgent();
      }
    }
    return b;
  }
  
  private DualStackDiscoveryAgent() {
    d = DiscoveryAgentLE.getInstance();
    d.addRobotStateListener(this);
    e = DiscoveryAgentClassic.getInstance();
    e.addRobotStateListener(this);
  }
  
  private void a(boolean paramBoolean) {
    f = paramBoolean;
    for (DiscoveryStateChangedListener localDiscoveryStateChangedListener : j) {
      if (f) {
        localDiscoveryStateChangedListener.onDiscoveryDidStart(this);
      }
      else {
        localDiscoveryStateChangedListener.onDiscoveryDidStop(this);
      }
    }
  }
  


  private int a()
  {
    return d.getConnectedRobots().size() + d.getConnectingRobots().size() + e.getConnectingRobots().size() + e.getConnectedRobots().size();
  }
  



  public void disconnectAll()
  {
    e.disconnectAll();
    d.disconnectAll();
  }
  
  public void addDiscoveryListener(DiscoveryAgentEventListener listener)
  {
    h.add(listener);
  }
  
  public void removeDiscoveryListener(DiscoveryAgentEventListener listener)
  {
    h.remove(listener);
  }
  
  public void addRobotStateListener(RobotChangedStateListener listener)
  {
    i.add(listener);
  }
  
  public void removeRobotStateListener(RobotChangedStateListener listener)
  {
    i.remove(listener);
  }
  
  public void addDiscoveryChangedStateListener(DiscoveryStateChangedListener listener)
  {
    j.add(listener);
  }
  
  public void removeDiscoveryChangedStateListener(DiscoveryStateChangedListener listener)
  {
    j.remove(listener);
  }
  
  public Context getContext()
  {
    return c;
  }
  
  public List<Robot> getRobots()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(d.getRobots());
    localArrayList.addAll(e.getRobots());
    return localArrayList;
  }
  
  public List<Robot> getConnectingRobots()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(d.getConnectingRobots());
    localArrayList.addAll(e.getConnectingRobots());
    return localArrayList;
  }
  
  public List<Robot> getConnectedRobots()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(d.getConnectedRobots());
    localArrayList.addAll(e.getConnectedRobots());
    return localArrayList;
  }
  
  public List<Robot> getOnlineRobots()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(d.getOnlineRobots());
    localArrayList.addAll(e.getOnlineRobots());
    return localArrayList;
  }
  
  public boolean startDiscovery(Context context) throws DiscoveryException
  {
    if (context == null) {
      throw new DiscoveryException(DiscoveryExceptionCode.NullContext);
    }
    c = context;
    boolean bool1 = d.startDiscovery(c);
    boolean bool2 = e.startDiscovery(c);
    if ((bool1) || (bool2)) {
      a(true);
    }
    return (bool1) && (bool2);
  }
  
  public void stopDiscovery()
  {
    a(false);
    d.stopDiscovery();
    e.stopDiscovery();
  }
  
  public void connect(Robot robot)
  {
    if ((robot instanceof RobotLE)) {
      d.connect((RobotLE)robot);
    }
    else if ((robot instanceof RobotClassic)) {
      e.connect((RobotClassic)robot);
    }
  }
  
  public boolean isDiscovering()
  {
    return f;
  }
  
  public int getMaxConnectedRobots()
  {
    return g;
  }
  
  public void setMaxConnectedRobots(int maxConnectedRobots)
  {
    g = maxConnectedRobots;
  }
  



  public void handleRobotChangedState(Robot robot, RobotChangedStateListener.RobotChangedStateNotificationType type)
  {
    int k = a();
    if (type == RobotChangedStateListener.RobotChangedStateNotificationType.Connecting) {
      if (k == g) {
        stopDiscovery();
      }
    }
    else if ((type == RobotChangedStateListener.RobotChangedStateNotificationType.Online) && 
      (k > g))
    {
      if ((robot instanceof RobotLE)) {
        robot.sleep();
      }
      else if ((robot instanceof RobotClassic)) {
        robot.disconnect();
      }
    }
    


    for (RobotChangedStateListener localRobotChangedStateListener : i)
    {
      if (localRobotChangedStateListener != null) {
        localRobotChangedStateListener.handleRobotChangedState(robot, type);
      }
    }
  }
}
