package com.orbotix.classic.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;








class b
  extends Drawable
{
  private static final int a = 6;
  private static final int b = -7829368;
  private final float c;
  private final float d;
  private final Paint e = new Paint();
  private final Paint f = new Paint();
  private final Paint g = new Paint();
  

  public b(Context paramContext)
  {
    c = getResourcesgetDisplayMetricsdensity;
    d = (6.0F * c);
    
    e.setColor(-1);
    e.setStyle(Paint.Style.STROKE);
    e.setStrokeWidth(d);
    e.setAntiAlias(true);
    
    f.setColor(-7829368);
    f.setStyle(Paint.Style.STROKE);
    f.setStrokeWidth(d * 0.6F);
    f.setAntiAlias(true);
    
    g.setColor(0);
  }
  
  public void a(int paramInt) {
    paramInt &= 0xFFFFFFFF;
    g.setColor(paramInt);
  }
  

  public void draw(Canvas canvas)
  {
    Rect localRect = getBounds();
    float f1 = localRect.width() / 2;
    float f2 = d / 2.0F;
    
    canvas.drawCircle(f1, f1, f1 - f2, g);
    canvas.drawCircle(f1, f1, f1 - f2, e);
    canvas.drawCircle(f1, f1, f1 - f2, f);
  }
  

  public void setAlpha(int i)
  {
    e.setAlpha(i);
  }
  

  public void setColorFilter(ColorFilter colorFilter)
  {
    e.setColorFilter(colorFilter);
  }
  

  public int getOpacity()
  {
    if (e.getAlpha() < 255) {
      if (e.getAlpha() > 0)
      {
        return -3;
      }
      
      return -2;
    }
    return -1;
  }
}
