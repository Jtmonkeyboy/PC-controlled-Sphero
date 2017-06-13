package com.orbotix.macro;

import org.apache.http.util.ByteArrayBuffer;






/**
 * @deprecated
 */
public class Macro
{
  public static final int RAW_MOTOR_FORWARD = 1;
  public static final int RAW_MOTOR_REVERSE = 2;
  private static final int a = 0;
  private static final int b = 1;
  private static final int c = 2;
  private static final int d = 3;
  private static final int e = 4;
  private static final int f = 5;
  private static final int g = 6;
  private static final int h = 7;
  private static final int i = 8;
  private static final int j = 9;
  private static final int k = 10;
  private static final int l = 11;
  private static final int m = 12;
  private static final int n = 13;
  private static final int o = 14;
  private static final int p = 15;
  private static final int q = 16;
  private static final int r = 17;
  private static final int s = 18;
  private static final int t = 19;
  private static final int u = 20;
  private static final int v = 27;
  private static final int w = 250;
  private ByteArrayBuffer x;
  private int y;
  
  public Macro()
  {
    x = new ByteArrayBuffer(250);
    y = 0;
  }
  
  private void a(byte paramByte) {
    if (y == 249) {
      return;
    }
    x.append(paramByte);
    y += 1;
  }
  
  private void a(int paramInt) {
    if (y == 249) {
      return;
    }
    x.append(paramInt);
    y += 1;
  }
  
  public int getAvailableBytes() {
    return 250 - (y + 1);
  }
  
  public boolean calibrate(int heading, int delay)
  {
    if ((heading > 360) || (heading < 0)) return false;
    if ((delay > 255) || (delay < 0)) return false;
    if (getAvailableBytes() < 4) { return false;
    }
    
    a(4);
    a(heading >> 8);
    a(heading & 0xFF);
    a(delay);
    return true;
  }
  
  public boolean rgb(float r, float g, float b, int delay)
  {
    if ((r < 0.0D) || (r > 1.0D)) return false;
    if ((g < 0.0D) || (g > 1.0D)) return false;
    if ((b < 0.0D) || (b > 1.0D)) return false;
    if ((delay < 0) || (delay > 255)) return false;
    if (getAvailableBytes() < 5) { return false;
    }
    int i1 = (int)(r * 255.0F);
    int i2 = (int)(g * 255.0F);
    int i3 = (int)(b * 255.0F);
    
    a(7);
    a(i1);
    a(i2);
    a(i3);
    a(delay);
    return true;
  }
  
  public boolean rgb(int r, int g, int b, int delay) {
    if ((delay < 0) || (delay > 255)) return false;
    if (getAvailableBytes() < 5) { return false;
    }
    a(7);
    a(r);
    a(g);
    a(b);
    a(delay);
    return true;
  }
  
  public boolean frontLED(float brightness, int delay) {
    if ((brightness < 0.0D) || (brightness > 1.0D)) return false;
    if ((delay < 0) || (delay > 255)) return false;
    if (getAvailableBytes() < 3) return false;
    int i1 = (int)(brightness * 255.0F);
    a(9);
    a(i1);
    a(delay);
    return true;
  }
  
  public boolean roll(float speed, int heading, int delay)
  {
    if ((speed < 0.0D) || (speed > 1.0D)) return false;
    if ((heading < 0) || (heading > 360)) return false;
    if ((delay < 0) || (delay > 255)) return false;
    if (getAvailableBytes() < 5) { return false;
    }
    int i1 = (int)(speed * 255.0F);
    
    a(5);
    a(i1);
    a(heading >> 8);
    a(heading & 0xFF);
    a(delay);
    return true;
  }
  
  public boolean setSD1(int delay)
  {
    if ((delay > 65535) || (delay < 0)) return false;
    if (getAvailableBytes() < 3) return false;
    a(1);
    a(delay >> 8);
    a(delay & 0xFF);
    return true;
  }
  

  public boolean rollSD1(float speed, int heading)
  {
    if ((speed < 0.0D) || (speed > 1.0D)) return false;
    if ((heading < 0) || (heading > 360)) return false;
    if (getAvailableBytes() < 4) { return false;
    }
    int i1 = (int)(speed * 255.0F);
    
    a(6);
    a(i1);
    a(heading >> 8);
    a(heading & 0xFF);
    return true;
  }
  
  public boolean setSD2(int delay)
  {
    if ((delay > 65535) || (delay < 0)) return false;
    if (getAvailableBytes() < 3) return false;
    a(2);
    a(delay >> 8);
    a(delay & 0xFF);
    return true;
  }
  
  public boolean rgbSD2(float r, float g, float b)
  {
    if ((r < 0.0D) || (r > 1.0D)) return false;
    if ((g < 0.0D) || (g > 1.0D)) return false;
    if ((b < 0.0D) || (b > 1.0D)) return false;
    if (getAvailableBytes() < 4) { return false;
    }
    int i1 = (int)(r * 255.0F);
    int i2 = (int)(g * 255.0F);
    int i3 = (int)(b * 255.0F);
    
    a(8);
    a(i1);
    a(i2);
    a(i3);
    return true;
  }
  
  public boolean stabilization(boolean setting, int delay) {
    if ((delay > 255) || (delay < 0)) return false;
    if (getAvailableBytes() < 3) return false;
    a(3);
    a(setting ? 1 : 0);
    a(delay);
    return true;
  }
  
  public boolean delay(int delay)
  {
    if ((delay > 65535) || (delay < 0)) return false;
    if (getAvailableBytes() < 3) return false;
    a(11);
    a(delay >> 8);
    a(delay & 0xFF);
    return true;
  }
  
  public boolean setSPD1(float speed)
  {
    if ((speed < 0.0D) || (speed > 1.0D)) return false;
    if (getAvailableBytes() < 2) return false;
    int i1 = (int)(speed * 255.0F);
    a(15);
    a(i1);
    return true;
  }
  
  public boolean setSPD2(float speed) {
    if ((speed < 0.0D) || (speed > 1.0D)) return false;
    if (getAvailableBytes() < 2) return false;
    int i1 = (int)(speed * 255.0F);
    a(16);
    a(i1);
    return true;
  }
  
  public boolean rollSD1SPD1(int heading)
  {
    if ((heading < 0) || (heading > 360)) return false;
    if (getAvailableBytes() < 3) { return false;
    }
    a(17);
    a(heading >> 8);
    a(heading & 0xFF);
    return true;
  }
  
  public boolean rollSD1SPD2(int heading)
  {
    if ((heading < 0) || (heading > 360)) return false;
    if (getAvailableBytes() < 3) { return false;
    }
    a(18);
    a(heading >> 8);
    a(heading & 0xFF);
    return true;
  }
  
  public boolean sleepnow(int delay)
  {
    if ((delay > 65535) || (delay < 0)) return false;
    if (getAvailableBytes() < 3) return false;
    a(14);
    a(delay >> 8);
    a(delay & 0xFF);
    return true;
  }
  
  public boolean rotationRate(float rate) {
    if ((rate < 0.0D) || (rate > 1.0D)) return false;
    if (getAvailableBytes() < 2) return false;
    int i1 = (int)(rate * 255.0F);
    a(19);
    a(i1);
    return true;
  }
  
  public boolean slew(float r, float g, float b, int delay)
  {
    if ((r < 0.0D) || (r > 1.0D)) return false;
    if ((g < 0.0D) || (g > 1.0D)) return false;
    if ((b < 0.0D) || (b > 1.0D)) return false;
    if ((delay < 0) || (delay > 65535)) return false;
    if (getAvailableBytes() < 6) { return false;
    }
    int i1 = (int)(r * 255.0F);
    int i2 = (int)(g * 255.0F);
    int i3 = (int)(b * 255.0F);
    
    a(20);
    a(i1);
    a(i2);
    a(i3);
    a(delay >> 8);
    a(delay & 0xFF);
    return true;
  }
  
  public boolean endStreaming() {
    a(27);
    return true;
  }
  











  public boolean rawMotor(int leftMode, int leftSpeed, int rightMode, int rightSpeed, int delay)
  {
    if ((leftMode != 1) && (leftMode != 2)) return false;
    if ((rightMode != 1) && (rightMode != 2)) return false;
    if ((leftSpeed < 0) && (leftSpeed > 255)) return false;
    if ((rightSpeed < 0) && (rightSpeed > 255)) return false;
    if ((delay < 0) && (delay > 255)) return false;
    if (getAvailableBytes() < 5) { return false;
    }
    a(10);
    a(leftMode);
    a(leftSpeed);
    a(rightMode);
    a(rightSpeed);
    a(delay);
    
    return true;
  }
  
  public int macroLength()
  {
    return y + 1;
  }
  
  public byte[] macroBytes()
  {
    a(0);
    return x.toByteArray();
  }
  
  public String toString()
  {
    String str1 = "";
    byte[] arrayOfByte = macroBytes();
    for (int i1 = 0; i1 < y; i1++) {
      String str2 = Integer.toHexString(arrayOfByte[i1]);
      if (str2.length() == 1) {
        str2 = "0" + str2;
      }
      str1 = str1 + str2;
      if (i1 % 2 == 1) {
        str1 = str1 + " ";
      }
    }
    
    return str1;
  }
}
