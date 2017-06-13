package com.orbotix.le;

import android.content.Context;
import android.os.Build.VERSION;
import com.orbotix.common.DiscoveryAgentEventListener;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.DiscoveryStateChangedListener;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.common.RobotChangedStateListener.RobotChangedStateNotificationType;
import com.orbotix.le.connectstrategy.ConnectStrategy;
import java.util.List;





public class DiscoveryAgentLE
  implements DiscoveryAgentLEProxy
{
  private static DiscoveryAgentLE a;
  private b b;
  
  private DiscoveryAgentLE()
  {
    b = b.a();
  }
  
  public static DiscoveryAgentLE getInstance() {
    if (Build.VERSION.SDK_INT < 18) {
      throw new UnsupportedOperationException();
    }
    
    if (a == null) {
      a = new DiscoveryAgentLE();
    }
    return a;
  }
  




  public void setRadioDescriptor(RadioDescriptor descriptor)
  {
    b.a(descriptor);
  }
  
  public void setConnectStrategy(ConnectStrategy connectStrategy)
  {
    b.a(connectStrategy);
  }
  
  public void fireRobotStateChange(Robot robot, RobotChangedStateListener.RobotChangedStateNotificationType type)
  {
    b.fireRobotStateChange(robot, type);
  }
  
  public boolean startDiscovery(Context context) throws DiscoveryException
  {
    return b.startDiscovery(context);
  }
  
  public void stopDiscovery()
  {
    b.stopDiscovery();
  }
  
  public List<Robot> getRobots()
  {
    return b.getRobots();
  }
  
  public List<Robot> getConnectingRobots()
  {
    return b.getConnectingRobots();
  }
  
  public List<Robot> getConnectedRobots()
  {
    return b.getConnectedRobots();
  }
  
  public List<Robot> getOnlineRobots()
  {
    return b.getOnlineRobots();
  }
  
  public void connect(Robot robot)
  {
    b.connect(robot);
  }
  
  public void disconnectAll()
  {
    b.disconnectAll();
  }
  
  public void addDiscoveryListener(DiscoveryAgentEventListener listener)
  {
    b.addDiscoveryListener(listener);
  }
  
  public void removeDiscoveryListener(DiscoveryAgentEventListener listener)
  {
    b.removeDiscoveryListener(listener);
  }
  
  public void addRobotStateListener(RobotChangedStateListener listener)
  {
    b.addRobotStateListener(listener);
  }
  
  public void removeRobotStateListener(RobotChangedStateListener listener)
  {
    b.addRobotStateListener(listener);
  }
  
  public void addDiscoveryChangedStateListener(DiscoveryStateChangedListener listener)
  {
    b.addDiscoveryChangedStateListener(listener);
  }
  
  public void removeDiscoveryChangedStateListener(DiscoveryStateChangedListener listener)
  {
    b.removeDiscoveryChangedStateListener(listener);
  }
  
  public Context getContext()
  {
    return b.getContext();
  }
  
  public int getMaxConnectedRobots()
  {
    return b.getMaxConnectedRobots();
  }
  
  public void setMaxConnectedRobots(int maxConnectedRobots)
  {
    b.setMaxConnectedRobots(maxConnectedRobots);
  }
  
  public boolean isDiscovering()
  {
    return b.isDiscovering();
  }
  
  public ConnectStrategy getConnectStrategy() {
    return b.b();
  }
}
