package com.orbotix.common.utilities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.orbotix.common.DLog;
import java.util.HashSet;
import java.util.Set;






public class ApplicationLifecycleMonitor
{
  private static final int a = 14;
  private static ApplicationLifecycleMonitor b;
  private final Set<ApplicationLifecycleListener> c = new HashSet();
  private ApplicationState d;
  private Application e;
  private Application.ActivityLifecycleCallbacks f;
  
  public static ApplicationLifecycleMonitor getInstance() {
    if (b == null) {
      b = new ApplicationLifecycleMonitor();
    }
    return b;
  }
  
  @TargetApi(14)
  private ApplicationLifecycleMonitor() {
    if (!a()) {
      DLog.w("Android SDK is less than required version. ApplicationLifecycleMonitor will not function. Required: %d, Actual: %d", new Object[] {
        Integer.valueOf(14), Integer.valueOf(Build.VERSION.SDK_INT) });
      return;
    }
    f = new Application.ActivityLifecycleCallbacks()
    {
      public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this, ApplicationLifecycleMonitor.ApplicationState.CREATED);
      }
      
      public void onActivityStarted(Activity activity)
      {
        ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this, ApplicationLifecycleMonitor.ApplicationState.FOREGROUND);
      }
      
      public void onActivityResumed(Activity activity)
      {
        ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this, ApplicationLifecycleMonitor.ApplicationState.FOREGROUND);
        synchronized (ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this)) {
          for (ApplicationLifecycleMonitor.ApplicationLifecycleListener localApplicationLifecycleListener : ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this)) {
            localApplicationLifecycleListener.onApplicationEnteredForeground(activity);
          }
        }
      }
      
      public void onActivityPaused(Activity activity)
      {
        ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this, ApplicationLifecycleMonitor.ApplicationState.BACKGROUND);
      }
      
      public void onActivityStopped(Activity activity)
      {
        ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this, ApplicationLifecycleMonitor.ApplicationState.BACKGROUND);
        synchronized (ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this)) {
          for (ApplicationLifecycleMonitor.ApplicationLifecycleListener localApplicationLifecycleListener : ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this)) {
            localApplicationLifecycleListener.onApplicationEnteredBackground(activity);
          }
        }
      }
      


      public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
      

      public void onActivityDestroyed(Activity activity)
      {
        ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this, ApplicationLifecycleMonitor.ApplicationState.DESTROYED);
        synchronized (ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this)) {
          for (ApplicationLifecycleMonitor.ApplicationLifecycleListener localApplicationLifecycleListener : ApplicationLifecycleMonitor.a(ApplicationLifecycleMonitor.this)) {
            localApplicationLifecycleListener.onApplicationWillExit(activity);
          }
        }
      }
    };
  }
  
  @TargetApi(14)
  public void setListeningApplication(@NonNull Application application) {
    if (!a()) {
      return;
    }
    if (application.equals(e)) {
      e.unregisterActivityLifecycleCallbacks(f);
    }
    e = application;
    e.registerActivityLifecycleCallbacks(f);
  }
  
  public void addListener(ApplicationLifecycleListener listener) {
    synchronized (c) {
      c.add(listener);
    }
  }
  
  public void removeListener(ApplicationLifecycleListener listener) {
    synchronized (c) {
      if (c.contains(listener)) {
        c.remove(listener);
      }
    }
  }
  
  public ApplicationState getApplicationState() {
    return d;
  }
  
  public boolean applicationIsForeground() {
    return d == ApplicationState.FOREGROUND;
  }
  
  public boolean applicationIsBackground() {
    return d == ApplicationState.BACKGROUND;
  }
  
  private boolean a() {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  public static enum ApplicationState
  {
    private ApplicationState() {}
  }
  
  public static abstract interface ApplicationLifecycleListener
  {
    public abstract void onApplicationEnteredBackground(Activity paramActivity);
    
    public abstract void onApplicationEnteredForeground(Activity paramActivity);
    
    public abstract void onApplicationWillExit(Activity paramActivity);
  }
}
