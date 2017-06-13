package com.orbotix.subsystem;

import com.orbotix.ConvenienceRobot;
import com.orbotix.command.ConfigureCollisionDetectionCommand;
import com.orbotix.command.ConfigureLocatorCommand;
import com.orbotix.command.SetDataStreamingCommand;
import com.orbotix.command.VersioningCommand;
import com.orbotix.command.VersioningResponse;
import com.orbotix.common.DLog;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.RobotVersion;
import com.orbotix.common.sensor.SensorFlag;

public class SensorControl implements com.orbotix.common.ResponseListener
{
  private static final int a = 420;
  private static final int b = 390;
  private static final int c = 400;
  private static final int d = 2;
  private ConvenienceRobot e;
  private boolean f;
  private long g;
  private StreamingRate h;
  
  public SensorControl(ConvenienceRobot robot)
  {
    e = robot;
    f = false;
    g = 0L;
    h = StreamingRate.STREAMING_RATE_OFF;
    e.addResponseListener(this);
  }
  
  public void enableCollisions(boolean enable) {
    if (enable) {
      e.sendCommand(new ConfigureCollisionDetectionCommand(1, 100, 100, 100, 100, 1));
    }
    else
    {
      e.sendCommand(new ConfigureCollisionDetectionCommand(0, 0, 0, 0, 0, 0));
    }
  }
  
  public void enableLocator(boolean enable)
  {
    if (enable) {
      e.sendCommand(new ConfigureLocatorCommand(1, 0, 0, 0));
      e.enableSensors(SensorFlag.LOCATOR.longValue(), StreamingRate.STREAMING_RATE10);
    }
    else {
      e.sendCommand(new ConfigureLocatorCommand(0, 0, 0, 0));
      e.disableSensors();
    }
  }
  
  public void enableSensors(long streamingMask, StreamingRate streamingRate) {
    if (null == e.getLastVersioning()) {
      f = true;
      g = streamingMask;
      h = streamingRate;
      e.sendCommand(new VersioningCommand());
      return;
    }
    if (streamingRate.getValue() <= 0) {
      disableSensors();
      return;
    }
    int i = a(streamingRate.getValue());
    if (i == 0) {
      DLog.w("Calculated 0 divisor, reconfiguring to allow for highest streaming speed");
      i = 1;
    }
    int j = 1;
    if (i < 2) {
      DLog.w("Using 2 packet frames since streaming rate is set higher than the robot can handle for 1");
      j = 2;
    }
    e.sendCommand(new SetDataStreamingCommand(i, j, streamingMask, 0));
  }
  
  public void disableSensors() {
    e.sendCommand(new SetDataStreamingCommand(1, 0, 0L, 0));
  }
  
  private int a(int paramInt) {
    int i;
    switch (e.getLastVersioning().getModelNumber()) {
    case 2: 
      i = 420;
      break;
    case 3: 
      i = 390;
      break;
    case 10: 
      i = 400;
      break;
    default: 
      DLog.w("Unrecognized model number to create normalized divisor: " + e.getLastVersioning().getModelNumber());
      i = 400;
    }
    
    return i / paramInt;
  }
  
  public void handleResponse(DeviceResponse response, Robot robot)
  {
    if ((f) && ((response instanceof VersioningResponse))) {
      f = false;
      enableSensors(g, h);
    }
  }
  




  public void handleStringResponse(String stringResponse, Robot robot) {}
  



  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot) {}
  



  public static enum StreamingRate
  {
    private int a;
    



    private StreamingRate(int value)
    {
      a = value;
    }
    
    public int getValue() {
      return a;
    }
  }
}
