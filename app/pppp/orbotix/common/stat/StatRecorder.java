package com.orbotix.common.stat;

import android.content.Context;
import java.util.Date;


public class StatRecorder
{
  private final long a = 2000L;
  
  private Date b;
  private Thread c;
  private Thread d;
  private PersistentSyncRunnable e;
  private WebSyncRunnable f;
  private boolean g;
  private static StatRecorder h;
  
  public static StatRecorder getInstance()
  {
    if (null == h) {
      h = new StatRecorder();
    }
    return h;
  }
  
  private StatRecorder() {
    g = false;
  }
  
  public void recordStat(Stat stat) {
    if (!g) {
      SLog.log("Stat recorder: Stat recorder is not running, start it with StatRecorder.getInstance().start()");
      return;
    }
    if (null == stat) {
      SLog.log("Stat recorder: Stat is null, ignoring...");
      return;
    }
    if (!a(stat.getAssociatedMac())) {
      SLog.log("Stat recorder: Stat does not have a valid mac address, ignoring...");
    }
    
    if (stat.getStatKey() == Stat.StatKey.RGB_CHANGE) {
      Date localDate = new Date();
      if (null != b)
      {
        if (localDate.getTime() - b.getTime() < 2000L)
        {
          return;
        }
      }
      b = localDate;
    }
    
    SLog.log(String.format("Stat recorder: Caching stat: %s", new Object[] { stat.toString() }));
    e.addStat(stat);
  }
  
  public void start(Context context) {
    if (g) {
      SLog.log("Stat recorder: recorder already started");
      return;
    }
    SLog.log("Stat recorder: Enabling StatRecorder syncing");
    e = new PersistentSyncRunnable(context);
    c = new Thread(e);
    c.start();
    
    f = new WebSyncRunnable(context);
    d = new Thread(f);
    d.start();
    
    g = true;
  }
  
  public void stop() {
    if (!g) {
      SLog.log("Stat recorder: recorder already stopped");
      return;
    }
    SLog.log("Stat recorder: Disabling StatRecorder syncing");
    c.interrupt();
    d.interrupt();
    g = false;
  }
  
  private static boolean a(String paramString) {
    return (null != paramString) && (paramString.length() != 0) && (paramString.matches("^([0-9A-F]{2}[:-]?){5}([0-9A-F]{2})$"));
  }
}
