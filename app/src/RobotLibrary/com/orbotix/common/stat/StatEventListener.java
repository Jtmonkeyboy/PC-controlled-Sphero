package com.orbotix.common.stat;

import com.orbotix.command.GetOdometerCommand;
import com.orbotix.common.DLog;
import com.orbotix.common.DiscoveryAgent;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.common.RobotChangedStateListener.RobotChangedStateNotificationType;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;
import java.util.HashSet;
import java.util.Set;

public class StatEventListener
  implements ResponseListener, RobotChangedStateListener
{
  private boolean a;
  private Set<DiscoveryAgent> b = new HashSet();
  private static StatEventListener c;
  
  public static StatEventListener getInstance() {
    if (null == c) {
      c = new StatEventListener();
    }
    return c;
  }
  
  private StatEventListener() {
    a = false;
  }
  
  public void startListener() {
    if (a) return;
    for (DiscoveryAgent localDiscoveryAgent : b) {
      localDiscoveryAgent.addRobotStateListener(this);
    }
    a = true;
  }
  
  public void stopListener() {
    if (!a) return;
    for (DiscoveryAgent localDiscoveryAgent : b) {
      localDiscoveryAgent.removeRobotStateListener(this);
    }
    a = false;
  }
  
  public void registerDiscoveryAgent(DiscoveryAgent agent) {
    b.add(agent);
  }
  



  public void handleRobotChangedState(Robot inRobot, RobotChangedStateListener.RobotChangedStateNotificationType type)
  {
    Stat localStat1 = new Stat(Stat.StatKey.BLUETOOTH_CONNECTION_EVENT, inRobot.getIdentifier());
    String str; switch (2.a[type.ordinal()]) {
    case 1: 
      str = "connected";
      inRobot.addResponseListener(this);
      break;
    case 2: 
      inRobot.sendCommand(new GetOdometerCommand());
      return;
    
    case 3: 
      str = "disconnected";
      Stat localStat2 = new Stat(Stat.StatKey.CONNECT_TIME, inRobot.getIdentifier());
      localStat2.addData(Stat.StatDataKey.VALUE, String.format("%d", new Object[] { Integer.valueOf(inRobot.getConnectTimeInSeconds()) }));
      StatRecorder.getInstance().recordStat(localStat2);
      inRobot.removeResponseListener(this);
      break;
    default: 
      return;
    }
    
    SLog.log("StatEventListener: Created robot connection notification with state: " + str);
    localStat1.addData(Stat.StatDataKey.VALUE, str);
    StatRecorder.getInstance().recordStat(localStat1);
  }
  
  public void handleResponse(DeviceResponse response, Robot robot)
  {
    switch (response.getDeviceId()) {
    case 2: 
      a(response, robot);
      break;
    case 1: 
      if (response.getCommandId() == 4) {
        ProfileStatGenerator localProfileStatGenerator = new ProfileStatGenerator(robot);
        localProfileStatGenerator.a(new ProfileStatGenerator.ProfileStatGeneratorListener()
        {

          public void handleProfileStatGenerated(Stat profileStat) { StatRecorder.getInstance().recordStat(profileStat); }
        });
      }
      break;
    }
    
  }
  



  private void a(DeviceResponse paramDeviceResponse, Robot paramRobot)
  {
    if (paramDeviceResponse.getResponseCode() != ResponseCode.OK) {
      DLog.w("Skipping response in stat event listener as it does not have an OK response code");
      return;
    }
    switch (paramDeviceResponse.getCommandId()) {
    case 117: 
      long l = paramDeviceResponse.getDataUint32Value();
      
      Stat localStat = new Stat(Stat.StatKey.ODOMETER, paramRobot.getIdentifier());
      SLog.log("StatEventListener: Read odometer value: " + l);
      localStat.addData(Stat.StatDataKey.CM, l);
      if (l > 0L) {
        StatRecorder.getInstance().recordStat(localStat);
      }
      
      break;
    }
    
  }
  

  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot)
  {
    Stat localStat;
    switch (asyncMessage.getPacket()[2]) {
    case 7: 
      localStat = new Stat(Stat.StatKey.COLLISION, robot.getIdentifier());
      SLog.log("StatEventListener: Created a collision stat");
      break;
    
    default: 
      return;
    }
    StatRecorder.getInstance().recordStat(localStat);
  }
  


  public void handleStringResponse(String stringResponse, Robot robot) {}
  


  public boolean equals(Object o)
  {
    return o instanceof StatEventListener;
  }
  
  public int hashCode()
  {
    return 42;
  }
}
