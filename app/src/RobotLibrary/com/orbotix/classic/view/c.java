package com.orbotix.classic.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;









class c
  extends Drawable
{
  private static final float a = 0.17F;
  private static final float b = 0.2F;
  private int c = -65536;
  private final Paint d = new Paint();
  private final Point e = new Point();
  private final Point f = new Point();
  
  c() {
    d.setStyle(Paint.Style.STROKE);
    d.setStrokeCap(Paint.Cap.ROUND);
    
    d.setColor(c);
    d.setAntiAlias(true);
  }
  

  public void draw(Canvas canvas)
  {
    Rect localRect = getBounds();
    
    int i = (int)(localRect.width() * 0.17F);
    
    d.setStrokeWidth(localRect.width() * 0.2F);
    

    e.set(left + i, top + i);
    f.set(right - i, bottom - i);
    canvas.drawLine(e.x, e.y, f.x, f.y, d);
    

    e.set(left + i, bottom - i);
    f.set(right - i, top + i);
    canvas.drawLine(e.x, e.y, f.x, f.y, d);
  }
  

  public void setAlpha(int i)
  {
    d.setAlpha(i);
  }
  

  public void setColorFilter(ColorFilter colorFilter)
  {
    d.setColorFilter(colorFilter);
  }
  

  public int getOpacity()
  {
    if (d.getAlpha() == 255)
      return -1;
    if ((d.getAlpha() < 255) && (d.getAlpha() > 0)) {
      return -3;
    }
    return -2;
  }
}
