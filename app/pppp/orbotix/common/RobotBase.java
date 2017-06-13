package com.orbotix.common;

import android.os.Handler;
import com.orbotix.command.SleepCommand.SleepType;
import com.orbotix.command.VersioningResponse;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.MainProcessorState;
import com.orbotix.common.internal.RadioLink;
import com.orbotix.common.internal.RadioLinkSessionListener;
import com.orbotix.common.internal.RadioLinkStateChangedListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;





public abstract class RobotBase
  implements Robot, RadioLinkSessionListener, RadioLinkStateChangedListener
{
  protected Date _connectedTimeStamp;
  protected Date _disconnectedTimeStamp;
  protected final Handler _mainThreadHandler;
  private DiscoveryAgentProxy a;
  private VersioningResponse b;
  
  protected RobotBase(DiscoveryAgentProxy governingAgent, Handler mainThreadHandler)
  {
    a = governingAgent;
    _mainThreadHandler = mainThreadHandler;
  }
  
  private Set<ResponseListener> c = new HashSet();
  
  public String getName() {
    return getRadioLink().getName();
  }
  
  public String getIdentifier() {
    return getRadioLink().getSerialNumber();
  }
  

  public String getRadioFirmwareRevision() { return getRadioLink().getRadioFirmwareRevision(); }
  
  public abstract void sendCommand(DeviceCommand paramDeviceCommand);
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract void disconnect();
  
  public abstract void sleep();
  
  public abstract void sleep(SleepCommand.SleepType paramSleepType);
  
  protected abstract RadioLink getRadioLink();
  public abstract float getSignalQuality();
  public VersioningResponse getVersions() { return b; }
  



  public synchronized void handleResponseReceived(final DeviceResponse response)
  {
    if ((response instanceof VersioningResponse)) {
      b = ((VersioningResponse)response);
    }
    final HashSet localHashSet = new HashSet(c);
    _mainThreadHandler.post(new Runnable()
    {
      public void run() {
        for (ResponseListener localResponseListener : localHashSet) {
          localResponseListener.handleResponse(response, RobotBase.this);
        }
      }
    });
  }
  
  public synchronized void handleStringResponseReceived(final String stringResponse)
  {
    final HashSet localHashSet = new HashSet(c);
    _mainThreadHandler.post(new Runnable()
    {
      public void run() {
        for (ResponseListener localResponseListener : localHashSet) {
          localResponseListener.handleStringResponse(stringResponse, RobotBase.this);
        }
      }
    });
  }
  
  public synchronized void handleAsyncMessageReceived(final AsyncMessage asyncMessage)
  {
    final HashSet localHashSet = new HashSet(c);
    _mainThreadHandler.post(new Runnable()
    {
      public void run() {
        for (ResponseListener localResponseListener : localHashSet) {
          localResponseListener.handleAsyncMessage(asyncMessage, RobotBase.this);
        }
      }
    });
  }
  

  public void handleLinkDidWake()
  {
    DLog.i("Connected " + toString());
    _connectedTimeStamp = new Date();
    _disconnectedTimeStamp = null;
    a.fireRobotStateChange(this, RobotChangedStateListener.RobotChangedStateNotificationType.Online);
  }
  
  public void handleLinkDidDisconnect()
  {
    DLog.i("Disconnected " + toString());
    _disconnectedTimeStamp = new Date();
    a.fireRobotStateChange(this, RobotChangedStateListener.RobotChangedStateNotificationType.Disconnected);
  }
  
  public void handleLinkDidSleep()
  {
    a.fireRobotStateChange(this, RobotChangedStateListener.RobotChangedStateNotificationType.Offline);
  }
  
  public void handleLinkConnectionInitiated()
  {
    a.fireRobotStateChange(this, RobotChangedStateListener.RobotChangedStateNotificationType.Connecting);
  }
  
  public void handleLinkFailedToConnect()
  {
    a.fireRobotStateChange(this, RobotChangedStateListener.RobotChangedStateNotificationType.FailedConnect);
  }
  
  public void handleLinkDidConnect()
  {
    a.fireRobotStateChange(this, RobotChangedStateListener.RobotChangedStateNotificationType.Connected);
  }
  

  public synchronized void addResponseListener(ResponseListener listener)
  {
    if (c.contains(listener)) {
      DLog.w("%s is already a response listener for %s", new Object[] { listener, getName() });
      return;
    }
    c.add(listener);
  }
  
  public synchronized void removeResponseListener(ResponseListener listener) {
    if (c.contains(listener)) {
      c.remove(listener);
    }
  }
  
  protected synchronized void clearResponseListeners() { c.clear(); }
  
  public int getConnectTimeInSeconds() {
    if (null == _connectedTimeStamp) {
      return 0;
    }
    Date localDate = new Date();
    if (null != _disconnectedTimeStamp) {
      localDate = _disconnectedTimeStamp;
    }
    return (int)((localDate.getTime() - _connectedTimeStamp.getTime()) / 1000L);
  }
  
  public final String getSerialNumber() {
    return getRadioLink().getSerialNumber();
  }
  
  public final String getAddress() {
    return getRadioLink().getAddress();
  }
  
  public boolean isBootloader()
  {
    return getRadioLink().getMpState() == MainProcessorState.InBootloader;
  }
}
