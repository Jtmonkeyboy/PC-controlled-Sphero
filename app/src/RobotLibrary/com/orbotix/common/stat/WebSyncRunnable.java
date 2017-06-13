package com.orbotix.common.stat;

import android.content.Context;
import android.os.Handler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class WebSyncRunnable
  implements WebSyncAgent.WebRequestResultListener, Runnable
{
  private static final int a = 23000;
  private Handler b;
  private Context c;
  private boolean d;
  
  public WebSyncRunnable(Context context)
  {
    c = context;
    d = false;
    b = new Handler(context.getMainLooper());
  }
  
  public void run()
  {
    SLog.log("WebSyncThread: start()");
    d = true;
    while (d) {
      a();
      try {
        SLog.log("WebSyncThread: Sleeping for 23000ms");
        Thread.sleep(23000L);
      } catch (InterruptedException localInterruptedException) {
        SLog.log("WebSyncThread: Thread sleep was interrupted, stopping");
        d = false;
      }
    }
  }
  
  private void a() {
    Map localMap = b();
    int i = 0;
    
    Object localObject1 = new ArrayList();
    final HashMap localHashMap = new HashMap();
    
    Object localObject2 = null;
    for (Object localObject3 = localMap.keySet().iterator(); ((Iterator)localObject3).hasNext();) { localObject4 = (String)((Iterator)localObject3).next();
      localObject5 = (ArrayList)localMap.get(localObject4);
      if (((Stat)((ArrayList)localObject5).get(0)).getStatKey() == Stat.StatKey.ROBOT_PROFILE) {
        localObject1 = localObject5;
        localObject2 = localObject4;
      } else {
        localHashMap.put(localObject4, localObject5);
      }
    }
    
    localObject3 = localObject2;
    Object localObject4 = localObject1;
    if (!((ArrayList)localObject4).isEmpty()) {
      b.post(new Runnable()
      {
        public void run() {
          new WebSyncAgent(WebSyncRunnable.this, a, b).execute(new Void[0]);
        }
        
      });
      i += ((ArrayList)localObject1).size();
    }
    
    for (Object localObject5 = localHashMap.keySet().iterator(); ((Iterator)localObject5).hasNext();) { final String str = (String)((Iterator)localObject5).next();
      final ArrayList localArrayList = (ArrayList)localHashMap.get(str);
      i += localArrayList.size();
      final int j = i;
      b.post(new Runnable()
      {
        public void run() {
          SLog.log(String.format("WebSyncThread: Syncing %d stats in %d buckets to the web", new Object[] { Integer.valueOf(j), Integer.valueOf(localHashMap.keySet().size()) }));
          new WebSyncAgent(WebSyncRunnable.this, localArrayList, str).execute(new Void[0]);
        }
      });
    }
  }
  
  private Map<String, ArrayList<Stat>> b() {
    HashMap localHashMap = new HashMap();
    File localFile1 = c.getFilesDir();
    for (File localFile2 : localFile1.listFiles(new FilenameFilter()
    {
      public boolean accept(File dir, String filename) {
        return filename.endsWith("statcache");
      }
    }))
    {




      SLog.log("WebSyncThread: Parsing file in documents: " + localFile2.getName());
      ArrayList localArrayList = a(localFile2.getName());
      if ((null != localArrayList) && (localArrayList.size() > 0)) {
        localHashMap.put(localFile2.getName(), localArrayList);
      }
    }
    return localHashMap;
  }
  
  private ArrayList<Stat> a(String paramString) {
    File localFile = new File(c.getFilesDir(), paramString);
    SLog.log("WebSyncThread: Creating stats from file: " + paramString);
    ArrayList localArrayList = new ArrayList();
    String str1 = null;
    try {
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      str1 = a(localFileInputStream);
    } catch (FileNotFoundException localFileNotFoundException) {
      SLog.log("WebSyncThread: File does not exist at path " + paramString);
      localFileNotFoundException.printStackTrace();
      return null;
    } catch (IOException localIOException) {
      SLog.log("WebSyncThread: Exception occurred while reading file at path: " + paramString);
      localIOException.printStackTrace();
      return null;
    }
    if ((null == str1) || (str1.length() == 0)) {
      SLog.log("WebSyncThread: Deleting blank file");
      c.deleteFile(paramString);
      return null;
    }
    String[] arrayOfString1 = str1.split(";");
    for (String str2 : arrayOfString1)
      if (str2.length() > 1)
      {

        SLog.log("WebSyncThread: Found stat in file: " + str2);
        localArrayList.add(Stat.deserialize(str2));
      }
    return localArrayList;
  }
  
  private String a(InputStream paramInputStream) throws IOException {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    String str;
    while ((str = localBufferedReader.readLine()) != null) {
      localStringBuilder.append(str).append("\n");
    }
    localBufferedReader.close();
    return localStringBuilder.toString();
  }
  
  public void OnWebRequestResult(boolean success, ArrayList<Stat> stats, String sourceFile)
  {
    if (success) {
      SLog.log("WebSyncThread: Web request succeeded, removing file at path: " + sourceFile);
      c.deleteFile(sourceFile);
    }
    else {
      SLog.log("WebSyncThread: Web request failure");
    }
  }
}
