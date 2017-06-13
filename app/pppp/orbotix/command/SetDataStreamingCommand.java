package com.orbotix.command;

import com.orbotix.async.DeviceSensorAsyncMessage;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.common.sensor.SensorFlag;
import java.util.HashMap;













public class SetDataStreamingCommand
  extends DeviceCommand
  implements ResponseListener
{
  private final int a;
  private final int b;
  private final long c;
  private final byte d;
  private static HashMap<String, a> e = new HashMap();
  










  public SetDataStreamingCommand(int divisor, int packetFrames, long sensorMask, int packetCount)
  {
    a = divisor;
    
    b = packetFrames;
    DeviceSensorAsyncMessage.sPacketFrames = packetFrames;
    
    c = sensorMask;
    DeviceSensorAsyncMessage.sMask = sensorMask;
    
    d = ((byte)packetCount);
  }
  
  public SetDataStreamingCommand(int divisor, int packetFrames, SensorFlag sensorMask, int packetCount) {
    this(divisor, packetFrames, sensorMask.longValue(), packetCount);
  }
  
  public byte getDeviceId()
  {
    return DeviceId.ROBOT.getValue();
  }
  
  public byte getCommandId()
  {
    return RobotCommandId.SET_DATA_STREAMING.getValue();
  }
  
  public byte[] getData()
  {
    byte[] arrayOfByte = new byte[13];
    
    arrayOfByte[0] = ((byte)(a >> 8));
    arrayOfByte[1] = ((byte)a);
    arrayOfByte[2] = ((byte)(b >> 8));
    arrayOfByte[3] = ((byte)b);
    arrayOfByte[4] = ((byte)(int)(c >> 24));
    arrayOfByte[5] = ((byte)(int)(c >> 16));
    arrayOfByte[6] = ((byte)(int)(c >> 8));
    arrayOfByte[7] = ((byte)(int)c);
    arrayOfByte[8] = d;
    arrayOfByte[9] = ((byte)(int)(c >> 56));
    arrayOfByte[10] = ((byte)(int)(c >> 48));
    arrayOfByte[11] = ((byte)(int)(c >> 40));
    arrayOfByte[12] = ((byte)(int)(c >> 32));
    
    return arrayOfByte;
  }
  





  public long getMask()
  {
    return c;
  }
  


  public void handleResponse(DeviceResponse response, Robot robot) {}
  


  public void handleStringResponse(String stringResponse, Robot robot) {}
  


  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot)
  {
    if ((asyncMessage instanceof DeviceSensorAsyncMessage)) {
      String str = robot.getIdentifier();
      
      if (e.containsKey(str)) {
        a localA = (a)e.get(str);
        
        if (a.a(localA))
        {
          if (localA.b()) {
            robot.sendCommand(new SetDataStreamingCommand(localA
              .d(), localA
              .e(), localA
              .f(), localA
              .g()));
            localA.a();
          }
        }
      }
    }
  }
  

  private static class a
  {
    private static final int a = 200;
    
    private static final int b = 50;
    private int c;
    private int d;
    private int e;
    private long f;
    private int g;
    
    public a(int paramInt1, int paramInt2, long paramLong, int paramInt3)
    {
      d = paramInt1;
      e = paramInt2;
      f = paramLong;
      g = paramInt3;
    }
    




    private boolean i()
    {
      if (++c > 150) {
        return true;
      }
      return false;
    }
    
    public void a()
    {
      c = 0;
    }
    




    public boolean b()
    {
      return g == 0;
    }
    
    public int c()
    {
      return c;
    }
    
    public int d() {
      return d;
    }
    
    public int e() {
      return e;
    }
    
    public long f() {
      return f;
    }
    
    public int g() {
      return g;
    }
    




    public int h()
    {
      return g == 0 ? 200 : g;
    }
  }
  



  public String toString()
  {
    return "SetDataStreamingCommand{Divisor=" + a + ", PacketFrames=" + b + ", SensorMask=" + String.format("%16X", new Object[] { Long.valueOf(c) }) + ", PacketCount=" + d + '}';
  }
}
