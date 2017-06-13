package com.orbotix.common.stat;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PersistentSyncRunnable
  implements Runnable
{
  public static final String PERSISTENT_STORAGE_SUFFIX = "statcache";
  private static final int a = 7000;
  private final ArrayList<Stat> b = new ArrayList();
  private Map<String, String> c = new HashMap();
  private Context d;
  private boolean e;
  
  public PersistentSyncRunnable(Context context)
  {
    d = context;
    e = false;
  }
  
  public void addStat(Stat stat) {
    synchronized (b) {
      b.add(stat);
      SLog.log(String.format("PersistentSyncThread: Cached %d stats", new Object[] { Integer.valueOf(b.size()) }));
    }
  }
  
  public void run()
  {
    SLog.log("PersistentSyncThread: start()");
    e = true;
    while (e) {
      a();
      try {
        SLog.log("PersistentSyncThread: Sleeping for 7000ms");
        Thread.sleep(7000L);
      } catch (InterruptedException localInterruptedException) {
        SLog.log("PersistentSyncThread: Thread sleep was interrupted, forcing sync and stopping");
        a();
        e = false;
      }
    }
  }
  
  private void a() {
    synchronized (b) {
      ArrayList localArrayList = new ArrayList(b);
      SLog.log(String.format("PersistentSyncThread: Syncing %d stats to persistent storage", new Object[] { Integer.valueOf(localArrayList.size()) }));
      
      Map localMap = a(localArrayList);
      
      c((ArrayList)localMap.get(Stat.StatKey.ROBOT_PROFILE.getValue()));
      d(localArrayList);
      
      b(localArrayList);
      for (Stat localStat : localArrayList) {
        b.remove(localStat);
      }
    }
  }
  
  private Map<String, ArrayList<Stat>> a(ArrayList<Stat> paramArrayList) {
    HashMap localHashMap = new HashMap();
    for (Stat localStat : paramArrayList) {
      if (localStat.getStatKey() == Stat.StatKey.ROBOT_PROFILE) {
        SLog.log("PersistentSyncThread: Detected profile stat");
        if (!localHashMap.containsKey("profile")) {
          localHashMap.put("profile", new ArrayList());
          SLog.log("PersistentSyncThread: Created new profile stat bucket");
        }
        ((ArrayList)localHashMap.get("profile")).add(localStat);
        SLog.log("PersistentSyncThread: Added profile stat to bucket");
      }
      else {
        SLog.log("PersistentSyncThread: Detected normal stat");
        if (!localHashMap.containsKey(localStat.getAssociatedMac())) {
          localHashMap.put(localStat.getAssociatedMac(), new ArrayList());
          SLog.log("PersistentSyncThread: Created new stat bucket for mac: " + localStat.getAssociatedMac());
        }
        ((ArrayList)localHashMap.get(localStat.getAssociatedMac())).add(localStat);
        SLog.log("PersistentSyncThread: Added stat to bucket: " + localStat.toString());
      }
    }
    
    return localHashMap;
  }
  
  private void b(ArrayList<Stat> paramArrayList)
  {
    Map localMap = a(paramArrayList);
    
    for (String str : localMap.keySet()) {
      e((ArrayList)localMap.get(str));
    }
  }
  
  private void c(ArrayList<Stat> paramArrayList) {
    if ((paramArrayList == null) || (paramArrayList.isEmpty())) {
      return;
    }
    
    for (Stat localStat : paramArrayList) {
      if (localStat.getStatKey() == Stat.StatKey.ROBOT_PROFILE) {
        String str1 = (String)localStat.getBaseMap().get(Stat.StatDataKey.MODEL.getValue());
        if ("30".equals(str1)) {
          String str2 = (String)localStat.getBaseMap().get(Stat.StatDataKey.FACTORY_CONFIG_BLOCK_CRC.getValue());
          String str3 = (String)localStat.getBaseMap().get(Stat.StatDataKey.SERIAL_NUMBER.getValue());
          c.put(str3, str2);
        }
      }
    }
  }
  
  private void d(ArrayList<Stat> paramArrayList) {
    if ((paramArrayList == null) || (paramArrayList.isEmpty())) {
      return;
    }
    
    for (Stat localStat : paramArrayList) {
      String str = (String)c.get(localStat.getAssociatedMac());
      localStat.addData(Stat.StatDataKey.FACTORY_CONFIG_BLOCK_CRC, str);
    }
  }
  
  private void e(ArrayList<Stat> paramArrayList) {
    String str1 = b();
    try {
      FileOutputStream localFileOutputStream = a(str1);
      for (Stat localStat : paramArrayList) {
        SLog.log(String.format("PersistentSyncThread: Writing to file: %s, stat %s", new Object[] { str1, localStat.toString() }));
        String str2 = localStat.serialize();
        String str3 = str2 + ";";
        
        localFileOutputStream.write(str3.getBytes());
      }
      localFileOutputStream.flush();
      localFileOutputStream.close();
    }
    catch (IOException localIOException) {
      SLog.log("PersistentSyncThread: IOException when writing stats to file");
      localIOException.printStackTrace();
    }
  }
  
  private String b() {
    return UUID.randomUUID().toString() + "" + "statcache";
  }
  
  private FileOutputStream a(String paramString) throws IOException {
    if (null == d) {
      SLog.log("PersistentSyncThread: Cannot create a new file with null context!");
      return null;
    }
    return d.openFileOutput(paramString, 0);
  }
}
