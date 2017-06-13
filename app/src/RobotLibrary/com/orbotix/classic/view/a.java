package com.orbotix.classic.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;






class a
  extends Drawable
{
  private static final float a = 0.15F;
  private static final float b = 0.2F;
  private static final float c = 0.5F;
  private static final float d = 0.3F;
  private static final float e = 0.2F;
  private final Paint f = new Paint();
  private final Point g = new Point();
  private final Point h = new Point();
  
  private int i = -16711936;
  
  a()
  {
    f.setStyle(Paint.Style.STROKE);
    f.setColor(i);
    f.setStrokeCap(Paint.Cap.ROUND);
    f.setAntiAlias(true);
  }
  

  public void draw(Canvas canvas)
  {
    Rect localRect = getBounds();
    
    int j = (int)(localRect.width() * 0.15F);
    f.setStrokeWidth(localRect.width() * 0.2F);
    

    g.set(left + j, (int)(localRect.height() * 0.5F));
    h.set((int)(localRect.width() * 0.3F), bottom - j);
    canvas.drawLine(g.x, g.y, h.x, h.y, f);
    
    g.set(h.x, h.y);
    h.set(right - j, (int)(localRect.height() * 0.2F));
    canvas.drawLine(g.x, g.y, h.x, h.y, f);
  }
  

  public void setAlpha(int i)
  {
    f.setAlpha(i);
  }
  

  public void setColorFilter(ColorFilter colorFilter)
  {
    f.setColorFilter(colorFilter);
  }
  

  public int getOpacity()
  {
    int j = f.getAlpha();
    if (j == 255)
      return -1;
    if ((j < 255) && (j > 0)) {
      return -3;
    }
    
    return -2;
  }
}
