//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.subsystem;

import com.orbotix.ConvenienceRobot;
import com.orbotix.command.ConfigureCollisionDetectionCommand;
import com.orbotix.command.ConfigureLocatorCommand;
import com.orbotix.command.SetDataStreamingCommand;
import com.orbotix.command.VersioningCommand;
import com.orbotix.command.VersioningResponse;
import com.orbotix.common.DLog;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.sensor.SensorFlag;

public class SensorControl implements ResponseListener {
  private static final int a = 420;
  private static final int b = 390;
  private static final int c = 400;
  private static final int d = 2;
  private ConvenienceRobot e;
  private boolean f;
  private long g;
  private SensorControl.StreamingRate h;

  public SensorControl(ConvenienceRobot robot) {
    this.e = robot;
    this.f = false;
    this.g = 0L;
    this.h = SensorControl.StreamingRate.STREAMING_RATE_OFF;
    this.e.addResponseListener(this);
  }

  public void enableCollisions(boolean enable) {
    if(enable) {
      this.e.sendCommand(new ConfigureCollisionDetectionCommand(1, 100, 100, 100, 100, 1));
    } else {
      this.e.sendCommand(new ConfigureCollisionDetectionCommand(0, 0, 0, 0, 0, 0));
    }

  }

  public void enableLocator(boolean enable) {
    if(enable) {
      this.e.sendCommand(new ConfigureLocatorCommand(1, 0, 0, 0));
      this.e.enableSensors(SensorFlag.LOCATOR.longValue(), SensorControl.StreamingRate.STREAMING_RATE10);
    } else {
      this.e.sendCommand(new ConfigureLocatorCommand(0, 0, 0, 0));
      this.e.disableSensors();
    }

  }

  public void enableSensors(long streamingMask, SensorControl.StreamingRate streamingRate) {
    if(null == this.e.getLastVersioning()) {
      this.f = true;
      this.g = streamingMask;
      this.h = streamingRate;
      this.e.sendCommand(new VersioningCommand());
    } else if(streamingRate.getValue() <= 0) {
      this.disableSensors();
    } else {
      int var4 = this.a(streamingRate.getValue());
      if(var4 == 0) {
        DLog.w("Calculated 0 divisor, reconfiguring to allow for highest streaming speed");
        var4 = 1;
      }

      byte var5 = 1;
      if(var4 < 2) {
        DLog.w("Using 2 packet frames since streaming rate is set higher than the robot can handle for 1");
        var5 = 2;
      }

      this.e.sendCommand(new SetDataStreamingCommand(var4, var5, streamingMask, 0));
    }
  }

  public void disableSensors() {
    this.e.sendCommand(new SetDataStreamingCommand(1, 0, 0L, 0));
  }

  private int a(int var1) {
    short var2;
    switch(this.e.getLastVersioning().getModelNumber()) {
      case 2:
        var2 = 420;
        break;
      case 3:
        var2 = 390;
        break;
      case 10:
        var2 = 400;
        break;
      default:
        DLog.w("Unrecognized model number to create normalized divisor: " + this.e.getLastVersioning().getModelNumber());
        var2 = 400;
    }

    return var2 / var1;
  }

  public void handleResponse(DeviceResponse response, Robot robot) {
    if(this.f && response instanceof VersioningResponse) {
      this.f = false;
      this.enableSensors(this.g, this.h);
    }

  }

  public void handleStringResponse(String stringResponse, Robot robot) {
  }

  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot) {
  }

  public static enum StreamingRate {
    STREAMING_RATE_OFF(0),
    STREAMING_RATE1(1),
    STREAMING_RATE10(10),
    STREAMING_RATE20(20),
    STREAMING_RATE50(50),
    STREAMING_RATE100(100),
    STREAMING_RATE200(200),
    STREAMING_RATE400(400);

    private int a;

    private StreamingRate(int value) {
      this.a = value;
    }

    public int getValue() {
      return this.a;
    }
  }
}
