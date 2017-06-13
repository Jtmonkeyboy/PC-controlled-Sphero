package com.orbotix.classic.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.orbotix.classic.DiscoveryAgentClassic;
import com.orbotix.common.DiscoveryAgentEventListener;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.common.RobotChangedStateListener.RobotChangedStateNotificationType;
import java.util.UUID;

































public class SpheroConnectionView
  extends RelativeLayout
{
  private static final int a = 60;
  private String b = "Select a Sphero";
  
  private static final String c = "OBX-CV";
  
  private static final int d = -16739376;
  private final float e;
  private boolean f = true;
  private boolean g = true;
  
  private int h = -16739376;
  private boolean i = true;
  
  private final b j;
  
  private Drawable k;
  
  private Drawable l;
  
  private Drawable m;
  
  private Drawable n;
  private static DiscoveryAgentEventListener o;
  private RobotChangedStateListener p = new RobotChangedStateListener()
  {

    public void handleRobotChangedState(Robot robot, RobotChangedStateListener.RobotChangedStateNotificationType type)
    {
      switch (SpheroConnectionView.3.a[type.ordinal()]) {
      case 1: 
        SpheroConnectionView.a(SpheroConnectionView.this, "Connected " + robot.getName());
        if (SpheroConnectionView.a(SpheroConnectionView.this)) {
          setVisibility(4);
        }
        






        break;
      case 2: 
        SpheroConnectionView.a(SpheroConnectionView.this, "Disconnected " + robot.getName());
        






        break;
      
      case 3: 
        SpheroConnectionView.a(SpheroConnectionView.this, "Disconnected " + robot.getName());
        if (getVisibility() == 8) {}
        

        break;
      }
      
    }
  };
  

  public SpheroConnectionView(Context context)
  {
    this(context, null);
  }
  




  public void setTitle(String title)
  {
    b = title;
  }
  




  public void enableToast(boolean showToast)
  {
    i = showToast;
  }
  
  public SpheroConnectionView(Context context, AttributeSet attrs) {
    super(context, attrs);
    e = getResourcesgetDisplayMetricsdensity;
    

    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    
    localLayoutParams.addRule(14);
    
    TextView localTextView = new TextView(getContext());
    addView(localTextView);
    localTextView.setTextColor(-1);
    localTextView.setText(b);
    localTextView.setLayoutParams(localLayoutParams);
    localTextView.setTextSize(10.0F * e);
    


    j = new b(context);
    






















    startDiscovery();
  }
  
  private void a(final String paramString) {
    if (i)
      post(new Runnable()
      {
        public void run() {
          Toast.makeText(getContext(), paramString, 1).show();
        }
      });
  }
  
  /**
   * @deprecated
   */
  public void showSpheros() { startDiscovery(); }
  

  public void startDiscovery()
  {
    DiscoveryAgentClassic localDiscoveryAgentClassic = DiscoveryAgentClassic.getInstance();
    
    localDiscoveryAgentClassic.addDiscoveryListener(o);
    localDiscoveryAgentClassic.addRobotStateListener(p);
    

    setVisibility(0);
  }
  








































  public void addDiscoveryListener(DiscoveryAgentEventListener listener)
  {
    DiscoveryAgentClassic.getInstance().addDiscoveryListener(listener);
  }
  
  public void removeDiscoveryListener(DiscoveryAgentEventListener listener) {
    DiscoveryAgentClassic.getInstance().removeDiscoveryListener(listener);
  }
  





  public void clearListeners() {}
  





  public void setSingleSpheroMode(boolean isSingleSphero)
  {
    f = isSingleSphero;
  }
  





  public void setOneAtATimeMode(boolean oneAtATimeMode)
  {
    g = oneAtATimeMode;
  }
  





  public void setNotYetConnectedDrawable(Drawable notYetConnectedDrawable)
  {
    k = notYetConnectedDrawable;
  }
  




  public void setConnectionFailedDrawable(Drawable connectionFailedDrawable)
  {
    l = connectionFailedDrawable;
  }
  




  public void setConnectedSuccessDrawable(Drawable connectedSuccessDrawable)
  {
    m = connectedSuccessDrawable;
  }
  
  public void setRowBackgroundDrawable(Drawable rowBackgroundDrawable) {
    n = rowBackgroundDrawable;
  }
  
  public void setTextColor(int textColor) {
    h = textColor;
  }
  
  private class b extends ListView
  {
    public b(Context paramContext)
    {
      super();
    }
  }
  
  private class a
    extends RelativeLayout
  {
    private static final int b = 40;
    private final FrameLayout c;
    private final TextView d;
    private View e;
    
    public a(Context paramContext)
    {
      super();
      
      int i = (int)(6.0F * SpheroConnectionView.b(SpheroConnectionView.this));
      int j = (int)(5.0F * SpheroConnectionView.b(SpheroConnectionView.this));
      float f = (int)(10.0F * SpheroConnectionView.b(SpheroConnectionView.this));
      float[] arrayOfFloat = { f, f, f, f, f, f, f, f };
      









      setGravity(17);
      setPadding(i, j, i, j);
      
      Object localObject1;
      
      if (SpheroConnectionView.c(SpheroConnectionView.this) == null) {
        localObject2 = new ShapeDrawable();
        localObject3 = ((ShapeDrawable)localObject2).getPaint();
        ((Paint)localObject3).setStrokeJoin(Paint.Join.BEVEL);
        ((Paint)localObject3).setStrokeWidth(SpheroConnectionView.b(SpheroConnectionView.this) * 4.0F);
        ((Paint)localObject3).setStyle(Paint.Style.STROKE);
        ((Paint)localObject3).setColor(SpheroConnectionView.d(SpheroConnectionView.this));
        ((ShapeDrawable)localObject2).setShape(new RoundRectShape(arrayOfFloat, null, null));
        ((ShapeDrawable)localObject2).setPadding(i, i, i, i);
        localObject1 = localObject2;
      }
      else {
        localObject1 = SpheroConnectionView.c(SpheroConnectionView.this);
      }
      

      Object localObject2 = new RelativeLayout(paramContext);
      Object localObject3 = new RelativeLayout.LayoutParams(-1, -1);
      ((RelativeLayout.LayoutParams)localObject3).setMargins(i, i, i, i);
      ((RelativeLayout.LayoutParams)localObject3).addRule(13);
      ((RelativeLayout)localObject2).setGravity(17);
      ((RelativeLayout)localObject2).setBackgroundDrawable((Drawable)localObject1);
      addView((View)localObject2, (ViewGroup.LayoutParams)localObject3);
      

      int k = (int)(3.0F * SpheroConnectionView.b(SpheroConnectionView.this));
      int m = (int)(40.0F * SpheroConnectionView.b(SpheroConnectionView.this));
      c = new FrameLayout(paramContext);
      localObject3 = new RelativeLayout.LayoutParams(m, m);
      ((RelativeLayout.LayoutParams)localObject3).addRule(9);
      ((RelativeLayout.LayoutParams)localObject3).addRule(10);
      ((RelativeLayout.LayoutParams)localObject3).setMargins(k, k, k, k);
      c.setId(UUID.randomUUID().variant());
      ((RelativeLayout)localObject2).addView(c, (ViewGroup.LayoutParams)localObject3);
      

      d = new TextView(paramContext);
      d.setTextSize(2, 22.0F);
      d.setTextColor(SpheroConnectionView.d(SpheroConnectionView.this));
      localObject3 = new RelativeLayout.LayoutParams(-1, -1);
      ((RelativeLayout.LayoutParams)localObject3).addRule(11);
      ((RelativeLayout.LayoutParams)localObject3).addRule(15);
      ((RelativeLayout.LayoutParams)localObject3).addRule(1, c.getId());
      leftMargin = ((int)(10.0F * SpheroConnectionView.b(SpheroConnectionView.this)));
      ((RelativeLayout)localObject2).addView(d, (ViewGroup.LayoutParams)localObject3);
    }
  }
}
