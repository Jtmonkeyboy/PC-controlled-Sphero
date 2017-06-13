package com.orbotix.classic;

import android.content.Context;
import com.orbotix.common.DiscoveryAgentEventListener;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.DiscoveryStateChangedListener;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.common.RobotChangedStateListener.RobotChangedStateNotificationType;
import java.util.List;






public class DiscoveryAgentClassic
  implements DiscoveryAgentClassicProxy
{
  private static DiscoveryAgentClassic a;
  private c b;
  
  private DiscoveryAgentClassic()
  {
    b = c.a();
  }
  
  public static DiscoveryAgentClassic getInstance() {
    if (a == null) {
      a = new DiscoveryAgentClassic();
    }
    return a;
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
    b.removeRobotStateListener(listener);
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
}
