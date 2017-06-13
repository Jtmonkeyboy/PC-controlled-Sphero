package com.orbotix.common.stat;

import android.os.AsyncTask;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;





public class WebSyncAgent
  extends AsyncTask<Void, Void, Boolean>
{
  private static final String a = "https://app.gosphero.com/api/v1/stats";
  private static final String b = "https://app.gosphero.com/api/v1/robots";
  private WebRequestResultListener c;
  private ArrayList<Stat> d;
  private String e;
  
  public WebSyncAgent(WebRequestResultListener listener, ArrayList<Stat> stats, String sourceFile)
  {
    c = listener;
    d = stats;
    e = sourceFile;
  }
  
  protected Boolean doInBackground(Void... params)
  {
    try {
      if ((null == d) || (d.size() == 0)) {
        SLog.log("WebSyncAgent: No stats to sync");
        return Boolean.valueOf(false);
      }
      
      String str = "https://app.gosphero.com/api/v1/stats";
      
      if (((Stat)d.get(0)).getStatKey() == Stat.StatKey.ROBOT_PROFILE) {
        str = "https://app.gosphero.com/api/v1/robots/" + ((Stat)d.get(0)).getAssociatedMac();
      }
      
      HttpPost localHttpPost = URLRequestFactory.requestWithURLAndStats(str, d);
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
      {
        return Boolean.valueOf(true);
      }
      
      SLog.log("WebSyncAgent: Sync failed http request with code: " + localHttpResponse.getStatusLine().getStatusCode());
      return Boolean.valueOf(false);
    }
    catch (IOException localIOException1) {
      SLog.log("WebSyncAgent: Exception when executing request: " + localIOException1.getMessage());
      localIOException1.printStackTrace();
    }
    return Boolean.valueOf(false);
  }
  
  protected void onPostExecute(Boolean aBoolean)
  {
    super.onPostExecute(aBoolean);
    if (c != null) {
      c.OnWebRequestResult(aBoolean.booleanValue(), d, e);
    }
  }
  
  public static abstract interface WebRequestResultListener
  {
    public abstract void OnWebRequestResult(boolean paramBoolean, ArrayList<Stat> paramArrayList, String paramString);
  }
}
