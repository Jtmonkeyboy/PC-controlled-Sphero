package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;
import org.apache.http.util.ByteArrayBuffer;




public class Roll
  implements MacroCommand
{
  private static final String a = "Roll";
  public static final byte COMMAND_ID = 5;
  public static final byte COMMAND_ID2 = 29;
  private int b = 0;
  private int c = 0;
  private float d = 0.5F;
  




  public int getDelay()
  {
    return b;
  }
  




  public void setDelay(int delay)
  {
    b = (delay & 0xFFFF);
  }
  




  public int getHeading()
  {
    return c;
  }
  




  public void setHeading(int heading)
  {
    c = (heading < 0 ? 0 : heading);
    c = (c > 359 ? 359 : c);
  }
  




  public float getSpeed()
  {
    return d;
  }
  




  public void setSpeed(float speed)
  {
    d = (speed < 0.0F ? 0.0F : speed);
    d = (d > 1.0F ? 1.0F : d);
  }
  





  public int getLength()
  {
    if (b > 255) {
      return 6;
    }
    return 5;
  }
  




  public byte[] getByteRepresentation()
  {
    Integer localInteger = Integer.valueOf((int)(d * 255.0D));
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(getLength());
    
    if (b > 255) {
      localByteArrayBuffer.append(29);
    } else {
      localByteArrayBuffer.append(5);
    }
    
    localByteArrayBuffer.append(localInteger.intValue());
    localByteArrayBuffer.append(c >> 8);
    localByteArrayBuffer.append(c & 0xFF);
    
    if (b > 255) {
      localByteArrayBuffer.append(b >> 8);
      localByteArrayBuffer.append(b & 0xFF);
    } else {
      localByteArrayBuffer.append(b & 0xFF);
    }
    
    return localByteArrayBuffer.toByteArray();
  }
  
  public String getName()
  {
    return "Roll";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public String getSettingsString()
  {
    return d + " " + c + " " + b;
  }
  





  public Roll(byte[] data)
  {
    setSpeed(ByteUtil.convertUnsignedToInt(data[1]) / 255.0F);
    int i = ByteUtil.convertUnsignedToInt(data[2], data[3]);
    setHeading(i);
    
    if (data[0] == 5) {
      setDelay(ByteUtil.convertUnsignedToInt(data[4]));
    } else {
      setDelay(ByteUtil.convertUnsignedToInt(data[4], data[5]));
    }
  }
  

  public Roll(float speed, int heading, int delay)
  {
    setSpeed(speed);
    setHeading(heading);
    setDelay(delay);
  }
  




  public static byte getCommandID()
  {
    return 5;
  }
}
