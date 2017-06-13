//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.classic.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import com.orbotix.classic.DiscoveryAgentClassic;
import com.orbotix.common.DiscoveryAgentEventListener;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.common.RobotChangedStateListener.RobotChangedStateNotificationType;
import java.util.UUID;

public class SpheroConnectionView extends RelativeLayout {
  private static final int a = 60;
  private String b;
  private static final String c = "OBX-CV";
  private static final int d = -16739376;
  private final float e;
  private boolean f;
  private boolean g;
  private int h;
  private boolean i;
  private final SpheroConnectionView.b j;
  private Drawable k;
  private Drawable l;
  private Drawable m;
  private Drawable n;
  private static DiscoveryAgentEventListener o;
  private RobotChangedStateListener p;

  public SpheroConnectionView(Context context) {
    this(context, (AttributeSet)null);
  }

  public void setTitle(String title) {
    this.b = title;
  }

  public void enableToast(boolean showToast) {
    this.i = showToast;
  }

  public SpheroConnectionView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.b = "Select a Sphero";
    this.f = true;
    this.g = true;
    this.h = -16739376;
    this.i = true;
    this.p = new RobotChangedStateListener() {
      public void handleRobotChangedState(Robot robot, RobotChangedStateNotificationType type) {
        switch(null.a[type.ordinal()]) {
          case 1:
            SpheroConnectionView.this.a("Connected " + robot.getName());
            if(SpheroConnectionView.this.f) {
              SpheroConnectionView.this.setVisibility(4);
            }
            break;
          case 2:
            SpheroConnectionView.this.a("Disconnected " + robot.getName());
            break;
          case 3:
            SpheroConnectionView.this.a("Disconnected " + robot.getName());
            if(SpheroConnectionView.this.getVisibility() == 8) {
              return;
            }
        }

      }
    };
    this.e = context.getResources().getDisplayMetrics().density;
    LayoutParams var3 = new LayoutParams(-2, -2);
    var3.addRule(14);
    TextView var4 = new TextView(this.getContext());
    this.addView(var4);
    var4.setTextColor(-1);
    var4.setText(this.b);
    var4.setLayoutParams(var3);
    var4.setTextSize(10.0F * this.e);
    this.j = new SpheroConnectionView.b(context);
    this.startDiscovery();
  }

  private void a(final String var1) {
    if(this.i) {
      this.post(new Runnable() {
        public void run() {
          Toast.makeText(SpheroConnectionView.this.getContext(), var1, 1).show();
        }
      });
    }

  }

  /** @deprecated */
  public void showSpheros() {
    this.startDiscovery();
  }

  public void startDiscovery() {
    DiscoveryAgentClassic var1 = DiscoveryAgentClassic.getInstance();
    var1.addDiscoveryListener(o);
    var1.addRobotStateListener(this.p);
    this.setVisibility(0);
  }

  public void addDiscoveryListener(DiscoveryAgentEventListener listener) {
    DiscoveryAgentClassic.getInstance().addDiscoveryListener(listener);
  }

  public void removeDiscoveryListener(DiscoveryAgentEventListener listener) {
    DiscoveryAgentClassic.getInstance().removeDiscoveryListener(listener);
  }

  public void clearListeners() {
  }

  public void setSingleSpheroMode(boolean isSingleSphero) {
    this.f = isSingleSphero;
  }

  public void setOneAtATimeMode(boolean oneAtATimeMode) {
    this.g = oneAtATimeMode;
  }

  public void setNotYetConnectedDrawable(Drawable notYetConnectedDrawable) {
    this.k = notYetConnectedDrawable;
  }

  public void setConnectionFailedDrawable(Drawable connectionFailedDrawable) {
    this.l = connectionFailedDrawable;
  }

  public void setConnectedSuccessDrawable(Drawable connectedSuccessDrawable) {
    this.m = connectedSuccessDrawable;
  }

  public void setRowBackgroundDrawable(Drawable rowBackgroundDrawable) {
    this.n = rowBackgroundDrawable;
  }

  public void setTextColor(int textColor) {
    this.h = textColor;
  }

  private class a extends RelativeLayout {
    private static final int b = 40;
    private final FrameLayout c;
    private final TextView d;
    private View e;

    public a(Context var2) {
      super(var2);
      int var3 = (int)(6.0F * SpheroConnectionView.this.e);
      int var4 = (int)(5.0F * SpheroConnectionView.this.e);
      float var5 = (float)((int)(10.0F * SpheroConnectionView.this.e));
      float[] var6 = new float[]{var5, var5, var5, var5, var5, var5, var5, var5};
      this.setGravity(17);
      this.setPadding(var3, var4, var3, var4);
      Object var7;
      if(SpheroConnectionView.this.n == null) {
        ShapeDrawable var8 = new ShapeDrawable();
        Paint var9 = var8.getPaint();
        var9.setStrokeJoin(Join.BEVEL);
        var9.setStrokeWidth(SpheroConnectionView.this.e * 4.0F);
        var9.setStyle(Style.STROKE);
        var9.setColor(SpheroConnectionView.this.h);
        var8.setShape(new RoundRectShape(var6, (RectF)null, (float[])null));
        var8.setPadding(var3, var3, var3, var3);
        var7 = var8;
      } else {
        var7 = SpheroConnectionView.this.n;
      }

      RelativeLayout var12 = new RelativeLayout(var2);
      LayoutParams var13 = new LayoutParams(-1, -1);
      var13.setMargins(var3, var3, var3, var3);
      var13.addRule(13);
      var12.setGravity(17);
      var12.setBackgroundDrawable((Drawable)var7);
      this.addView(var12, var13);
      int var10 = (int)(3.0F * SpheroConnectionView.this.e);
      int var11 = (int)(40.0F * SpheroConnectionView.this.e);
      this.c = new FrameLayout(var2);
      var13 = new LayoutParams(var11, var11);
      var13.addRule(9);
      var13.addRule(10);
      var13.setMargins(var10, var10, var10, var10);
      this.c.setId(UUID.randomUUID().variant());
      var12.addView(this.c, var13);
      this.d = new TextView(var2);
      this.d.setTextSize(2, 22.0F);
      this.d.setTextColor(SpheroConnectionView.this.h);
      var13 = new LayoutParams(-1, -1);
      var13.addRule(11);
      var13.addRule(15);
      var13.addRule(1, this.c.getId());
      var13.leftMargin = (int)(10.0F * SpheroConnectionView.this.e);
      var12.addView(this.d, var13);
    }
  }

  private class b extends ListView {
    public b(Context var2) {
      super(var2);
    }
  }
}
