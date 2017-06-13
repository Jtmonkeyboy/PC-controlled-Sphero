package com.orbotix.async;

import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.sensor.Acceleration;
















public class CollisionDetectedAsyncData
  extends AsyncMessage
{
  private Acceleration a;
  private boolean b;
  private boolean c;
  private CollisionPower d;
  private float e;
  private long f;
  
  public static class CollisionPower
  {
    public final short x;
    public final short y;
    
    public CollisionPower(short x, short y)
    {
      this.x = x;
      this.y = y;
    }
    






    public boolean equals(Object o)
    {
      if (!(o instanceof CollisionPower)) return false;
      CollisionPower localCollisionPower = (CollisionPower)o;
      return (x == x) && (y == y);
    }
    
    public int hashCode()
    {
      int i = x;
      i = 31 * i + y;
      return i;
    }
    
    public String toString()
    {
      return "CollisionPower{x=" + x + ", y=" + y + '}';
    }
  }
  








  public CollisionDetectedAsyncData(byte[] packet)
  {
    super(packet);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    byte[] arrayOfByte = getData();
    
    short s1 = (short)(((short)arrayOfByte[0] << 8) + ((short)arrayOfByte[1] & 0xFF));
    short s2 = (short)(((short)arrayOfByte[2] << 8) + ((short)arrayOfByte[3] & 0xFF));
    short s3 = (short)(((short)arrayOfByte[4] << 8) + ((short)arrayOfByte[5] & 0xFF));
    
    a = new Acceleration(s1, s2, s3);
    
    b = ((arrayOfByte[6] & 0x1) > 0);
    c = ((arrayOfByte[6] & 0x2) > 0);
    
    short s4 = (short)(((short)arrayOfByte[7] << 8) + ((short)arrayOfByte[8] & 0xFF));
    short s5 = (short)(((short)arrayOfByte[9] << 8) + ((short)arrayOfByte[10] & 0xFF));
    d = new CollisionPower(s4, s5);
    
    e = (((short)arrayOfByte[11] & 0xFF) / 255.0F);
    
    f = ((arrayOfByte[12] & 0xFF) << 24);
    f += ((arrayOfByte[13] & 0xFF) << 16);
    f += ((arrayOfByte[14] & 0xFF) << 8);
    f += (arrayOfByte[15] & 0xFF);
  }
  










  public Acceleration getImpactAcceleration()
  {
    return a;
  }
  




  public boolean hasImpactXAxis()
  {
    return b;
  }
  




  public boolean hasImpactYAxis()
  {
    return c;
  }
  




  public CollisionPower getImpactPower()
  {
    return d;
  }
  




  public float getImpactSpeed()
  {
    return e;
  }
  





  public long getImpactTimeStamp()
  {
    return f;
  }
  
  public String toString()
  {
    return "CollisionDetectedAsyncData{Accel=" + a + ", ImpactX=" + b + ", ImpactY=" + c + ", ImpactPower=" + d + ", ImpactSpeed=" + e + ", ImpactTimeStamp=" + f + '}';
  }
}
